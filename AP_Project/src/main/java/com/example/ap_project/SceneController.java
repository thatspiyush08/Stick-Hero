package com.example.ap_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;


public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Stickman stickman; // Add this field
    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane;
    public static   Rectangle[] rectangles;




    public void HomeToRunController(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/ap_project/Theme.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root, 1100, 520);
//        root = fxmlLoader.load();
//        Scene scene = new Scene(root, 1100, 630);
//        rectangles = new CustomRectangle[2000];
        double xPosition = 213;
        double gapWidth = 450;

        rectangles = new Rectangle[2000];
        xPosition = 208;
        gapWidth = 350;
        Group G1 = new Group();
        for (int i = 0; i < 2000; i++) {
            rectangles[i] = new Rectangle();
            rectangles[i].setWidth(Math.random() * 50 + 50);
            rectangles[i].setHeight(111);
            rectangles[i].setLayoutY(410-(rectangles[i].getHeight()/2));
            rectangles[i].setLayoutX(xPosition);

            xPosition += gapWidth; // Adjust the gap width as needed
            G1.getChildren().add(rectangles[i]);
        }
        ((Pane)root).getChildren().add(G1);

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

}
