package com.bsuir.weapons.model.weapon.ranged.impl;

import com.bsuir.weapons.model.weapon.Quality;
import com.bsuir.weapons.model.weapon.ranged.AbstractFirearm;
import com.bsuir.weapons.model.weapon.ranged.FiringMode;

public class Pistol extends AbstractFirearm {
    private boolean isOneHanded;

    public Pistol(String name, float weight, int cost, Quality quality, float range,
                  int capacity, float calibre, boolean isOneHanded) {
        super(name, weight, cost, quality, range, capacity, FiringMode.SINGLE_SHOT, calibre);
        this.isOneHanded = isOneHanded;
    }

    public Pistol() {
        //For XML Deserialization
    }

    public boolean isOneHanded() {
        return isOneHanded;
    }

    public void setOneHanded(boolean oneHanded) {
        isOneHanded = oneHanded;
    }

    @Override
    public String shoot() {
        String message;
        if (getBullets() != null && getBullets().hasNext()) {
            message = String.format("%s pistol='%s' shoots a bullet={%s}!", isOneHanded ?
                    "One-handed" : "Dual", getName(), getBullets().next());
        } else {
            message = String.format("%s pistol='%s' tried to shoot but there is no bullet :(",
                    isOneHanded ? "One-handed" : "Dual", getName());
        }
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Pistol pistol = (Pistol) o;

        return isOneHanded == pistol.isOneHanded;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isOneHanded ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return (isOneHanded ? "ONE-HANDED PISTOL"
                : "DUAL PISTOLS") + " { " + super.toString() + " }";
    }
}
