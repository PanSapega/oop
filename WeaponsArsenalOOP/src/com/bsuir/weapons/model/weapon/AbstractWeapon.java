package com.bsuir.weapons.model.weapon;

import com.bsuir.weapons.model.Identifiable;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractWeapon implements Identifiable, Serializable {
    private String name;
    private float weight;
    private int cost;
    private Quality quality;
    private long id;

    public AbstractWeapon(String name, float weight, int cost, Quality quality) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
        this.quality = quality;
    }

    public AbstractWeapon() {
        //For xml Deserialization
    }

    public String getName() {
        return name;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractWeapon that = (AbstractWeapon) o;

        if (Float.compare(that.weight, weight) != 0) return false;
        if (cost != that.cost) return false;
        if (!Objects.equals(name, that.name)) return false;
        return quality == that.quality;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
        result = 31 * result + cost;
        result = 31 * result + (quality != null ? quality.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Name='" + name +
                "' | Cost=" + cost +
                " | Quality=" + quality +
                " | Weight=" + weight + " | ";
    }
}
