package com.bsuir.weapons.data.repository;

class IdGenerator {
    private static int id = 0;

    public synchronized static int generate() {
        return id++;
    }
}
