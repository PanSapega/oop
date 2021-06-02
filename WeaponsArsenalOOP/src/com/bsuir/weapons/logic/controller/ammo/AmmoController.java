package com.bsuir.weapons.logic.controller.ammo;

import com.bsuir.weapons.model.weapon.ammo.AmmoType;
import com.bsuir.weapons.model.weapon.ammo.Arrow;
import com.bsuir.weapons.model.weapon.ammo.Bullet;
import com.bsuir.weapons.model.weapon.ammo.WeaponAmmunition;
import com.bsuir.weapons.model.weapon.AbstractWeapon;
import com.bsuir.weapons.model.weapon.ranged.AbstractFirearm;
import com.bsuir.weapons.model.weapon.ranged.impl.ArrowWeapon;
import com.bsuir.weapons.view.AlertCreator;
import com.bsuir.weapons.view.ViewConstants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class AmmoController {
    private AmmoType ammoType;
    private AbstractWeapon weapon;
    private ObservableList<Object> ammoList;

    @FXML
    private ListView<Object> viewList;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Label headerLabel;

    @FXML
    private Button addButton;

    @FXML
    private void initialize() {
        initializeButtons();
    }

    public void initializeButtons() {
        deleteButton.setOnAction(action -> {
            Object selectedObject = viewList.getSelectionModel().getSelectedItem();
            if (selectedObject != null) {
                if (ammoType == AmmoType.BULLET) {
                    ((AbstractFirearm) weapon).getBullets().remove((Bullet) selectedObject);
                } else if (ammoType == AmmoType.ARROW) {
                    ((ArrowWeapon) weapon).getArrows().remove((Arrow) selectedObject);
                }
            } else {
                AlertCreator.createNotSelectedAlert();
            }
            ammoList.remove(selectedObject);
        });

        addButton.setOnAction(action -> {
            if (ammoType == AmmoType.BULLET) {
                List<Bullet> bullets = new ArrayList<>();
                Stage stage = createBulletFormStage(bullets, ViewConstants.ADD_BULLET_TITLE);
                if (stage != null) {
                    stage.showAndWait();
                }
                AbstractFirearm firearm = (AbstractFirearm) weapon;
                WeaponAmmunition<Bullet> weaponAmmunition = firearm.getBullets();
                if (weaponAmmunition == null) {
                    firearm.setBulletsDeque(new ArrayDeque<>(bullets));
                } else {
                    weaponAmmunition.addItems(bullets);
                }
                ammoList.addAll(bullets);
            } else if (ammoType == AmmoType.ARROW) {
                List<Arrow> arrows = new ArrayList<>();
                Stage stage = createArrowFormStage(arrows, ViewConstants.ADD_ARROW_TITLE);
                if (stage != null) {
                    stage.showAndWait();
                }
                ArrowWeapon arrowWeapon = (ArrowWeapon) weapon;
                WeaponAmmunition<Arrow> weaponAmmunition = arrowWeapon.getArrows();
                if (weaponAmmunition == null) {
                    arrowWeapon.setArrowsDeque(new ArrayDeque<>(arrows));
                } else {
                    weaponAmmunition.addItems(arrows);
                }
                ammoList.addAll(arrows);
            }
        });

        editButton.setOnAction(action -> {
            Object selectedObject = viewList.getSelectionModel().getSelectedItem();
            if (selectedObject != null) {
                if (ammoType == AmmoType.BULLET) {
                    Bullet bullet = (Bullet) selectedObject;
                    List<Bullet> bullets = new ArrayList<>();
                    bullets.add(bullet);
                    Stage stage = createBulletFormStage(bullets, ViewConstants.EDIT_BULLET_TITLE);
                    if (stage != null) {
                        stage.showAndWait();
                    }
                    int index = viewList.getSelectionModel().getSelectedIndex();
                    ammoList.set(index, bullets.get(0));
                    for (int i = 1; i < bullets.size(); i++) {
                        ammoList.add(bullets.get(i));
                    }
                    AbstractFirearm firearm = (AbstractFirearm) weapon;
                    firearm.getBullets().remove(bullet);
                    firearm.getBullets().addItems(bullets);
                } else {
                    Arrow arrow = (Arrow) selectedObject;
                    List<Arrow> arrows = new ArrayList<>();
                    arrows.add(arrow);
                    Stage stage = createArrowFormStage(arrows, ViewConstants.EDIT_ARROW_TITLE);
                    if (stage != null) {
                        stage.showAndWait();
                    }
                    int index = viewList.getSelectionModel().getSelectedIndex();
                    ammoList.set(index, arrows.get(0));
                    for (int i = 1; i < arrows.size(); i++) {
                        ammoList.add(arrows.get(i));
                    }
                    ArrowWeapon arrowWeapon = (ArrowWeapon) weapon;
                    arrowWeapon.getArrows().remove(arrow);
                    arrowWeapon.getArrows().addItems(arrows);
                }
            } else {
                AlertCreator.createNotSelectedAlert();
            }
        });
    }

    private Stage createBulletFormStage(List<Bullet> bullets, String title) {
        FXMLLoader fxmlLoader = new FXMLLoader(AmmoController.class.getResource("../../../fxml/Bullet.fxml"));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            return null;
        }
        BulletFormController controller = fxmlLoader.getController();
        controller.setBullets(bullets);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(parent));

        return stage;
    }

    private Stage createArrowFormStage(List<Arrow> arrows, String title) {
        FXMLLoader fxmlLoader = new FXMLLoader(AmmoController.class.getResource("../../../fxml/Arrow.fxml"));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            return null;
        }
        ArrowFormController controller = fxmlLoader.getController();
        controller.setArrows(arrows);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(parent));

        return stage;
    }

    public void setAmmoType(AmmoType ammoType) {
        this.ammoType = ammoType;
    }

    public void setWeapon(AbstractWeapon weapon) {
        this.weapon = weapon;
    }

    public void initializeData() {
        headerLabel.setText(ammoType.getValue() + "s");
        List<Object> initialAmmo = new ArrayList<>();
        if (ammoType == AmmoType.BULLET) {
            WeaponAmmunition<Bullet> bullets = ((AbstractFirearm) weapon).getBullets();
            if (bullets != null) {
                initialAmmo.addAll(bullets.getAmmunition());
            }
        } else if (ammoType == AmmoType.ARROW) {
            WeaponAmmunition<Arrow> arrows = ((ArrowWeapon) weapon).getArrows();
            if (arrows != null) {
                initialAmmo.addAll(arrows.getAmmunition());
            }
        }
        ammoList = FXCollections.observableArrayList(initialAmmo);
        viewList.setItems(ammoList);
    }
}
