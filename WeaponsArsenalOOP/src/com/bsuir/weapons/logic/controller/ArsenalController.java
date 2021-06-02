package com.bsuir.weapons.logic.controller;

import com.bsuir.weapons.data.ResultSet;
import com.bsuir.weapons.data.factory.WeaponFactory;
import com.bsuir.weapons.data.io.IOType;
import com.bsuir.weapons.data.io.Reader;
import com.bsuir.weapons.data.io.Writer;
import com.bsuir.weapons.data.io.impl.WeaponReaderFactory;
import com.bsuir.weapons.data.io.impl.WeaponWriterFactory;
import com.bsuir.weapons.data.repository.WeaponRepository;
import com.bsuir.weapons.logic.form.ammo.AmmoFormCreator;
import com.bsuir.weapons.logic.form.weapon.WeaponInputFormCreator;
import com.bsuir.weapons.data.updater.WeaponUpdater;
import com.bsuir.weapons.model.weapon.AbstractWeapon;
import com.bsuir.weapons.model.weapon.WeaponType;
import com.bsuir.weapons.model.weapon.melee.AbstractMeleeWeapon;
import com.bsuir.weapons.model.weapon.ranged.AbstractRangedWeapon;
import com.bsuir.weapons.view.AlertCreator;
import com.bsuir.weapons.view.ViewConstants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.*;

public class ArsenalController {
    private final WeaponRepository weaponRepository;
    private final FileChooser fileChooser = new FileChooser();
    private ObservableList<AbstractWeapon> viewWeapons;
    private ObservableList<String> actionLogs;


