package com.bsuir.weapons.model.weapon.ranged.impl;

import com.bsuir.weapons.model.weapon.Quality;
import com.bsuir.weapons.model.weapon.ranged.AbstractFirearm;
import com.bsuir.weapons.model.weapon.ranged.FiringMode;

public class AutomaticGun extends AbstractFirearm {
    private float rapidity;

    public AutomaticGun(String name, float weight, int cost, Quality quality, float range,
                        int capacity, float calibre, float rapidity) {
        super(name, weight, cost, quality, range, capacity, FiringMode.AUTO, calibre);
        this.rapidity = rapidity;
    }

    public AutomaticGun() {
        //For XML Deserialization
    }

    public float getRapidity() {
        return rapidity;
    }

    public void setRapidity(float rapidity) {
        this.rapidity = rapidity;
    }

    @Override
    public String shoot() {
        String message;
        if (getBullets() != null && getBullets().hasNext()) {
            message = String.format("AutomaticGun='%s' shoots a bullet={%s}!",
                    getName(), getBullets().next());
        } else {
            message = String.format("AutomaticGun='%s' tried to shoot but there is no bullet :(", getName());
        }
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AutomaticGun that = (AutomaticGun) o;

        return Float.compare(that.rapidity, rapidity) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (rapidity != +0.0f ? Float.floatToIntBits(rapidity) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AUTOMATIC GUN { " + super.toString() +
            " | Rapidity=" + rapidity + " }";
    }
}
