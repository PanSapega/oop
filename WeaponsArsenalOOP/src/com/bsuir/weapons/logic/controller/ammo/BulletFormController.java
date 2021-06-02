package com.bsuir.weapons.logic.controller.ammo;

import com.bsuir.weapons.logic.validator.TextFieldsValidator;
import com.bsuir.weapons.model.weapon.ammo.Bullet;
import com.bsuir.weapons.view.AlertCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BulletFormController {
    private List<Bullet> bullets;

    @FXML
    private TextField lengthField;

    @FXML
    private TextField thicknessField;

    @FXML
    private ChoiceBox<Boolean> booleanChoice;

    @FXML
    private Button addButton;

    @FXML
    private TextField countField;

    @FXML
    public void initialize() {
        booleanChoice.getItems().addAll(true, false);
        booleanChoice.getSelectionModel().select(true);

        addButton.setOnAction(action -> {
            List<TextField> fields = new ArrayList<>(Arrays.asList(lengthField, thicknessField, countField));
            if (TextFieldsValidator.isValid(fields)) {
                int count = Integer.parseInt(countField.getText());
                float length = Float.parseFloat(lengthField.getText());
                float thickness = Float.parseFloat(thicknessField.getText());
                boolean isArmorPiercing = booleanChoice.getValue();
                if (bullets.size() == 0) {
                    for (int i = 0; i < count; i++) {
                        bullets.add(new Bullet(length, thickness, isArmorPiercing));
                    }
                } else {
                    for (int i = 0; i < count; i++) {
                        if (i == 0) {
                            Bullet bullet = bullets.get(0);
                            bullet.setLength(length);
                            bullet.setThickness(thickness);
                            bullet.setArmorPiercing(isArmorPiercing);
                        } else {
                            bullets.add(new Bullet(length, thickness, isArmorPiercing));
                        }
                    }
                }
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.close();
            } else {
                AlertCreator.createInvalidDataError();
            }
        });
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
        if (bullets.size() > 0) {
            Bullet firstBullet = bullets.get(0);
            lengthField.setText(String.valueOf(firstBullet.getLength()));
            thicknessField.setText(String.valueOf(firstBullet.getThickness()));
            countField.setText(String.valueOf(bullets.size()));
            booleanChoice.getSelectionModel().select(firstBullet.isArmorPiercing());
            addButton.setText("Edit");
        }
    }
}
