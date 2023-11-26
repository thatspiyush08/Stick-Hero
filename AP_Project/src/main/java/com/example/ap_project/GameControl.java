package com.example.ap_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class GameControl {
    private Stickman stickman;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Rectangle stickLine;

    public void extendStick() {
//      stickLine.setX(100);
//      stickLine.setY(100);
        if (stickLine != null) {
            double currentLength = stickLine.getHeight();
            double newLength = stickLine.getHeight() + 10;
            stickLine.setHeight(currentLength + 10);
            stickLine.setTranslateY(-newLength+5);
        }
    }


    public void Pause(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/ap_project/Pause.fxml"));
        stickman.extendStick();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
