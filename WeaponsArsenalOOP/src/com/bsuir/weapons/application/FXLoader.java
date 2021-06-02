package com.bsuir.weapons.application;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXLoader {

    public FXLoader() throws IOException {
        com.sun.javafx.application.PlatformImpl.startup(()->{
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("../fxml/WeaponsArsenal.fxml")
            );
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Weapons arsenal");
            stage.show();
            stage.setResizable(false);
        });
    }
}
