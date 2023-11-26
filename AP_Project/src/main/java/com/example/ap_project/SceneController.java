package com.example.ap_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;


public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Stickman stickman; // Add this field

    public void setStickman(Stickman stickman) {
        this.stickman = stickman;
    }


    public void HomeToRunController(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/ap_project/Theme.fxml"));
        stickman.extendStick();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
