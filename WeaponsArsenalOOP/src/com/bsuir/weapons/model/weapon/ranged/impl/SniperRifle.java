package com.bsuir.weapons.model.weapon.ranged.impl;

import com.bsuir.weapons.model.weapon.Quality;
import com.bsuir.weapons.model.weapon.ranged.AbstractFirearm;
import com.bsuir.weapons.model.weapon.ranged.FiringMode;

public class SniperRifle extends AbstractFirearm {
    private float sightMultiplicity;

    public SniperRifle(String name, float weight, int cost, Quality quality, float range,
                       int capacity, float calibre, float sightMultiplicity) {
        super(name, weight, cost, quality, range, capacity, FiringMode.SINGLE_SHOT, calibre);
        this.sightMultiplicity = sightMultiplicity;
    }

    public SniperRifle() {
        //For XML Deserialization
    }

    public float getSightMultiplicity() {
        return sightMultiplicity;
    }

    public void setSightMultiplicity(float sightMultiplicity) {
        this.sightMultiplicity = sightMultiplicity;
    }

    @Override
    public String shoot() {
        String message;
        if (getBullets() != null && getBullets().hasNext()) {
            message = String.format("Sniper rifle='%s' shoots a bullet={%s}!",
                    getName(), getBullets().next());
        } else {
            message = String.format("Sniper rifle='%s' tried to shoot but there is no bullet :(", getName());
        }
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SniperRifle that = (SniperRifle) o;

        return Float.compare(that.sightMultiplicity, sightMultiplicity) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (sightMultiplicity != +0.0f ? Float.floatToIntBits(sightMultiplicity) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SNIPER RIFLE { " + super.toString() +
                " | Sight multiplicity="
                + sightMultiplicity + " }";
    }
}
