package com.example.ap_project;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.example.ap_project.SceneController.*;
import static com.example.ap_project.SceneController.cherries;

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
    private boolean isFlipped = false;

    @FXML
    private ImageView Shero;
    @FXML
    private Label CherryLabel;
    @FXML
    private Label ScoreLabel;

    int flag = 0;
    int score=0;
    double stickLength = 0;
    private boolean extending = false;

    private void setCherriesOpacity(int platformIndex) {
        // Generate a new random number for each platform
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        System.out.println(randomNumber);
        if (randomNumber==0){
            cherries[platformIndex].setOpacity(1);
        }
        }


    public void extendStick() {
        if (!extending) {
            extending = true;
            timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
                double currentLength = stickLine.getHeight();
                double newLength = currentLength + 20; // Increase the length by a larger amount
                stickLine.setHeight(newLength);
                stickLength = newLength;
                stickLine.setTranslateY(-newLength + 20);
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }

    public void rotateStick() {
        timeline.pause();
        extending = false;
        stickLine.setRotate(0);
        stickLine.setTranslateY(-(stickLine.getHeight() / 2) + 2);
        stickLine.setTranslateX(stickLine.getHeight() / 2.0);

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.1), stickLine);
        rotateTransition.setByAngle(90);
        rotateTransition.play();

        rotateTransition.setOnFinished(event -> {
            movement();
        });
    }
    public void flipHero(ImageView Shero) {
        double centerY = Shero.getLayoutY() + Shero.getBoundsInLocal().getHeight() / 2;

        if (!isFlipped) {
            Shero.setScaleY (-1); // Flip vertically
            Shero.setLayoutY(centerY + (Shero.getBoundsInLocal().getHeight() / 2)-7.0 );
        } else {
            Shero.setScaleY(1); // Revert to normal
            Shero.setLayoutY(centerY - (Shero.getBoundsInLocal().getHeight() / 2)-34.0);
        }
        isFlipped = !isFlipped;
    }
    public void movement() {
        TranslateTransition move = new TranslateTransition(Duration.millis(1000), Shero);
        move.setCycleCount(1);

        move.setOnFinished(event -> {
            boolean isOnPlatform = false;
            int platformIndex = -1;

            for (int i = 1; i < rectangles.length; i++) {
                if (350 - 40 <= stickLine.getHeight() && (320 + rectangles[i].getWidth() >= stickLine.getHeight())) {
                    isOnPlatform = true;
                    platformIndex = i;
                    break;
                }
            }

            if (isOnPlatform) {
                score++;

                setCherriesOpacity(score);

                double currentPlatformCenter = rectangles[platformIndex - 1].getLayoutX();
                double nextPlatformCenter = rectangles[platformIndex].getLayoutX();
                double shiftDistance = nextPlatformCenter - currentPlatformCenter;

                for (int j = 0; j < rectangles.length; j++) {
                    TranslateTransition shiftRectangle = new TranslateTransition(Duration.millis(1000), rectangles[j]);
                    shiftRectangle.setToX(rectangles[j].getTranslateX() - shiftDistance);
                    shiftRectangle.play();
                }

                // Translate cherries along with the platforms
                for (ImageView cherry : cherries) {
                    TranslateTransition shiftCherry = new TranslateTransition(Duration.millis(1000), cherry);
                    shiftCherry.setToX(cherry.getTranslateX() - shiftDistance);
                    shiftCherry.play();
                }

                TranslateTransition shiftPlayer = new TranslateTransition(Duration.millis(1000), Shero);
                shiftPlayer.setToX(rectangles[platformIndex].getX());
                shiftPlayer.play();
                Shero.setX(rectangles[platformIndex].getX());

                stickman.Current_Platform = rectangles[platformIndex];
                if (platformIndex < rectangles.length - 1) {
                    stickman.Next_Platform = rectangles[platformIndex + 1];
                    stickLine.setHeight(0);
                    stickLine.setRotate(0);
                    TranslateTransition shiftStick = new TranslateTransition(Duration.millis(1000), stickLine);
                    shiftStick.setToX(rectangles[platformIndex].getX());
                    shiftStick.play();

                    // Generate a new random number for each platform
                    Random random = new Random();
                    int randomNumber = random.nextInt(2);
                    ScoreLabel.setText("Score: " + score);



                } else {
                    stickman.Next_Platform = null;
                }
            } else {
                TranslateTransition shiftPlayer1 = new TranslateTransition(Duration.millis(1000), Shero);
                shiftPlayer1.setToX(stickLine.getHeight() + 30);
                shiftPlayer1.play();
                shiftPlayer1.setOnFinished(eve -> {
                    TranslateTransition falldown = new TranslateTransition(Duration.millis(500), Shero);
                    falldown.setByY(131);
                    RotateTransition somersault = new RotateTransition(Duration.millis(500), Shero);
                    somersault.setByAngle(360);
                    somersault.setCycleCount(1);

                    ParallelTransition pT = new ParallelTransition(falldown, somersault);
                    pT.play();
                });
            }
        });

        move.play();

    }
    public void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.F) {
            flipHero(Shero);
        }
    }

    public void Pause(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/ap_project/Pause.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
