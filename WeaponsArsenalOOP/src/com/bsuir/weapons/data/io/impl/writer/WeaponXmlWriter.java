package com.bsuir.weapons.data.io.impl.writer;

import com.bsuir.weapons.data.io.IOType;
import com.bsuir.weapons.data.io.Writer;
import com.bsuir.weapons.model.weapon.AbstractWeapon;
import com.bsuir.weapons.model.weapon.melee.impl.Bat;
import com.bsuir.weapons.model.weapon.melee.impl.Blade;
import com.bsuir.weapons.model.weapon.ranged.impl.*;
import com.bsuir.weapons.view.AlertCreator;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class WeaponXmlWriter implements Writer<AbstractWeapon> {
    private static final List<String> WEAPON_NAMES = new ArrayList<>(
            Arrays.asList(ArrowWeapon.class.getSimpleName(), AutomaticGun.class.getSimpleName(),
                    Pistol.class.getSimpleName(), Shotgun.class.getSimpleName(), SniperRifle.class.getSimpleName(),
                    Bat.class.getSimpleName(), Blade.class.getSimpleName())
    );

    @Override
    public void write(File file, List<AbstractWeapon> weapons, IOType ioType) {
        XmlMapper mapper = new XmlMapper();
        StringBuilder xmlBuilder = new StringBuilder();
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (AbstractWeapon weapon : weapons) {
                String xmlWeapon = mapper.writeValueAsString(weapon);
                xmlBuilder.append(process(xmlWeapon));
            }
            fileWriter.write(xmlBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
            AlertCreator.createIOError();
        }
    }


    private String process(String oldStr) {
        String formattedStr = oldStr.replaceAll("bullets|arrows", "WeaponAmmunition");
        formattedStr = formattedStr.replaceAll("<ammunition><ammunition>", "<ammunition>");
        formattedStr = formattedStr.replaceAll("</ammunition></ammunition>", "</ammunition>");
        return formattedStr;
    }
}
