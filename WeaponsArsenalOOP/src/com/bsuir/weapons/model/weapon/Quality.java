package com.bsuir.weapons.model.weapon;

public enum Quality {
    FACTORY_NEW("Factory new"),
    MINIMAL_WEAR("Minimal wear"),
    FIELD_TESTED("Field tested"),
    WELL_WORN("Well worn"),
    BATTLE_SCARRED("Battle scarred");

    private String value;

    Quality(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Quality getType(String value) {
        for (Quality quality : values()) {
            if (quality.value.equals(value)) {
                return quality;
            }
        }
        return FACTORY_NEW;
    }
}
