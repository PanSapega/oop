package com.bsuir.weapons.view;

import java.util.Arrays;
import java.util.List;

public final class ViewConstants {
    public static final String ALL_WEAPON = "All weapon";
    public static final String MELEE_WEAPON = "Melee weapon";
    public static final String RANGED_WEAPON = "Ranged weapon";
    public static final String FIREARM = "Firearm";
    public static final String BAT = "Bat";
    public static final String BLADE = "Blade";
    public static final String ARROW_SHOOTING_WEAPON = "Arrow weapon";
    public static final String AUTOMATIC_GUN = "Automatic gun";
    public static final String PISTOL = "Pistol";
    public static final String SHOTGUN = "Shotgun";
    public static final String SNIPER_RIFLE = "Sniper rifle";

    public static final List<String> WEAPON_SELECTOR_STRINGS = Arrays.asList(
            PISTOL, AUTOMATIC_GUN, ARROW_SHOOTING_WEAPON,
            SHOTGUN, SNIPER_RIFLE, BAT, BLADE);
    public static final String DEFAULT_WEAPON_SELECTOR_STRING = PISTOL;

    public static final List<String> VIEW_SELECTOR_STRINGS = Arrays.asList(
            ALL_WEAPON, MELEE_WEAPON, RANGED_WEAPON, FIREARM, BAT, BLADE,
            ARROW_SHOOTING_WEAPON, AUTOMATIC_GUN, PISTOL, SHOTGUN, SNIPER_RIFLE);
    static {
        VIEW_SELECTOR_STRINGS.replaceAll(str -> str + "s");
    }
    public static final String DEFAULT_SELECTOR_STRING = VIEW_SELECTOR_STRINGS.get(0);


    public static final String ADD_WEAPON_TITLE = "Adding weapon";
    public static final String EDIT_WEAPON_TITLE = "Editing weapon";
    public static final String ADD_BULLET_TITLE = "Adding bullets";
    public static final String EDIT_BULLET_TITLE = "Editing bullets";
    public static final String ADD_ARROW_TITLE = "Adding arrows";
    public static final String EDIT_ARROW_TITLE = "Editing arrows";
    public static final String ADD_WEAPON_HEADER = "Choose weapon type";
    public static final String AMMO_TITLE = "Ammo";

    public static final String INT_TYPE = "int";
    public static final String FLOAT_TYPE = "float";
    public static final String STRING_TYPE = "str";

    public static final String CONFIRM_TITLE = "Confirm";
    public static final String CONFIRM_CONTENT = "Are you sure?";
    public static final String INVALID_INPUT_TITLE = "Invalid input";
    public static final String INVALID_INPUT_CONTENT = "Invalid input(field must be filled, less than 25 symbols, " +
            "type should be correct). Try again.";
    public static final String NO_AMMO_TITLE = "Invalid weapon";
    public static final String NO_AMMO_CONTENT = "There weapon is goes without ammo.";
    public static final String NOT_SELECTED_TITLE = "No weapon";
    public static final String NOT_SELECTED_CONTENT = "Weapon item is not selected. Please, select.";
    public static final String IO_ERROR_TITLE = "IO error";
    public static final String IO_ERROR_CONTENT = "Input/Output error was occurred.";
    public static final String HELP_TITLE = "Help";
    public static final String HELP_HEADER = "This program was developed by Alexander Voroshilov, group 951007.";
    public static final String HELP_CONTENT = "Here you see weapon arsenal. You can create/edit/remove weapons and ammo. " +
            "You can shoot from weapon or just hit. To save/load file with weapon arsenal press 'File'.";
}
