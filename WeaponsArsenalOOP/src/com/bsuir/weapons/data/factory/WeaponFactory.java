package com.bsuir.weapons.data.factory;

import com.bsuir.weapons.data.ResultSet;
import com.bsuir.weapons.model.weapon.AbstractWeapon;
import com.bsuir.weapons.model.weapon.Quality;
import com.bsuir.weapons.model.weapon.WeaponType;
import com.bsuir.weapons.model.weapon.melee.impl.Bat;
import com.bsuir.weapons.model.weapon.melee.impl.Blade;
import com.bsuir.weapons.model.weapon.ranged.impl.*;
import com.bsuir.weapons.view.WeaponFields;

public class WeaponFactory {

    public static AbstractWeapon create(WeaponType weaponType, ResultSet resultSet) {
        String name = resultSet.getString(WeaponFields.WEAPON_NAME);
        float weight = resultSet.getFloat(WeaponFields.WEAPON_WEIGHT);
        int cost = resultSet.getInteger(WeaponFields.WEAPON_COST);
        Quality quality = (Quality) resultSet.getObject(WeaponFields.WEAPON_QUALITY);
        if (weaponType == WeaponType.BAT || weaponType == WeaponType.BLADE) {
            boolean isOneHanded = resultSet.getBoolean(WeaponFields.WEAPON_ONE_HANDED);
            float length = resultSet.getFloat(WeaponFields.WEAPON_LENGTH);
            if (weaponType == WeaponType.BAT) {
                return new Bat(name, weight, cost, quality, isOneHanded, length,
                        resultSet.getBoolean(WeaponFields.WEAPON_SPIKES),
                        resultSet.getString(WeaponFields.WEAPON_MATERIAL));
            }
            return new Blade(name, weight, cost, quality, isOneHanded, length,
                    resultSet.getFloat(WeaponFields.WEAPON_SHARPNESS));
        } else {
            float range = resultSet.getFloat(WeaponFields.WEAPON_RANGE);
            int capacity = resultSet.getInteger(WeaponFields.WEAPON_CAPACITY);
            if (weaponType == WeaponType.ARROW_WEAPON) {
                return new ArrowWeapon(name, weight, cost, quality, range, capacity);
            }
            float calibre = resultSet.getFloat(WeaponFields.WEAPON_CALIBRE);
            switch (weaponType) {
                case PISTOL:
                    return new Pistol(name, weight, cost, quality, range, capacity, calibre,
                            resultSet.getBoolean(WeaponFields.WEAPON_ONE_HANDED));
                case SNIPER_RIFLE:
                    return new SniperRifle(name, weight, cost, quality, range, capacity, calibre,
                            resultSet.getFloat(WeaponFields.WEAPON_MULTIPLICITY));
                case AUTOMATIC_GUN:
                    return new AutomaticGun(name, weight, cost, quality, range, capacity, calibre,
                            resultSet.getFloat(WeaponFields.WEAPON_RAPIDITY));
                case SHOTGUN:
                    return new Shotgun(name, weight, cost, quality, range, capacity, calibre,
                            resultSet.getInteger(WeaponFields.WEAPON_BARRELS));
            }
        }
        throw new IllegalArgumentException("There is no such weapon.");
    }
}
