package com.example.ap_project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;

public class GameControl {
    private Stickman stickman;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Line stickLine;

    public void extendStick(ActionEvent event) {

        double currentLength = stickLine.getEndY() - stickLine.getStartY();
        double newLength = currentLength + 5.0;

        stickLine.setEndY(stickLine.getStartY() + newLength);
    }


    public void setStickman(Stickman stickman) {
        this.stickman=stickman;
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
