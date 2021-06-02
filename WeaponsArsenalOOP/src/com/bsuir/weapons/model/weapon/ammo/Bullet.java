package com.bsuir.weapons.model.weapon.ammo;

import java.io.Serializable;

public class Bullet implements Serializable {
    private float length;
    private float thickness;
    private boolean isArmorPiercing;

    public Bullet(float length, float thickness, boolean isArmorPiercing) {
        this.length = length;
        this.thickness = thickness;
        this.isArmorPiercing = isArmorPiercing;
    }

    public Bullet() {
        //For XML Deserialization
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getThickness() {
        return thickness;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }

    public boolean isArmorPiercing() {
        return isArmorPiercing;
    }

    public void setArmorPiercing(boolean armorPiercing) {
        isArmorPiercing = armorPiercing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bullet bullet = (Bullet) o;

        if (Float.compare(bullet.length, length) != 0) return false;
        if (Float.compare(bullet.thickness, thickness) != 0) return false;
        return isArmorPiercing == bullet.isArmorPiercing;
    }

    @Override
    public int hashCode() {
        int result = (length != +0.0f ? Float.floatToIntBits(length) : 0);
        result = 31 * result + (thickness != +0.0f ? Float.floatToIntBits(thickness) : 0);
        result = 31 * result + (isArmorPiercing ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return (isArmorPiercing ? "Armor-piercing" : "Typical") +
                " bullet with length=" + length + " and thickness=" + thickness;
    }
}
