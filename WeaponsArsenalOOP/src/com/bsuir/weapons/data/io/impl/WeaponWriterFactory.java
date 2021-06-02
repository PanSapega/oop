package com.bsuir.weapons.data.io.impl;

import com.bsuir.weapons.data.io.IOType;
import com.bsuir.weapons.data.io.Writer;
import com.bsuir.weapons.data.io.impl.writer.WeaponBinaryWriter;
import com.bsuir.weapons.data.io.impl.writer.WeaponTextWriter;
import com.bsuir.weapons.data.io.impl.writer.WeaponXmlWriter;
import com.bsuir.weapons.model.weapon.AbstractWeapon;

public class WeaponWriterFactory {

    public static Writer<AbstractWeapon> create(IOType ioType) {
        switch (ioType) {
            case BINARY:
                return new WeaponBinaryWriter();
            case XML:
                return new WeaponXmlWriter();
            case TXT:
                return new WeaponTextWriter();
        }
        throw new IllegalArgumentException("Invalid IO type.");
    }
}
