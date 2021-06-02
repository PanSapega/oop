package com.bsuir.weapons.model.weapon;

import static com.bsuir.weapons.view.ViewConstants.VIEW_SELECTOR_STRINGS;

public enum WeaponType {
    ALL(VIEW_SELECTOR_STRINGS.get(0)),
    MELEE(VIEW_SELECTOR_STRINGS.get(1)),
    RANGED(VIEW_SELECTOR_STRINGS.get(2)),
    FIREARM(VIEW_SELECTOR_STRINGS.get(3)),
    BAT(VIEW_SELECTOR_STRINGS.get(4)),
    BLADE(VIEW_SELECTOR_STRINGS.get(5)),
    ARROW_WEAPON(VIEW_SELECTOR_STRINGS.get(6)),
    AUTOMATIC_GUN(VIEW_SELECTOR_STRINGS.get(7)),
    PISTOL(VIEW_SELECTOR_STRINGS.get(8)),
    SHOTGUN(VIEW_SELECTOR_STRINGS.get(9)),
    SNIPER_RIFLE(VIEW_SELECTOR_STRINGS.get(10));

    private String value;

    WeaponType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static WeaponType getType(String value) {
        for (WeaponType weaponType : values()) {
            if (weaponType.value.equals(value)) {
                return weaponType;
            }
        }
        return ALL;
    }
}
