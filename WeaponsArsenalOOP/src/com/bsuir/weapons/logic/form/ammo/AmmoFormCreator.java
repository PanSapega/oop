package com.bsuir.weapons.logic.form.ammo;

import com.bsuir.weapons.logic.controller.ammo.AmmoController;
import com.bsuir.weapons.model.weapon.ammo.AmmoType;
import com.bsuir.weapons.model.weapon.AbstractWeapon;
import com.bsuir.weapons.model.weapon.ranged.AbstractFirearm;
import com.bsuir.weapons.model.weapon.ranged.impl.ArrowWeapon;
import com.bsuir.weapons.view.ViewConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AmmoFormCreator {

    public Stage createForm(AbstractWeapon weapon) {
        FXMLLoader fxmlLoader = new FXMLLoader(AmmoFormCreator.class.getResource("../../../fxml/AmmoForm.fxml"));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            return null;
        }
        AmmoController controller = fxmlLoader.getController();
        controller.setWeapon(weapon);
        AmmoType ammoType;
        if (weapon instanceof ArrowWeapon) {
            ammoType = AmmoType.ARROW;
        } else if (weapon instanceof AbstractFirearm) {
            ammoType = AmmoType.BULLET;
        } else {
            return null;
        }
        controller.setAmmoType(ammoType);
        controller.initializeData();
        Stage stage = new Stage();
        stage.setTitle(ViewConstants.AMMO_TITLE);
        stage.setScene(new Scene(parent));

        return stage;
    }
}
