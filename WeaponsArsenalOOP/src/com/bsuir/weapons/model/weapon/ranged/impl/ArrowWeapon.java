package com.bsuir.weapons.model.weapon.ranged.impl;

import com.bsuir.weapons.model.weapon.ammo.WeaponAmmunition;
import com.bsuir.weapons.model.weapon.ammo.Arrow;
import com.bsuir.weapons.model.weapon.Quality;
import com.bsuir.weapons.model.weapon.ranged.AbstractRangedWeapon;
import com.bsuir.weapons.model.weapon.ranged.FiringMode;

import java.util.Deque;
import java.util.Objects;

public class ArrowWeapon extends AbstractRangedWeapon {
    private WeaponAmmunition<Arrow> arrows;

    public ArrowWeapon(String name, float weight, int cost, Quality quality,
                       float range, int capacity) {
        super(name, weight, cost, quality, range, capacity, FiringMode.SINGLE_SHOT);
    }

    public ArrowWeapon() {
        //For XML Deserialization
    }

    public WeaponAmmunition<Arrow> getArrows() {
        return arrows;
    }

    public void setArrows(WeaponAmmunition<Arrow> arrows) {
        this.arrows = arrows;
    }

    public void setArrowsDeque(Deque<Arrow> arrows) {
        this.arrows = new WeaponAmmunition<>(arrows);
    }

    @Override
    public String shoot() {
        String message;
        if (arrows != null && arrows.hasNext()) {
            message = String.format("Arrow Weapon='%s' shoots with arrow=%s", getName(), arrows.next());
        } else {
            message = String.format("Arrow Weapon='%s' trying to shoot but there is no arrow", getName());
        }
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ArrowWeapon that = (ArrowWeapon) o;

        return Objects.equals(arrows, that.arrows);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (arrows != null ? arrows.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ARROW WEAPON { " + super.toString() +
                " Arrows = " +
                (arrows == null ? "0" : arrows.size())
                + " }";
    }
}
