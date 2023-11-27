package com.example.ap_project;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
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
    private Rectangle Platform0;

    @FXML
    private Rectangle Platform1;

    @FXML
    private Rectangle Platform2;



    @FXML
    private Rectangle stickLine;
    private Timeline timeline;
    @FXML
    private ImageView Shero;
    int flag = 0;

    public void extendStick() {

//        if (stickLine != null)
//        {
        if (flag == 0){
            timeline = new Timeline(new KeyFrame(Duration.millis(100),event->{
                double currentLength = stickLine.getHeight();
                double newLength = currentLength + 5;
                stickLine.setHeight(newLength);
                stickLine.setTranslateY(-newLength + 5);
                stickman.Current_Platform=Platform0;
                stickman.Next_Platform=Platform1;
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
            flag = 1;
        }

//        }

    }

    public void rotateStick() {
        if (flag == 1){
            timeline.pause();
            stickLine.setRotate(0);
            stickLine.setTranslateY(-(stickLine.getHeight()/2)+3);
            stickLine.setTranslateX(stickLine.getHeight()/2.0);
            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.1), stickLine);
            rotateTransition.setByAngle(90); // Rotate by 90 degrees
            rotateTransition.play();
            movement();

        }

//        timeline.stop();

    }

    public void movement(){
        TranslateTransition move = new TranslateTransition(Duration.millis(2000),Shero);
        move.setByX(stickLine.getHeight()+10);
        move.setCycleCount(1);

        move.setOnFinished(event -> {
            if (stickman.Next_Platform.getLayoutX()-stickman.Current_Platform.getLayoutX()-65>stickLine.getHeight()||(stickman.Next_Platform.getLayoutX()-stickman.Current_Platform.getLayoutX()-60+stickman.Next_Platform.getWidth()<stickLine.getHeight())) {
                // Stickman is not on any platform, initiate downward movement
                fall();
            }
            else{
                TranslateTransition moveToCenter = new TranslateTransition(Duration.millis(500), Shero);
                moveToCenter.setToX(stickman.Next_Platform.getLayoutX()-stickman.Current_Platform.getLayoutX()-50+stickman.Next_Platform.getX()+(stickman.Next_Platform.getWidth()/2));
                moveToCenter.play();
            }

        });

        move.play();
    }

    public void fall() {
        TranslateTransition fallDown = new TranslateTransition(Duration.millis(1000), Shero);
        fallDown.setByY(200); // Adjust the value as needed for the downward fall
        fallDown.setCycleCount(1);
        fallDown.play();
    }



    public void Pause(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/ap_project/Pause.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}