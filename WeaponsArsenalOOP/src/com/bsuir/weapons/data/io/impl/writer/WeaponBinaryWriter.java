package com.bsuir.weapons.data.io.impl.writer;

import com.bsuir.weapons.data.io.IOType;
import com.bsuir.weapons.data.io.Writer;
import com.bsuir.weapons.model.weapon.AbstractWeapon;
import com.bsuir.weapons.view.AlertCreator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class WeaponBinaryWriter implements Writer<AbstractWeapon> {

    @Override
    public void write(File file, List<AbstractWeapon> weapons, IOType ioType) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(weapons);
        } catch (IOException e) {
            e.printStackTrace();
            AlertCreator.createIOError();
        }
    }
}
