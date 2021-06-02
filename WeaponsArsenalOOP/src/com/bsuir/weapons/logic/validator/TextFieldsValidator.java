package com.bsuir.weapons.logic.validator;

import com.bsuir.weapons.view.ViewConstants;
import javafx.scene.control.TextField;

import java.util.List;

public class TextFieldsValidator {
    private static final int MAX_STR_LENGTH = 25;

    public static boolean isValid(List<TextField> textFieldList) {
        for (TextField textField : textFieldList) {
            String value = textField.getText();
            if (value == null || value.isEmpty() || value.length() > MAX_STR_LENGTH) {
                return false;
            }
            String promptValue = textField.getPromptText();
            if (!promptValue.contains(ViewConstants.STRING_TYPE)) {
                try {
                    if (promptValue.contains(ViewConstants.INT_TYPE)) {
                        if (Integer.parseInt(value) < 0) {
                            return false;
                        }
                    } else {
                        if (Float.parseFloat(value) < 0) {
                            return false;
                        }
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return true;
    }
}
