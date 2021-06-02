package com.bsuir.weapons.model.weapon.melee.impl;

import com.bsuir.weapons.model.weapon.Quality;
import com.bsuir.weapons.model.weapon.melee.AbstractMeleeWeapon;

import java.util.Objects;

public class Bat extends AbstractMeleeWeapon {
    private boolean isWithSpikes;
    private String material;

    public Bat(String name, float weight, int cost, Quality quality,
               boolean isOneHanded, float length, boolean isWithSpikes, String material) {
        super(name, weight, cost, quality, isOneHanded, length);
        this.isWithSpikes = isWithSpikes;
        this.material = material;
    }

    public Bat() {
        //For XML Deserialization
    }

    public boolean isWithSpikes() {
        return isWithSpikes;
    }

    public void setWithSpikes(boolean withSpikes) {
        isWithSpikes = withSpikes;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String hit() {
        return String.format("%s bat='%s' of material=%s and length=%.2f hits!",
                isWithSpikes ? "Spiked" : "Baseball", getName(), material, getLength());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Bat bat = (Bat) o;

        if (isWithSpikes != bat.isWithSpikes) return false;
        return Objects.equals(material, bat.material);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isWithSpikes ? 1 : 0);
        result = 31 * result + (material != null ? material.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BAT { " + super.toString() +
                (isWithSpikes ? "With spikes" : "Without spikes") +
                " | Material=" + material + " }";
    }
}
