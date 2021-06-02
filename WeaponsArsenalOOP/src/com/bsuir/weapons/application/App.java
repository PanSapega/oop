package com.bsuir.weapons.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class App {
    private static final String PLUGIN_API_DIRECTORY = "api";
    private static final String COMPILED_CLASSES_DIRECTORY = "out/production/WeaponsArsenalOOP";

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/WeaponsArsenal.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Weapons arsenal");
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) throws Exception {

        File pluginAPIDirectory = new File(PLUGIN_API_DIRECTORY);

        File[] pluginAPIs = pluginAPIDirectory.listFiles();
        if (pluginAPIs == null || pluginAPIs.length == 0) {
            throw new IllegalArgumentException("No plugins API!");
        }

        URL[] pluginAPILocations = new URL[pluginAPIs.length];
        for (int i = 0; i < pluginAPIs.length; i++) {
            pluginAPILocations[i] = pluginAPIs[i].toURI().toURL();
        }

        URLClassLoader pluginAPIsLoader = new URLClassLoader(pluginAPILocations, ClassLoader.getSystemClassLoader());

        URL compiledClassesDir = new File(COMPILED_CLASSES_DIRECTORY).toURI().toURL();
        URLClassLoader appLoader = new URLClassLoader(new URL[]{compiledClassesDir}, pluginAPIsLoader);

        Class<?> pluginLoaderClass = appLoader.loadClass("com.bsuir.weapons.application.FXLoader");
        pluginLoaderClass.newInstance();
    }
}
