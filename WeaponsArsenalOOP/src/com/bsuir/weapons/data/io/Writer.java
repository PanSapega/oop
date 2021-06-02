package com.bsuir.weapons.data.io;

import com.bsuir.weapons.model.Identifiable;

import java.io.File;
import java.util.List;

public interface Writer<T extends Identifiable> {
    void write(File file, List<T> objects, IOType ioType);
}
