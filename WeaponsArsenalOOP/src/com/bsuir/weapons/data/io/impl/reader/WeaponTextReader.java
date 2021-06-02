package com.bsuir.weapons.data.io.impl.reader;

import com.bsuir.weapons.data.ResultSet;
import com.bsuir.weapons.data.factory.WeaponFactory;
import com.bsuir.weapons.data.io.IOType;
import com.bsuir.weapons.data.io.Reader;
import com.bsuir.weapons.model.weapon.ammo.Arrow;
import com.bsuir.weapons.model.weapon.ammo.Bullet;
import com.bsuir.weapons.model.weapon.AbstractWeapon;
import com.bsuir.weapons.model.weapon.Quality;
import com.bsuir.weapons.model.weapon.WeaponType;
import com.bsuir.weapons.model.weapon.ranged.AbstractFirearm;
import com.bsuir.weapons.model.weapon.ranged.impl.ArrowWeapon;
import com.bsuir.weapons.view.AlertCreator;
import com.bsuir.weapons.view.WeaponFields;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WeaponTextReader implements Reader<AbstractWeapon> {

    @Override
    public List<AbstractWeapon> read(File file, IOType ioType) {
        List<AbstractWeapon> weapons = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            Map<String, Object> map = new HashMap<>();
            StringBuilder ammoStr = null;
            String str = bufferedReader.readLine();
            WeaponType weaponType = null;
            while (str != null) {
                String[] pair = str.split(":");
                if (pair.length > 1) {
                    map.put(pair[0], getValueByKey(pair[0], pair[1]));
                } else {
                    if (str.contains("->")) {
                        String weaponTypeStr = str.split("->")[1];
                        weaponType = WeaponType.getType(weaponTypeStr);
                    } else if (str.contains("{")) {
                        ammoStr = new StringBuilder();
                        while (!(str = bufferedReader.readLine()).contains("}")) {
                            ammoStr.append(str).append('\n');
                        }
                    }
                }
                str = bufferedReader.readLine();
                if (str != null && str.isEmpty()) {
                    AbstractWeapon weapon = WeaponFactory.create(weaponType, new ResultSet(map));
                    weapons.add(weapon);
                    if (ammoStr != null) {
                        switch (weaponType) {
                            case SNIPER_RIFLE:
                            case FIREARM:
                            case SHOTGUN:
                            case AUTOMATIC_GUN:
                            case PISTOL:
                                List<Bullet> bullets = createBullets(ammoStr.toString());
                                ((AbstractFirearm) weapon).setBulletsDeque(new ArrayDeque<>(bullets));
                                break;
                            case ARROW_WEAPON:
                                List<Arrow> arrows = createArrows(ammoStr.toString());
                                ((ArrowWeapon) weapon).setArrowsDeque(new ArrayDeque<>(arrows));
                                break;
                        }
                    }
                    ammoStr = null;
                }
            }
        } catch (IOException e) {
            AlertCreator.createIOError();
        }
        return weapons;
    }

    private Object getValueByKey(String key, String value) {
        switch (key) {
            case WeaponFields.WEAPON_ONE_HANDED:
            case WeaponFields.WEAPON_SPIKES:
                return Boolean.valueOf(value);
            case WeaponFields.WEAPON_QUALITY:
                return Quality.getType(value);
            case WeaponFields.WEAPON_NAME:
            case WeaponFields.WEAPON_MATERIAL:
                return value;
            case WeaponFields.WEAPON_BARRELS:
            case WeaponFields.WEAPON_CAPACITY:
            case WeaponFields.WEAPON_COST:
                return Integer.valueOf(value);
        }
        return Float.valueOf(value);
    }

    private List<Bullet> createBullets(String ammoText) {
        List<Bullet> bullets = new ArrayList<>();
        String[] newStrings = ammoText.split("\n");
        for (int i = 0; i < newStrings.length; i += 3) {
            Bullet bullet = new Bullet();
            String lengthStr = newStrings[i].split(":")[1];
            bullet.setLength(Float.parseFloat(lengthStr));
            String thicknessStr = newStrings[i + 1].split(":")[1];
            bullet.setThickness(Float.parseFloat(thicknessStr));
            String armorPiercingStr = newStrings[i + 2].split(":")[1];
            bullet.setArmorPiercing(Boolean.parseBoolean(armorPiercingStr));
            bullets.add(bullet);
        }
        return bullets;
    }

    private List<Arrow> createArrows(String ammoText) {
        List<Arrow> arrows = new ArrayList<>();
        String[] newStrings = ammoText.split("\n");
        for (int i = 0; i < newStrings.length; i += 2) {
            Arrow arrow = new Arrow();
            String poisonedStr = newStrings[i].split(":")[1];
            arrow.setPoisoned(Boolean.parseBoolean(poisonedStr));
            String lengthStr = newStrings[i + 1].split(":")[1];
            arrow.setLength(Float.parseFloat(lengthStr));
            arrows.add(arrow);
        }
        return arrows;
    }
}
