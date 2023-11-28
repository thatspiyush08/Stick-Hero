package com.example.ap_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/ap_project/HomeScreen.fxml"));

        String path = "/C://Users//mohmm//Downloads//music//George's Lament - Go By Ocean _ Ryan McCaffrey.mp3/";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        Scene scene = new Scene(fxmlLoader.load(), 1100, 630);
        stage.setTitle("Playing Audio");
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();



    }


    public static void main(String[] args) {
        launch();
    }
}