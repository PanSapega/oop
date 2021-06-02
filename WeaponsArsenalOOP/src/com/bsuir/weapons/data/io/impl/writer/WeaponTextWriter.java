package com.bsuir.weapons.data.io.impl.writer;

import com.bsuir.weapons.data.io.IOType;
import com.bsuir.weapons.data.io.Writer;
import com.bsuir.weapons.model.weapon.ammo.Arrow;
import com.bsuir.weapons.model.weapon.ammo.Bullet;
import com.bsuir.weapons.model.weapon.ammo.WeaponAmmunition;
import com.bsuir.weapons.model.weapon.AbstractWeapon;
import com.bsuir.weapons.model.weapon.melee.AbstractMeleeWeapon;
import com.bsuir.weapons.model.weapon.melee.impl.Bat;
import com.bsuir.weapons.model.weapon.melee.impl.Blade;
import com.bsuir.weapons.model.weapon.ranged.AbstractFirearm;
import com.bsuir.weapons.model.weapon.ranged.AbstractRangedWeapon;
import com.bsuir.weapons.model.weapon.ranged.impl.*;
import com.bsuir.weapons.view.AlertCreator;
import com.bsuir.weapons.view.WeaponFields;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WeaponTextWriter implements Writer<AbstractWeapon> {

    @Override
    public void write(File file, List<AbstractWeapon> weapons, IOType ioType) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (AbstractWeapon weapon : weapons) {
                bufferedWriter.write("Type->" + getViewClassName(weapon.getClass().getSimpleName()) + '\n');
                bufferedWriter.write(WeaponFields.WEAPON_NAME + ':' + weapon.getName() + '\n');
                bufferedWriter.write(WeaponFields.WEAPON_WEIGHT + ':' + weapon.getWeight() + '\n');
                bufferedWriter.write(WeaponFields.WEAPON_COST + ':' + weapon.getCost() + '\n');
                bufferedWriter.write(WeaponFields.WEAPON_QUALITY + ':' + weapon.getQuality() + '\n');
                if (weapon instanceof AbstractMeleeWeapon) {
                    AbstractMeleeWeapon meleeWeapon = (AbstractMeleeWeapon) weapon;
                    bufferedWriter.write(WeaponFields.WEAPON_ONE_HANDED + ':' + meleeWeapon.getOneHanded() + '\n');
                    bufferedWriter.write(WeaponFields.WEAPON_LENGTH + ':' + meleeWeapon.getLength() + '\n');
                    if (weapon instanceof Bat) {
                        Bat bat = (Bat) weapon;
                        bufferedWriter.write(WeaponFields.WEAPON_SPIKES + ':' + bat.isWithSpikes() + '\n');
                        bufferedWriter.write(WeaponFields.WEAPON_MATERIAL + ':' + bat.getMaterial() + '\n');
                    } else if (weapon instanceof Blade) {
                        Blade blade = (Blade) weapon;
                        bufferedWriter.write(WeaponFields.WEAPON_SHARPNESS + ':' + blade.getSharpness() + '\n');
                    }
                } else if (weapon instanceof AbstractRangedWeapon) {
                    AbstractRangedWeapon rangedWeapon = (AbstractRangedWeapon) weapon;
                    bufferedWriter.write(WeaponFields.WEAPON_RANGE + ':' + rangedWeapon.getRange() + '\n');
                    bufferedWriter.write(WeaponFields.WEAPON_CAPACITY + ':' + rangedWeapon.getCapacity() + '\n');
                    if (weapon instanceof ArrowWeapon) {
                        ArrowWeapon arrowWeapon = (ArrowWeapon) weapon;
                        WeaponAmmunition<Arrow> arrows = arrowWeapon.getArrows();
                        if (arrows != null) {
                            bufferedWriter.write("Arrows{\n");
                            for (Arrow arrow : arrows.getAmmunition()) {
                                bufferedWriter.write("Is poisoned? :" + arrow.isPoisoned() + '\n');
                                bufferedWriter.write(WeaponFields.WEAPON_LENGTH + ':' + arrow.getLength() + '\n');
                            }
                            bufferedWriter.write("}\n");
                        }
                    } else if (weapon instanceof AbstractFirearm) {
                        AbstractFirearm abstractFirearm = (AbstractFirearm) weapon;
                        WeaponAmmunition<Bullet> bullets = abstractFirearm.getBullets();
                        if (bullets != null) {
                            bufferedWriter.write("Bullets{\n");
                            for (Bullet bullet : bullets.getAmmunition()) {
                                bufferedWriter.write(WeaponFields.WEAPON_LENGTH + ':' + bullet.getLength() + '\n');
                                bufferedWriter.write("Thickness:" + bullet.getThickness() + '\n');
                                bufferedWriter.write("Is armor piercing?:" + bullet.isArmorPiercing() + '\n');
                            }
                            bufferedWriter.write("}\n");
                        }
                        bufferedWriter.write(WeaponFields.WEAPON_CALIBRE + ':' + abstractFirearm.getCalibre() + '\n');
                        if (weapon instanceof AutomaticGun) {
                            bufferedWriter.write(WeaponFields.WEAPON_RAPIDITY + ':' +
                                    ((AutomaticGun)abstractFirearm).getRapidity() + '\n');
                        } else if (weapon instanceof Pistol) {
                            bufferedWriter.write(WeaponFields.WEAPON_ONE_HANDED + ':' +
                                    ((Pistol)abstractFirearm).isOneHanded() + '\n');
                        } else if (weapon instanceof Shotgun) {
                            bufferedWriter.write(WeaponFields.WEAPON_BARRELS + ':' +
                                    ((Shotgun)abstractFirearm).getBarrelsCount() + '\n');
                        } else if (weapon instanceof SniperRifle) {
                            bufferedWriter.write(WeaponFields.WEAPON_MULTIPLICITY + ':' +
                                    ((SniperRifle)abstractFirearm).getSightMultiplicity() + '\n');
                        }
                    }
                }
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            AlertCreator.createIOError();
        }
    }

    private String getViewClassName(String className) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < className.length(); i++) {
            char character = className.charAt(i);
            if (Character.isUpperCase(character) && i > 0) {
                stringBuilder.append(" ");
                stringBuilder.append(Character.toLowerCase(character));
            } else {
                stringBuilder.append(character);
            }
        }
        stringBuilder.append("s");
        return stringBuilder.toString();
    }
}
