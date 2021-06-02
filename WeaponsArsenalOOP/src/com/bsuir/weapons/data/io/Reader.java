package com.bsuir.weapons.data.io;

import com.bsuir.weapons.model.Identifiable;

import java.io.File;
import java.util.List;

public interface Reader<T extends Identifiable> {
    List<T> read(File file, IOType ioType);
}
