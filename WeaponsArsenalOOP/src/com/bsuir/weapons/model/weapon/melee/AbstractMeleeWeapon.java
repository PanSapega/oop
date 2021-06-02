package com.bsuir.weapons.model.weapon.melee;

import com.bsuir.weapons.model.weapon.AbstractWeapon;
import com.bsuir.weapons.model.weapon.Quality;

public abstract class AbstractMeleeWeapon extends AbstractWeapon implements Hittable {
    private boolean isOneHanded;
    private float length;

    public AbstractMeleeWeapon(String name, float weight, int cost, Quality quality, boolean isOneHanded, float length) {
        super(name, weight, cost, quality);
        this.isOneHanded = isOneHanded;
        this.length = length;
    }

    public AbstractMeleeWeapon() {
        //For xml Deserialization
    }

    public boolean getOneHanded() {
        return isOneHanded;
    }

    public void setOneHanded(boolean oneHanded) {
        this.isOneHanded = oneHanded;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AbstractMeleeWeapon that = (AbstractMeleeWeapon) o;

        if (isOneHanded != that.isOneHanded) return false;
        return Float.compare(that.length, length) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isOneHanded ? 1 : 0);
        result = 31 * result + (length != +0.0f ? Float.floatToIntBits(length) : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + (isOneHanded ?
                " One-handed" : " Two-handed") +
                " | Length=" + length + " | ";
    }
}
