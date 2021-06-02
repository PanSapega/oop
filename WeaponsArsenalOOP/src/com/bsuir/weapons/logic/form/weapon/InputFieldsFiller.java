package com.bsuir.weapons.logic.form.weapon;

import com.bsuir.weapons.logic.form.InputItem;
import com.bsuir.weapons.model.weapon.AbstractWeapon;
import com.bsuir.weapons.model.weapon.melee.AbstractMeleeWeapon;
import com.bsuir.weapons.model.weapon.melee.impl.Bat;
import com.bsuir.weapons.model.weapon.melee.impl.Blade;
import com.bsuir.weapons.model.weapon.ranged.AbstractFirearm;
import com.bsuir.weapons.model.weapon.ranged.AbstractRangedWeapon;
import com.bsuir.weapons.model.weapon.ranged.impl.*;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class InputFieldsFiller {

    public static void fill(List<Node> nodes, AbstractWeapon weapon) {
        Iterator<Node> nodeIterator = nodes.iterator();

        Node inputNode = getNodeByType(nodeIterator, InputItem.TEXT_FIELD);
        ((TextField) inputNode).setText(weapon.getName());
        inputNode = getNodeByType(nodeIterator, InputItem.TEXT_FIELD);
        ((TextField) inputNode).setText(String.valueOf(weapon.getWeight()));
        inputNode = getNodeByType(nodeIterator, InputItem.TEXT_FIELD);
        ((TextField) inputNode).setText(String.valueOf(weapon.getCost()));
        inputNode = getNodeByType(nodeIterator, InputItem.QUALITY);
        ((ChoiceBox) inputNode).setValue(weapon.getQuality());

        if (weapon instanceof AbstractMeleeWeapon) {
            fillMeleeWeapons(nodeIterator, weapon);
        } else if (weapon instanceof AbstractRangedWeapon) {
            fillRangedWeapons(nodeIterator, weapon);
        }
    }

    private static void fillMeleeWeapons(Iterator<Node> nodeIterator, AbstractWeapon weapon) {
        AbstractMeleeWeapon meleeWeapon = (AbstractMeleeWeapon) weapon;
        Node inputNode = getNodeByType(nodeIterator, InputItem.BOOLEAN);
        ((ChoiceBox) inputNode).setValue(meleeWeapon.getOneHanded());
        inputNode = getNodeByType(nodeIterator, InputItem.TEXT_FIELD);
        ((TextField) inputNode).setText(String.valueOf(meleeWeapon.getLength()));
        if (meleeWeapon instanceof Bat) {
            Bat bat = (Bat) meleeWeapon;
            inputNode = getNodeByType(nodeIterator, InputItem.BOOLEAN);
            ((ChoiceBox) inputNode).setValue(bat.isWithSpikes());
            inputNode = getNodeByType(nodeIterator, InputItem.TEXT_FIELD);
            ((TextField) inputNode).setText(bat.getMaterial());
        } else if (meleeWeapon instanceof Blade) {
            Blade blade = (Blade) weapon;
            inputNode = getNodeByType(nodeIterator, InputItem.TEXT_FIELD);
            ((TextField) inputNode).setText(String.valueOf(blade.getSharpness()));
        }
    }

    private static void fillRangedWeapons(Iterator<Node> nodeIterator, AbstractWeapon weapon) {
        AbstractRangedWeapon rangedWeapon = (AbstractRangedWeapon) weapon;
        Node inputNode = getNodeByType(nodeIterator, InputItem.TEXT_FIELD);
        ((TextField) inputNode).setText(String.valueOf(rangedWeapon.getRange()));
        inputNode = getNodeByType(nodeIterator, InputItem.TEXT_FIELD);
        ((TextField) inputNode).setText(String.valueOf(rangedWeapon.getCapacity()));
        if (!(rangedWeapon instanceof ArrowWeapon)) {
            AbstractFirearm firearm = (AbstractFirearm) weapon;
            inputNode = getNodeByType(nodeIterator, InputItem.TEXT_FIELD);
            ((TextField) inputNode).setText(String.valueOf(firearm.getCalibre()));
            if (firearm instanceof AutomaticGun) {
                AutomaticGun automaticGun = (AutomaticGun) weapon;
                inputNode = getNodeByType(nodeIterator, InputItem.TEXT_FIELD);
                ((TextField) inputNode).setText(String.valueOf(automaticGun.getRapidity()));
            } else if (firearm instanceof Shotgun) {
                Shotgun shotgun = (Shotgun) weapon;
                inputNode = getNodeByType(nodeIterator, InputItem.TEXT_FIELD);
                ((TextField) inputNode).setText(String.valueOf(shotgun.getBarrelsCount()));
            } else if (firearm instanceof Pistol) {
                Pistol pistol = (Pistol) weapon;
                inputNode = getNodeByType(nodeIterator, InputItem.BOOLEAN);
                ((ChoiceBox) inputNode).setValue(pistol.isOneHanded());
            } else if (firearm instanceof SniperRifle) {
                SniperRifle sniperRifle = (SniperRifle) weapon;
                inputNode = getNodeByType(nodeIterator, InputItem.TEXT_FIELD);
                ((TextField) inputNode).setText(String.valueOf(sniperRifle.getSightMultiplicity()));
            }
        }
    }

    private static Node getNodeByType(Iterator<Node> nodeIterator, InputItem inputItem) {
        while (nodeIterator.hasNext()) {
            Node nextNode = nodeIterator.next();
            if (isCorrectInputType(nextNode, inputItem)) {
                return nextNode;
            }
        }
        throw new NoSuchElementException("No nodes left.");
    }

    private static boolean isCorrectInputType(Node node, InputItem inputItem) {
        if (inputItem == InputItem.TEXT_FIELD) {
            return node instanceof TextField;
        }
        return node instanceof ChoiceBox;
    }
}
