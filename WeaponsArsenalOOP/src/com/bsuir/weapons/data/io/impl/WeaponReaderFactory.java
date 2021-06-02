package com.bsuir.weapons.data.io.impl;

import com.bsuir.weapons.data.io.IOType;
import com.bsuir.weapons.data.io.Reader;
import com.bsuir.weapons.data.io.impl.reader.WeaponBinaryReader;
import com.bsuir.weapons.data.io.impl.reader.WeaponTextReader;
import com.bsuir.weapons.data.io.impl.reader.WeaponXmlReader;
import com.bsuir.weapons.model.weapon.AbstractWeapon;

public class WeaponReaderFactory {

    public static Reader<AbstractWeapon> create(IOType ioType) {
        switch (ioType) {
            case BINARY:
                return new WeaponBinaryReader();
            case XML:
                return new WeaponXmlReader();
            case TXT:
                return new WeaponTextReader();
        }
        throw new IllegalArgumentException("Invalid IO type.");
    }
}
