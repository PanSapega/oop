package com.bsuir.weapons.model.weapon.ranged;

public enum FiringMode {
    AUTO("Auto"),
    SEMI_AUTO("Semi-auto"),
    SINGLE_SHOT("Single shot"),
    PUMP("Pump");

    private String value;

    FiringMode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
