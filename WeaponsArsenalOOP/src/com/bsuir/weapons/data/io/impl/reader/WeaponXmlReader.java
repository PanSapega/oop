package com.bsuir.weapons.data.io.impl.reader;

import com.bsuir.weapons.data.io.IOType;
import com.bsuir.weapons.data.io.Reader;
import com.bsuir.weapons.model.weapon.ammo.Arrow;
import com.bsuir.weapons.model.weapon.ammo.Bullet;
import com.bsuir.weapons.model.weapon.ammo.WeaponAmmunition;
import com.bsuir.weapons.model.weapon.AbstractWeapon;
import com.bsuir.weapons.model.weapon.melee.impl.Bat;
import com.bsuir.weapons.model.weapon.melee.impl.Blade;
import com.bsuir.weapons.model.weapon.ranged.AbstractFirearm;
import com.bsuir.weapons.model.weapon.ranged.impl.*;
import com.bsuir.weapons.view.AlertCreator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WeaponXmlReader  implements Reader<AbstractWeapon> {
    private static final List<String> WEAPON_NAMES = new ArrayList<>(
            Arrays.asList(ArrowWeapon.class.getSimpleName(), AutomaticGun.class.getSimpleName(),
                    Pistol.class.getSimpleName(), Shotgun.class.getSimpleName(), SniperRifle.class.getSimpleName(),
                    Bat.class.getSimpleName(), Blade.class.getSimpleName())
    );

    @Override
    public List<AbstractWeapon> read(File file, IOType ioType) {
        List<AbstractWeapon> weapons = new ArrayList<>();
        XmlMapper mapper = new XmlMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setDefaultUseWrapper(false);
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            StringBuilder xmlBuilder = new StringBuilder();
            String xmlFileContent = fileReader.readLine();
            int minIndex = getEndIndex(xmlFileContent);
            String currentStr = xmlFileContent;
            while (minIndex > -1) {
                int startIndex = 0;
                xmlBuilder.append(currentStr, startIndex, minIndex);
                for (int i = minIndex; i < currentStr.length(); i++) {
                    xmlBuilder.append(currentStr.charAt(i));
                    if (currentStr.charAt(i) == '>') {
                        AbstractWeapon weapon = readXMLWeapon(mapper, xmlBuilder.toString());
                        if (xmlBuilder.toString().contains(WeaponAmmunition.class.getSimpleName() + "><ammunition>")) {
                            createAmmo(weapon, currentStr, mapper);
                        }
                        weapons.add(weapon);
                        startIndex = ++i;
                        xmlBuilder = new StringBuilder();
                        break;
                    }
                }
                currentStr = currentStr.substring(startIndex);
                minIndex = getEndIndex(currentStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
            AlertCreator.createIOError();
        }
        return weapons;
    }

    private int getEndIndex(String nextStr) {
        int minIndex = Integer.MAX_VALUE;
        for (String str : WEAPON_NAMES) {
            int currentIndex = nextStr.indexOf("/" + str);
            if (currentIndex < minIndex && currentIndex != -1) {
                minIndex = currentIndex;
            }
        }
        if (minIndex == Integer.MAX_VALUE) {
            minIndex = -1;
        }
        return minIndex;
    }

    private AbstractWeapon readXMLWeapon(XmlMapper xmlMapper, String xmlString) throws IOException {
        if (xmlString.contains(ArrowWeapon.class.getSimpleName())) {
            return xmlMapper.readValue(xmlString, ArrowWeapon.class);
        }
        if (xmlString.contains(AutomaticGun.class.getSimpleName())) {
            return xmlMapper.readValue(xmlString, AutomaticGun.class);
        }
        if (xmlString.contains(Pistol.class.getSimpleName())) {
            return xmlMapper.readValue(xmlString, Pistol.class);
        }
        if (xmlString.contains(Shotgun.class.getSimpleName())) {
            return xmlMapper.readValue(xmlString, Shotgun.class);
        }
        if (xmlString.contains(SniperRifle.class.getSimpleName())) {
            return xmlMapper.readValue(xmlString, SniperRifle.class);
        }
        if (xmlString.contains(Bat.class.getSimpleName())) {
            return xmlMapper.readValue(xmlString, Bat.class);
        }
        if (xmlString.contains(Blade.class.getSimpleName())) {
            return xmlMapper.readValue(xmlString, Blade.class);
        }
        return null;
    }

    private void createAmmo(AbstractWeapon weapon, String currentStr, XmlMapper mapper) throws IOException {
        int ammoBeginIndex = currentStr.indexOf("<WeaponAmmunition>");
        int ammoLastIndex = currentStr.indexOf("</WeaponAmmunition") + 19;
        String ammoXML = currentStr.substring(ammoBeginIndex, ammoLastIndex);
        WeaponAmmunition<Object> weaponAmmunition = mapper.readValue(ammoXML, WeaponAmmunition.class);
        if (weapon instanceof ArrowWeapon) {
            Deque<Arrow> arrows = new ArrayDeque<>();
            for (Object object : weaponAmmunition.getAmmunition()) {
                LinkedHashMap linkedHashMap = (LinkedHashMap) object;
                Arrow arrow = new Arrow();
                arrow.setLength(Float.parseFloat((String) linkedHashMap.get("length")));
                arrow.setPoisoned(Boolean.parseBoolean((String) linkedHashMap.get("poisoned")));
                arrows.add(arrow);
            }
            ((ArrowWeapon) weapon).setArrowsDeque(arrows);
        } else if (weapon instanceof AbstractFirearm) {
            Deque<Bullet> bullets = new ArrayDeque<>();
            for (Object object : weaponAmmunition.getAmmunition()) {
                LinkedHashMap linkedHashMap = (LinkedHashMap) object;
                Bullet bullet = new Bullet();
                bullet.setLength(Float.parseFloat((String) linkedHashMap.get("length")));
                bullet.setThickness(Float.parseFloat((String) linkedHashMap.get("thickness")));
                bullet.setArmorPiercing(Boolean.parseBoolean((String) linkedHashMap.get("armorPiercing")));
                bullets.add(bullet);
            }
            ((AbstractFirearm) weapon).setBulletsDeque(bullets);
        }
    }
}
