package com.example.ap_project;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @FXML
    private AnchorPane AnchorPane;
    private  Rectangle[] rectangles;
    private int screenCounter = 0;
    Parent root;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/ap_project/HomeScreen.fxml"));

        String path = "/home/piyush/SEM3/AP/Stick-Hero/AP_Project/src/main/resources/com/example/ap_project/Georges-Lament-Go-By-Ocean-Ryan1.mp3/";

        root = fxmlLoader.load();
        Scene scene = new Scene(root, 1100, 630);

        stage.setTitle("Made with love by Ayaan & Piyush");
        stage.setScene(scene);

        stage.show();



    }


    public static void main(String[] args) {
        launch();
        System.out.println("hello");
    }
}
