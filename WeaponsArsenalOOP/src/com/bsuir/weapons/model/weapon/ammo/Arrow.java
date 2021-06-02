package com.bsuir.weapons.model.weapon.ammo;

import java.io.Serializable;

public class Arrow implements Serializable {
    private float length;
    private boolean isPoisoned;

    public Arrow(float length, boolean isPoisoned) {
        this.length = length;
        this.isPoisoned = isPoisoned;
    }

    public Arrow() {
        //For XML Deserialization
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public boolean isPoisoned() {
        return isPoisoned;
    }

    public void setPoisoned(boolean poisoned) {
        isPoisoned = poisoned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Arrow arrow = (Arrow) o;

        if (Float.compare(arrow.length, length) != 0) return false;
        return isPoisoned == arrow.isPoisoned;
    }

    @Override
    public int hashCode() {
        int result = (length != +0.0f ? Float.floatToIntBits(length) : 0);
        result = 31 * result + (isPoisoned ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return (isPoisoned ? "Poisoned" : "Typical") +
                " arrow with length=" + length;
    }
}
