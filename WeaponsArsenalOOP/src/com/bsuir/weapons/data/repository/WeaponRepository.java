package com.bsuir.weapons.data.repository;

import com.bsuir.weapons.model.weapon.ammo.Bullet;
import com.bsuir.weapons.model.weapon.AbstractWeapon;
import com.bsuir.weapons.model.weapon.Quality;
import com.bsuir.weapons.model.weapon.WeaponType;
import com.bsuir.weapons.model.weapon.melee.AbstractMeleeWeapon;
import com.bsuir.weapons.model.weapon.melee.impl.Bat;
import com.bsuir.weapons.model.weapon.melee.impl.Blade;
import com.bsuir.weapons.model.weapon.ranged.AbstractFirearm;
import com.bsuir.weapons.model.weapon.ranged.AbstractRangedWeapon;
import com.bsuir.weapons.model.weapon.ranged.impl.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WeaponRepository implements Repository<AbstractWeapon> {
    private static final AtomicBoolean IS_REPOSITORY_CREATED = new AtomicBoolean(false);
    private static final Lock INSTANCE_LOCKER = new ReentrantLock();

    private static WeaponRepository instance = null;
    private final List<AbstractWeapon> weapons = new ArrayList<>();

    private WeaponRepository() {
        Pistol pistol = new Pistol("Dual berettas", 1.15f, 400, Quality.MINIMAL_WEAR,
                80, 30, 9, false);
        pistol.setBulletsDeque(new ArrayDeque<>(Arrays.asList(
                new Bullet(20.2f, 10.43f, true),
                new Bullet(50.2f, 10.43f, false)
        )));
        add(
                new Bat("Basketball bat", 1.5f, 70, Quality.FACTORY_NEW,
                        true, 0.9f, false, "Tree"),
                new Blade("Knife", 0.56f, 40, Quality.MINIMAL_WEAR,
                        true, 0.3f, 100),
                new ArrowWeapon("Bow", 0.7f, 200, Quality.WELL_WORN, 65, 1),
                new AutomaticGun("AK-47", 3.47f, 1000, Quality.FACTORY_NEW,
                        350, 30, 5f, 8.3f), pistol,
                new Shotgun("Spas-12", 4.4f, 650, Quality.FIELD_TESTED,
                        50, 8, 18.5f, 1),
                new SniperRifle("AWP", 6.5f, 1400, Quality.MINIMAL_WEAR,
                        1500, 10, 7.7f, 10f)
        );
    }

    public static WeaponRepository getInstance() {
        if (!IS_REPOSITORY_CREATED.get()) {
            INSTANCE_LOCKER.lock();
            try {
                if (!IS_REPOSITORY_CREATED.get()) {
                    instance = new WeaponRepository();
                    IS_REPOSITORY_CREATED.set(true);
                }
            } finally {
                INSTANCE_LOCKER.unlock();
            }

        }
        return instance;
    }

    @Override
    public void add(AbstractWeapon... weapons) {
        for (AbstractWeapon weapon : weapons) {
            long generatedId = IdGenerator.generate();
            weapon.setId(generatedId);
            this.weapons.add(weapon);
        }
    }

    @Override
    public void remove(AbstractWeapon item) {
        weapons.remove(item);
    }

    @Override
    public void update(AbstractWeapon item) {
        for (int i = 0; i < weapons.size(); i++) {
            if (weapons.get(i).getId() == item.getId()) {
                weapons.set(i, item);
                break;
            }
        }
    }

    @Override
    public Optional<AbstractWeapon> findById(long id) {
        for (AbstractWeapon weapon : weapons) {
            if (weapon.getId() == id) {
                return Optional.of(weapon);
            }
        }
        return Optional.empty();
    }

    @Override
    public void clear() {
        weapons.clear();
    }

    public List<AbstractWeapon> getWeaponsByType(WeaponType weaponType) {
        List<AbstractWeapon> typedList = new ArrayList<>();
        weapons.forEach(abstractWeapon -> {
            if (isTyped(abstractWeapon, weaponType)) {
                typedList.add(abstractWeapon);
            }
        });
        return typedList;
    }

    private boolean isTyped(AbstractWeapon abstractWeapon, WeaponType weaponType) {
        switch (weaponType) {
            case MELEE:
                return abstractWeapon instanceof AbstractMeleeWeapon;
            case RANGED:
                return abstractWeapon instanceof AbstractRangedWeapon;
            case FIREARM:
                return abstractWeapon instanceof AbstractFirearm;
            case BAT:
                return abstractWeapon instanceof Bat;
            case BLADE:
                return abstractWeapon instanceof Blade;
            case ARROW_WEAPON:
                return abstractWeapon instanceof ArrowWeapon;
            case SHOTGUN:
                return abstractWeapon instanceof Shotgun;
            case AUTOMATIC_GUN:
                return abstractWeapon instanceof AutomaticGun;
            case SNIPER_RIFLE:
                return abstractWeapon instanceof SniperRifle;
            case PISTOL:
                return abstractWeapon instanceof Pistol;
        }
        ;
        return true;
    }
}
