package com.bsuir.weapons.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertCreator {

    public static void createNoAmmoAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, ViewConstants.NO_AMMO_CONTENT,
                ButtonType.OK);
        alert.setTitle(ViewConstants.NO_AMMO_TITLE);
        alert.setHeaderText(ViewConstants.NO_AMMO_TITLE);
        alert.showAndWait();
    }

    public static void createInvalidDataError() {
        Alert alert = new Alert(Alert.AlertType.ERROR, ViewConstants.INVALID_INPUT_CONTENT, ButtonType.OK);
        alert.setTitle(ViewConstants.INVALID_INPUT_TITLE);
        alert.setHeaderText(ViewConstants.INVALID_INPUT_TITLE);
        alert.showAndWait();
    }

    public static void createNotSelectedAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, ViewConstants.NOT_SELECTED_CONTENT, ButtonType.OK);
        alert.setTitle(ViewConstants.NOT_SELECTED_TITLE);
        alert.setHeaderText(ViewConstants.NOT_SELECTED_TITLE);
        alert.showAndWait();
    }

    public static void createIOError() {
        Alert alert = new Alert(Alert.AlertType.ERROR, ViewConstants.IO_ERROR_CONTENT, ButtonType.OK);
        alert.setTitle(ViewConstants.IO_ERROR_TITLE);
        alert.setHeaderText(ViewConstants.IO_ERROR_TITLE);
        alert.showAndWait();
    }

    public static void createHelpAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, ViewConstants.HELP_CONTENT, ButtonType.OK);
        alert.setTitle(ViewConstants.HELP_TITLE);
        alert.setHeaderText(ViewConstants.HELP_HEADER);
        alert.showAndWait();
    }

    public static ButtonType createConfirmAlert () {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, ViewConstants.CONFIRM_CONTENT,
                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.setTitle(ViewConstants.CONFIRM_TITLE);
        alert.showAndWait();
        return alert.getResult();
    }
}
