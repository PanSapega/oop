package com.bsuir.weapons.data.io.impl.reader;

import com.bsuir.weapons.data.io.IOType;
import com.bsuir.weapons.data.io.Reader;
import com.bsuir.weapons.model.weapon.AbstractWeapon;
import com.bsuir.weapons.view.AlertCreator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class WeaponBinaryReader implements Reader<AbstractWeapon> {

    @Override
    public List<AbstractWeapon> read(File file, IOType ioType) {
        List<AbstractWeapon> weapons = new ArrayList<>();
        try (ObjectInputStream objectOutputStream = new ObjectInputStream(new FileInputStream(file))) {
            weapons = (List<AbstractWeapon>) objectOutputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            AlertCreator.createIOError();
        }
        return weapons;
    }
}
