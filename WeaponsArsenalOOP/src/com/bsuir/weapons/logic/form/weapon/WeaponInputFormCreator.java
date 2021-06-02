package com.bsuir.weapons.logic.form.weapon;

import com.bsuir.weapons.data.ResultSet;
import com.bsuir.weapons.logic.controller.WeaponFormController;
import com.bsuir.weapons.model.weapon.AbstractWeapon;
import com.bsuir.weapons.model.weapon.WeaponType;
import com.bsuir.weapons.view.ViewConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class WeaponInputFormCreator {
    private static final int INITIAL_WINDOW_HEIGHT = 115;
    private static final int WINDOW_WIDTH = 380;
    private static final int Y_OFFSET = 45;

    private AbstractWeapon weapon;

    public void setDefaultWeapon(AbstractWeapon weapon) {
        this.weapon = weapon;
    }

    public Stage createForm(ResultSet resultSet, WeaponType weaponType) {
        FXMLLoader fxmlLoader = new FXMLLoader(WeaponInputFormCreator.class.getResource("../../../fxml/WeaponForm.fxml"));
        Parent root = loadRoot(fxmlLoader);
        if (root == null) {
            return null;
        }
        ViewNodesCreator viewNodesCreator = new ViewNodesCreator();
        List<Node> nodes = viewNodesCreator.create(weaponType);
        if (weapon != null) {
            InputFieldsFiller.fill(nodes, weapon);
        }
        int yOffset = nodes.size() / 2 * Y_OFFSET;
        WeaponFormController controller = fxmlLoader.getController();
        controller.setNodes(nodes);
        initializeController(weaponType, controller, resultSet, yOffset);
        Stage stage = new Stage();
        stage.setTitle(weapon == null ? ViewConstants.ADD_WEAPON_TITLE : ViewConstants.EDIT_WEAPON_TITLE);
        stage.setScene(new Scene(root, WINDOW_WIDTH, INITIAL_WINDOW_HEIGHT + yOffset));

        return stage;
    }

    private Parent loadRoot(FXMLLoader fxmlLoader) {
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            return null;
        }
    }

    private void initializeController(WeaponType weaponType, WeaponFormController controller,
                                      ResultSet resultSet, int yOffset) {
        String weaponText = weaponType.getValue();
        String headerText = (weapon == null ? "Adding " : "Editing ") +
                weaponText.substring(0, weaponText.length() - 1);
        controller.setHeaderLabel(headerText);
        controller.addButtonY(yOffset - 25);
        controller.setResultSet(resultSet);
    }
}
