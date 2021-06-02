package com.bsuir.weapons.model.weapon.ranged;

import com.bsuir.weapons.model.weapon.ammo.WeaponAmmunition;
import com.bsuir.weapons.model.weapon.ammo.Bullet;
import com.bsuir.weapons.model.weapon.Quality;

import java.util.Deque;

public abstract class AbstractFirearm extends AbstractRangedWeapon {
    private WeaponAmmunition<Bullet> bullets;
    private float calibre;

    public AbstractFirearm(String name, float weight, int cost, Quality quality,
                           float range, int capacity, FiringMode firingMode, float calibre) {
        super(name, weight, cost, quality, range, capacity, firingMode);
        this.calibre = calibre;
    }

    public AbstractFirearm() {
        //For XML Deserialization
    }

    public void setBullets(WeaponAmmunition<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void setBulletsDeque(Deque<Bullet> deque) {
        bullets = new WeaponAmmunition<>(deque);
    }

    public WeaponAmmunition<Bullet> getBullets() {
        return bullets;
    }

    public float getCalibre() {
        return calibre;
    }

    public void setCalibre(float calibre) {
        this.calibre = calibre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AbstractFirearm that = (AbstractFirearm) o;

        return Float.compare(that.calibre, calibre) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (calibre != +0.0f ? Float.floatToIntBits(calibre) : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "Calibre=" + calibre + " | Bullets=" +
                (bullets == null ? "0" : bullets.size());
    }
}
