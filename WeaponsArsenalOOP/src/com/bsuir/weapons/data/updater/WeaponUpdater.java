package com.bsuir.weapons.data.updater;

import com.bsuir.weapons.data.ResultSet;
import com.bsuir.weapons.model.weapon.AbstractWeapon;
import com.bsuir.weapons.model.weapon.Quality;
import com.bsuir.weapons.model.weapon.melee.AbstractMeleeWeapon;
import com.bsuir.weapons.model.weapon.melee.impl.Bat;
import com.bsuir.weapons.model.weapon.melee.impl.Blade;
import com.bsuir.weapons.model.weapon.ranged.AbstractFirearm;
import com.bsuir.weapons.model.weapon.ranged.AbstractRangedWeapon;
import com.bsuir.weapons.model.weapon.ranged.impl.*;
import com.bsuir.weapons.view.WeaponFields;

public class WeaponUpdater {

    public static void update(AbstractWeapon weapon, ResultSet resultSet) {
        weapon.setName(resultSet.getString(WeaponFields.WEAPON_NAME));
        weapon.setWeight(resultSet.getFloat(WeaponFields.WEAPON_WEIGHT));
        weapon.setCost(resultSet.getInteger(WeaponFields.WEAPON_COST));
        weapon.setQuality((Quality) resultSet.getObject(WeaponFields.WEAPON_QUALITY));
        if (weapon instanceof AbstractMeleeWeapon) {
            updateMeleeWeapons(weapon, resultSet);
        } else if (weapon instanceof AbstractRangedWeapon) {
            updateRangedWeapons(weapon, resultSet);
        }
    }

    public static void updateMeleeWeapons(AbstractWeapon weapon, ResultSet resultSet) {
        AbstractMeleeWeapon meleeWeapon = (AbstractMeleeWeapon) weapon;
        meleeWeapon.setOneHanded(resultSet.getBoolean(WeaponFields.WEAPON_ONE_HANDED));
        meleeWeapon.setLength(resultSet.getFloat(WeaponFields.WEAPON_LENGTH));
        if (meleeWeapon instanceof Bat) {
            Bat bat = (Bat) weapon;
            bat.setWithSpikes(resultSet.getBoolean(WeaponFields.WEAPON_SPIKES));
            bat.setMaterial(resultSet.getString(WeaponFields.WEAPON_MATERIAL));
        } else if (meleeWeapon instanceof Blade) {
            Blade blade = (Blade) weapon;
            blade.setSharpness(resultSet.getFloat(WeaponFields.WEAPON_SHARPNESS));
        }
    }

    public static void updateRangedWeapons(AbstractWeapon weapon, ResultSet resultSet) {
        AbstractRangedWeapon rangedWeapon = (AbstractRangedWeapon) weapon;
        rangedWeapon.setRange(resultSet.getFloat(WeaponFields.WEAPON_RANGE));
        rangedWeapon.setCapacity(resultSet.getInteger(WeaponFields.WEAPON_CAPACITY));
        if (!(rangedWeapon instanceof ArrowWeapon)) {
            AbstractFirearm firearm = (AbstractFirearm) weapon;
            firearm.setCalibre(resultSet.getFloat(WeaponFields.WEAPON_CALIBRE));
            if (firearm instanceof AutomaticGun) {
                AutomaticGun automaticGun = (AutomaticGun) weapon;
                automaticGun.setRapidity(resultSet.getFloat(WeaponFields.WEAPON_RAPIDITY));
            } else if (firearm instanceof Shotgun) {
                Shotgun shotgun = (Shotgun) weapon;
                shotgun.setBarrelsCount(resultSet.getInteger(WeaponFields.WEAPON_BARRELS));
            } else if (firearm instanceof Pistol) {
                Pistol pistol = (Pistol) weapon;
                pistol.setOneHanded(resultSet.getBoolean(WeaponFields.WEAPON_ONE_HANDED));
            } else if (firearm instanceof SniperRifle) {
                SniperRifle sniperRifle = (SniperRifle) weapon;
                sniperRifle.setSightMultiplicity(resultSet.getFloat(WeaponFields.WEAPON_MULTIPLICITY));
            }
        }
    }
}
