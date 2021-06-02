package com.bsuir.weapons.logic.controller.ammo;

import com.bsuir.weapons.logic.validator.TextFieldsValidator;
import com.bsuir.weapons.model.weapon.ammo.Arrow;
import com.bsuir.weapons.view.AlertCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrowFormController {
    private List<Arrow> arrows;

    @FXML
    private TextField lengthField;

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
            List<TextField> fields = new ArrayList<>(Arrays.asList(lengthField, countField));
            if (TextFieldsValidator.isValid(fields)) {
                int count = Integer.parseInt(countField.getText());
                float length = Float.parseFloat(lengthField.getText());
                boolean isPoisoned = booleanChoice.getValue();
                if (arrows.size() == 0) {
                    for (int i = 0; i < count; i++) {
                        arrows.add(new Arrow(length, isPoisoned));
                    }
                } else {
                    for (int i = 0; i < count; i++) {
                        if (i == 0) {
                            Arrow bullet = arrows.get(0);
                            bullet.setLength(length);
                            bullet.setPoisoned(isPoisoned);
                        } else {
                            arrows.add(new Arrow(length, isPoisoned));
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

    public void setArrows(List<Arrow> arrows) {
        this.arrows = arrows;
        if (arrows.size() > 0) {
            Arrow firstBullet = arrows.get(0);
            lengthField.setText(String.valueOf(firstBullet.getLength()));
            countField.setText(String.valueOf(arrows.size()));
            booleanChoice.getSelectionModel().select(firstBullet.isPoisoned());
            addButton.setText("Edit");
        }
    }
}
