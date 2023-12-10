package com.example.ap_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;

import javafx.application.Application;
import javafx.scene.Group;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @FXML
    private AnchorPane AnchorPane;
    private  Rectangle[] rectangles;
    private int screenCounter = 0;
    Parent root;
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/ap_project/HomeScreen.fxml"));

        String path = "/C://Users//mohmm//Downloads//music//George's Lament - Go By Ocean _ Ryan McCaffrey.mp3/";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        Scene scene = new Scene(fxmlLoader.load(), 1100, 630);


        stage.setTitle("Made with love by Ayaan & Piyush");
        stage.setScene(scene);

        stage.show();

    }


    private static GameControl gameControl;

    public static void main(String[] args) {



        launch();

    }
}
