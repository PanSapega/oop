package com.bsuir.weapons.logic.controller;

import com.bsuir.weapons.data.ResultSet;
import com.bsuir.weapons.logic.validator.TextFieldsValidator;
import com.bsuir.weapons.view.AlertCreator;
import com.bsuir.weapons.view.ViewConstants;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class WeaponFormController {
    private ResultSet resultSet;

    @FXML
    private Label headerLabel;

    @FXML
    private Button addButton;

    @FXML
    private AnchorPane pane;

    @FXML
    void initialize() {
        initializeButton();
    }

    public void setHeaderLabel(String headerText) {
        headerLabel.setText(headerText);
    }

    public void setNodes(List<Node> nodes) {
        pane.getChildren().addAll(nodes);
    }

    public void addButtonY(int yOffset) {
        addButton.setLayoutY(addButton.getLayoutY() + yOffset);
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public void initializeButton() {
        addButton.setOnAction(event -> {
            List<TextField> fields = new ArrayList<>();
            for (Node node : pane.getChildren()) {
                if (node instanceof TextField) {
                    fields.add((TextField) node);
                }
            }
            if (TextFieldsValidator.isValid(fields)) {
                String key = "key";
                for (Node node : pane.getChildren()) {
                    if (node instanceof Label) {
                        String text = ((Label) node).getText();
                        key = text.substring(0, text.length() - 1);
                    } else if (node instanceof TextField) {
                        TextField textField = (TextField) node;
                        String value = textField.getText();
                        String promptValue = textField.getPromptText();
                        Object objectData;
                        if (promptValue.contains(ViewConstants.INT_TYPE)) {
                            objectData = Integer.parseInt(value);
                        } else if (promptValue.contains(ViewConstants.FLOAT_TYPE)) {
                            objectData = Float.parseFloat(value);
                        } else {
                            objectData = value;
                        }
                        resultSet.add(key, objectData);
                    } else if (node instanceof ChoiceBox) {
                        ChoiceBox<Object> choiceBox = (ChoiceBox) node;
                        Object value = choiceBox.getValue();
                        resultSet.add(key, value);
                    }
                }
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.close();
            } else {
                AlertCreator.createInvalidDataError();
            }
        });
    }
}

