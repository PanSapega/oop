package com.bsuir.weapons.model.weapon.ammo;

import java.io.Serializable;
import java.util.*;


public class WeaponAmmunition<T> implements Iterator<T>, Serializable {
    private Deque<T> ammunition;

    public WeaponAmmunition(Deque<T> ammunition) {
        this.ammunition = ammunition;
    }

    public WeaponAmmunition() {
        //For XML
    }

    public void setAmmunition(Deque<T> ammunition) {
        this.ammunition = ammunition;
    }

    @Override
    public boolean hasNext() {
        return ammunition.size() > 0;
    }

    @Override
    public T next() {
        return ammunition.pollLast();
    }

    public Deque<T> getAmmunition() {
        return ammunition;
    }

    public void addItem(T item) {
        ammunition.addLast(item);
    }

    public void remove(T item) {
        ammunition.remove(item);
    }

    public void addItems(List<T> items) {
        for (T item : items) {
            this.ammunition.addLast(item);
        }
    }

    public int size() {
        return ammunition == null ? 0 : ammunition.size();
    }
}
