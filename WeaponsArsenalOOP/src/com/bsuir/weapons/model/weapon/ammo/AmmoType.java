package com.bsuir.weapons.model.weapon.ammo;

public enum AmmoType {
    BULLET("Bullet"),
    ARROW("Arrow");

    private String value;

    AmmoType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
