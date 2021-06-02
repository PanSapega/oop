package com.bsuir.weapons.logic.form.weapon;

import com.bsuir.weapons.logic.form.InputItem;
import com.bsuir.weapons.model.weapon.Quality;
import com.bsuir.weapons.model.weapon.WeaponType;
import com.bsuir.weapons.view.ViewConstants;
import com.bsuir.weapons.view.WeaponFields;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ViewNodesCreator {
    private static final List<String> INTEGER_FIELDS = Arrays.asList(
            WeaponFields.WEAPON_BARRELS, WeaponFields.WEAPON_CAPACITY, WeaponFields.WEAPON_COST
    );
    private static final List<String> STRING_FIELDS = Arrays.asList(
            WeaponFields.WEAPON_MATERIAL, WeaponFields.WEAPON_NAME
    );
    private static final int Y_INITIAL = 65;
    private static final int X_LABEL = 14;
    private static final int X_FIELD = 150;
    private static final int FIELD_WIDTH = 210;
    private static final int Y_OFFSET = 40;

    private int currentYOffset = Y_INITIAL;

    public List<Node> create(WeaponType weaponType) {
        List<Node> nodes = new ArrayList<>();
        addInputItem(nodes, WeaponFields.WEAPON_NAME, InputItem.TEXT_FIELD);
        addInputItem(nodes, WeaponFields.WEAPON_WEIGHT, InputItem.TEXT_FIELD);
        addInputItem(nodes, WeaponFields.WEAPON_COST, InputItem.TEXT_FIELD);
        addInputItem(nodes, WeaponFields.WEAPON_QUALITY, InputItem.QUALITY);
        if (weaponType == WeaponType.BAT || weaponType == WeaponType.BLADE) {
            addInputItem(nodes, WeaponFields.WEAPON_ONE_HANDED, InputItem.BOOLEAN);
            addInputItem(nodes, WeaponFields.WEAPON_LENGTH, InputItem.TEXT_FIELD);
            if (weaponType == WeaponType.BAT) {
                addInputItem(nodes, WeaponFields.WEAPON_SPIKES, InputItem.BOOLEAN);
                addInputItem(nodes, WeaponFields.WEAPON_MATERIAL, InputItem.TEXT_FIELD);
            } else {
                addInputItem(nodes, WeaponFields.WEAPON_SHARPNESS, InputItem.TEXT_FIELD);
            }
        } else {
            addInputItem(nodes, WeaponFields.WEAPON_RANGE, InputItem.TEXT_FIELD);
            addInputItem(nodes, WeaponFields.WEAPON_CAPACITY, InputItem.TEXT_FIELD);
            if (weaponType != WeaponType.ARROW_WEAPON) {
                addInputItem(nodes, WeaponFields.WEAPON_CALIBRE, InputItem.TEXT_FIELD);
                switch (weaponType) {
                    case AUTOMATIC_GUN:
                        addInputItem(nodes, WeaponFields.WEAPON_RAPIDITY, InputItem.TEXT_FIELD);
                        break;
                    case SNIPER_RIFLE:
                        addInputItem(nodes, WeaponFields.WEAPON_MULTIPLICITY, InputItem.TEXT_FIELD);
                        break;
                    case PISTOL:
                        addInputItem(nodes, WeaponFields.WEAPON_ONE_HANDED, InputItem.BOOLEAN);
                        break;
                    case SHOTGUN:
                        addInputItem(nodes, WeaponFields.WEAPON_BARRELS, InputItem.TEXT_FIELD);
                        break;
                }
            }
        }
        return nodes;
    }

    private void addInputItem(List<Node> nodes, String itemText, InputItem inputItem) {
        Label label = new Label(itemText + ":");
        label.setLayoutX(X_LABEL);
        label.setLayoutY(currentYOffset + 5);
        nodes.add(label);
        Node inputNode;
        switch (inputItem) {
            case BOOLEAN:
                inputNode = createBooleanChoice();
                break;
            case QUALITY:
                inputNode = createQualityChoice();
                break;
            case TEXT_FIELD:
                inputNode = createTextField(itemText);
                break;
            default:
                throw new IllegalArgumentException("No such input item.");
        }
        inputNode.setLayoutX(X_FIELD);
        inputNode.setLayoutY(currentYOffset);
        nodes.add(inputNode);
        currentYOffset += Y_OFFSET;
    }


    private TextField createTextField(String text) {
        TextField textField = new TextField();
        String promptText = String.format("Enter %s(%s)", text, getType(text));
        textField.setPromptText(promptText);
        textField.setPrefWidth(FIELD_WIDTH);

        return textField;
    }

    private ChoiceBox<Boolean> createBooleanChoice() {
        ChoiceBox<Boolean> booleanChoice = new ChoiceBox<>();
        booleanChoice.getItems().addAll(true, false);
        booleanChoice.getSelectionModel().select(true);
        booleanChoice.setPrefWidth(FIELD_WIDTH);

        return booleanChoice;
    }

    private ChoiceBox<Quality> createQualityChoice() {
        ChoiceBox<Quality> qualityChoiceBox = new ChoiceBox<>();
        for (Quality quality : Quality.values()) {
            qualityChoiceBox.getItems().add(quality);
        }
        qualityChoiceBox.getSelectionModel().select(0);
        qualityChoiceBox.setPrefWidth(FIELD_WIDTH);

        return qualityChoiceBox;
    }

    private String getType(String text) {
        if (INTEGER_FIELDS.contains(text)) {
            return ViewConstants.INT_TYPE;
        } else if (STRING_FIELDS.contains(text)) {
            return ViewConstants.STRING_TYPE;
        }
        return ViewConstants.FLOAT_TYPE;
    }
}
