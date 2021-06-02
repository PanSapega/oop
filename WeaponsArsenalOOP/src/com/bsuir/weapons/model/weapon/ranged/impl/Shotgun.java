package com.bsuir.weapons.model.weapon.ranged.impl;

import com.bsuir.weapons.model.weapon.Quality;
import com.bsuir.weapons.model.weapon.ranged.AbstractFirearm;
import com.bsuir.weapons.model.weapon.ranged.FiringMode;

public class Shotgun extends AbstractFirearm {
    private int barrelsCount;

    public Shotgun(String name, float weight, int cost, Quality quality, float range,
                   int capacity, float calibre, int barrelsCount) {
        super(name, weight, cost, quality, range, capacity, FiringMode.PUMP, calibre);
        this.barrelsCount = barrelsCount;
    }

    public Shotgun() {
        //For XML Deserialization
    }

    public int getBarrelsCount() {
        return barrelsCount;
    }

    public void setBarrelsCount(int barrelsCount) {
        this.barrelsCount = barrelsCount;
    }

    @Override
    public String shoot() {
        String message;
        if (getBullets() != null && getBullets().hasNext()) {
            message = String.format("Shotgun='%s' shoots a bullet={%s}!",
                    getName(), getBullets().next());
        } else {
            message = String.format("Shotgun='%s' tried to shoot but there is no bullet :(", getName());
        }
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Shotgun shotgun = (Shotgun) o;

        return barrelsCount == shotgun.barrelsCount;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + barrelsCount;
        return result;
    }

    @Override
    public String toString() {
        return "SHOTGUN { "+ super.toString() +
                " | Barrels count=" + barrelsCount + " }";
    }
}
