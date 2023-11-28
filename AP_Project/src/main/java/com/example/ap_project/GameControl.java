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
import javafx.scene.layout.Pane;
import java.io.IOException;

import static com.example.ap_project.SceneController.rectangles;

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





    double sticklenght=0;
    private boolean extending = false;

    public void extendStick() {

            extending = true;
            timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
                double currentLength = stickLine.getHeight();
                double newLength = currentLength + 20; // Increase the length by a larger amount
                stickLine.setHeight(newLength);
                sticklenght=newLength;
                stickLine.setTranslateY(-newLength + 5);
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.setOnFinished(event -> extending = false);
            timeline.play();
            if (!extending){
                rotateStick();
            }

    }


    public void rotateStick() {

            timeline.pause();

            stickLine.setRotate(0);
            stickLine.setTranslateY(-(stickLine.getHeight() / 2) + 3);
            stickLine.setTranslateX(stickLine.getHeight() / 2.0);

            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.06), stickLine);
            rotateTransition.setByAngle(90);
            rotateTransition.play();

            rotateTransition.setOnFinished(event -> {
                movement();
                extendStick();
            //    extendStick();  // Call extendStick after the stick
            });

    }

    public void movement() {
        TranslateTransition move = new TranslateTransition(Duration.millis(2000), Shero);
        move.setByX(stickLine.getHeight() + 10);
        move.setCycleCount(1);

        move.setOnFinished(event -> {
            boolean isOnPlatform = false;
            for (int i = 1; i < rectangles.length; i++) {
                if (350 - 40 <= stickLine.getHeight() && (350 - 60 + rectangles[i].getWidth() >= stickLine.getHeight())) {
                    // Stickman is on the platform
                    isOnPlatform = true;

                    // Calculate the shift distance
                    double shiftDistance = rectangles[i].getLayoutX() - rectangles[i - 1].getLayoutX() + rectangles[i].getX() + (rectangles[i - 1].getWidth() / 2);

                    // Shift all rectangles and the player using TranslateTransition
                    for (int j = 0; j < rectangles.length; j++) {
                        TranslateTransition shiftRectangle = new TranslateTransition(Duration.millis(1000), rectangles[j]);
                        shiftRectangle.setToX(rectangles[j].getTranslateX() - shiftDistance);
                        shiftRectangle.play();
                    }

                    TranslateTransition shiftPlayer = new TranslateTransition(Duration.millis(1000), Shero);
                    shiftPlayer.setToX(Shero.getTranslateX() - shiftDistance+20);
                    shiftPlayer.play();
                    stickLine.setHeight(20);
                    stickLine.setRotate(0);
                    TranslateTransition shiftStick = new TranslateTransition(Duration.millis(1000), stickLine);
                    shiftStick.setToX(Shero.getTranslateX() - shiftDistance+17);
                    shiftStick.play();
                    // Update the Stickman's current and next platforms
                    stickman.Current_Platform = rectangles[i];
                    if (i < rectangles.length - 1) {
                        stickman.Next_Platform = rectangles[i + 1];






                    } else {
                        stickman.Next_Platform = null;
                    }
                    break;
                }
            }

            if (!isOnPlatform) {
                // Stickman is not on any platform, initiate downward movement
                fall();
            }
        });

        move.play();

    }

    public void fall() {
        TranslateTransition fallDown = new TranslateTransition(Duration.millis(1000), Shero);
        fallDown.setByY(250); // Adjust the value as needed for the downward fall
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