    public ArsenalController() {
        weaponRepository = WeaponRepository.getInstance();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem openButton;

    @FXML
    private MenuItem saveButton;

    @FXML
    private MenuItem quitButton;

    @FXML
    private MenuItem helpButton;

    @FXML
    private ChoiceBox<String> typeSelector;

    @FXML
    private ListView<AbstractWeapon> viewList;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button showAmmoButton;

    @FXML
    private Label headerLabel;

    @FXML
    private Button actionButton;

    @FXML
    private ListView<String> actionLogger;

    @FXML
    void initialize() {
        initSelector();
        initButtons();
        initMenu();

        actionLogs = FXCollections.observableArrayList();
        actionLogger.setItems(actionLogs);
        setWeaponsOnView(weaponRepository.getWeaponsByType(WeaponType.ALL));
    }

    private void setWeaponsOnView(List<AbstractWeapon> abstractWeaponList) {
        viewWeapons = FXCollections.observableArrayList(abstractWeaponList);
        viewList.setItems(viewWeapons);
    }

    private void addActionLog(String log) {
        actionLogs.add(log);
    }

    private void initSelector() {
        typeSelector.getItems().addAll(ViewConstants.VIEW_SELECTOR_STRINGS);
        typeSelector.getSelectionModel().select(ViewConstants.DEFAULT_SELECTOR_STRING);
        typeSelector.setOnAction(event -> {
            String value = typeSelector.getValue();
            WeaponType weaponType = WeaponType.getType(value);
            setWeaponsOnView(weaponRepository.getWeaponsByType(weaponType));
            headerLabel.setText(value);
        });
    }

    private void initMenu() {
        helpButton.setOnAction(action -> {
            AlertCreator.createHelpAlert();
        });

        quitButton.setOnAction(action -> {
            if (AlertCreator.createConfirmAlert() == ButtonType.YES) {
                System.exit(0);
            }
        });

        openButton.setOnAction(action -> {
            Window window = headerLabel.getScene().getWindow();
            File file = fileChooser.showOpenDialog(window);
            if (file != null) {
                IOType ioType = getIOTypeByFile(file);
                Reader<AbstractWeapon> reader = WeaponReaderFactory.create(ioType);
                List<AbstractWeapon> weapons = reader.read(file, getIOTypeByFile(file));
                weaponRepository.clear();
                for (AbstractWeapon abstractWeapon : weapons) {
                    weaponRepository.add(abstractWeapon);
                }
                setWeaponsOnView(weapons);
            }
        });

        saveButton.setOnAction(action -> {
            Window window = headerLabel.getScene().getWindow();
            File file = fileChooser.showSaveDialog(window);
            if (file != null) {
                List<AbstractWeapon> weapons = weaponRepository.getWeaponsByType(WeaponType.ALL);
                IOType ioType = getIOTypeByFile(file);
                Writer<AbstractWeapon> writer = WeaponWriterFactory.create(ioType);
                writer.write(file, weapons, ioType);
            }
        });
    }

    private IOType getIOTypeByFile(File file) {
        String filePath = file.getPath();
        String extension = filePath.substring(filePath.lastIndexOf('.'));
        switch (extension) {
            case ".xml":
                return IOType.XML;
            case ".txt":
                return IOType.TXT;
        }
        return IOType.BINARY;
    }

    private void initButtons() {
        deleteButton.setOnAction(event -> {
            AbstractWeapon weapon = viewList.getSelectionModel().getSelectedItem();
            if (weapon != null) {
                weaponRepository.remove(weapon);
                viewWeapons.remove(weapon);
            } else {
                AlertCreator.createNotSelectedAlert();
            }
        });

        actionButton.setOnAction(event -> {
            AbstractWeapon weapon = viewList.getSelectionModel().getSelectedItem();
            if (weapon != null) {
                String log;
                if (weapon instanceof AbstractRangedWeapon) {
                    log = ((AbstractRangedWeapon) weapon).shoot();
                } else {
                    log = ((AbstractMeleeWeapon) weapon).hit();
                }
                addActionLog(log);
                int index = viewList.getSelectionModel().getSelectedIndex();
                viewWeapons.set(index, weapon);
            } else {
                AlertCreator.createNotSelectedAlert();
            }
        });

        showAmmoButton.setOnAction(event -> {
            AbstractWeapon weapon = viewList.getSelectionModel().getSelectedItem();
            if (weapon instanceof AbstractRangedWeapon) {
                AmmoFormCreator ammoFormCreator = new AmmoFormCreator();
                Optional<AbstractWeapon> currentWeaponOptional = weaponRepository.findById(weapon.getId());
                if (currentWeaponOptional.isPresent()) {
                    AbstractWeapon rangedWeapon = currentWeaponOptional.get();
                    Stage ammoStage = ammoFormCreator.createForm(rangedWeapon);
                    if (ammoStage != null) {
                        ammoStage.showAndWait();
                        int index = viewList.getSelectionModel().getSelectedIndex();
                        viewWeapons.set(index, rangedWeapon);
                    }
                }
            } else {
                AlertCreator.createNoAmmoAlert();
            }
        });

        addButton.setOnAction(a -> {
            ChoiceDialog<String> choiceDialog = new ChoiceDialog<>(ViewConstants.DEFAULT_WEAPON_SELECTOR_STRING,
                    ViewConstants.WEAPON_SELECTOR_STRINGS);
            choiceDialog.setTitle(ViewConstants.ADD_WEAPON_TITLE);
            choiceDialog.setHeaderText(ViewConstants.ADD_WEAPON_HEADER);
            choiceDialog.setResultConverter((ButtonType type) -> {
                ButtonBar.ButtonData data = type == null ? null : type.getButtonData();
                if (data == ButtonBar.ButtonData.OK_DONE) {
                    return "OK";
                } else {
                    return null;
                }
            });
            Optional<String> selectedType = choiceDialog.showAndWait();

            if (selectedType.isPresent() && "OK".equals(selectedType.get())) {
                String item = choiceDialog.getSelectedItem();
                WeaponType weaponType = WeaponType.getType(item + "s");
                ResultSet resultSet = new ResultSet();
                WeaponInputFormCreator weaponFormCreator = new WeaponInputFormCreator();
                Stage addWeaponStage = weaponFormCreator.createForm(resultSet, weaponType);
                if (addWeaponStage != null) {
                    addWeaponStage.showAndWait();
                    if (resultSet.getSize() > 0) {
                        AbstractWeapon weapon = WeaponFactory.create(weaponType, resultSet);
                        weaponRepository.add(weapon);
                        viewWeapons.add(weapon);
                    }
                }
            }
        });

        editButton.setOnAction(a -> {
            AbstractWeapon viewWeapon = viewList.getSelectionModel().getSelectedItem();
            if (viewWeapon != null) {
                String className = viewWeapon.getClass().getSimpleName();
                WeaponType weaponType = WeaponType.getType(getViewClassName(className));
                ResultSet resultSet = new ResultSet();
                WeaponInputFormCreator weaponFormCreator = new WeaponInputFormCreator();
                weaponFormCreator.setDefaultWeapon(viewWeapon);
                Stage addWeaponStage = weaponFormCreator.createForm(resultSet, weaponType);
                if (addWeaponStage != null) {
                    addWeaponStage.showAndWait();
                    if (resultSet.getSize() > 0) {
                        long id = viewWeapon.getId();
                        Optional<AbstractWeapon> weaponOptional = weaponRepository.findById(id);
                        weaponOptional.ifPresent(weapon -> {
                            WeaponUpdater.update(weapon, resultSet);
                            int index = viewList.getSelectionModel().getSelectedIndex();
                            viewWeapons.set(index, viewWeapon);
                        });
                    }
                }
            } else {
                AlertCreator.createNotSelectedAlert();
            }
        });
    }

    private String getViewClassName(String className) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < className.length(); i++) {
            char character = className.charAt(i);
            if (Character.isUpperCase(character) && i > 0) {
                stringBuilder.append(" ");
                stringBuilder.append(Character.toLowerCase(character));
            } else {
                stringBuilder.append(character);
            }
        }
        stringBuilder.append("s");
        return stringBuilder.toString();
    }

}
