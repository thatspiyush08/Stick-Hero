package com.example.ap_project;


import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.List;
import java.util.Map;

interface Collection {
    void collect();
}

// Association: Stickman has an Orientation
abstract class Orientation {
    protected double xPosition;
    protected double yPosition;

    public Orientation(double xPosition, double yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}

// Composition: Stickman has-a StickLength
public class Stickman {
    private static final double STICK_LENGTH = 100.0;
    private static final double MOVE_DISTANCE = 10.0;
    private double stickLength;

    public static Rectangle Current_Platform;
    public static Rectangle Next_Platform;
    private ImageView stickmanImageView;
    private TranslateTransition translateTransition;
    private StickLength stick;
    private CherriesCollection cherriesCollection;

    public Stickman(double xPosition, double yPosition, double stickLength) {

        this.stickLength = stickLength;
        this.stick = new StickLength(stickLength);
        this.cherriesCollection = new CherriesCollection();
        Image stickmanImage = new Image("file:/home/piyush/SEM3/AP/AP_Project/src/main/resources/com/example/ap_project/Player.png");
        stickmanImageView = new ImageView(stickmanImage);
        stickmanImageView.setFitWidth(stickLength);
        stickmanImageView.setPreserveRatio(true);

        // Create TranslateTransition for movement
        translateTransition = new TranslateTransition(Duration.millis(500), stickmanImageView);
    }
    public ImageView getImageView() {
        return stickmanImageView;
    }

    public void moveForward() {
        translateTransition.setByX(MOVE_DISTANCE);
        translateTransition.play();
    }

    public void extendStick() {
        stickLength += 3.0; // You can adjust the extension amount as needed
        stickmanImageView.setFitWidth(stickLength);
    }

    public boolean landing(Platform platform) {

        return true;
    }

    public void collectCherry(Cherries cherry, Score score) {
        cherriesCollection.collectCherry(cherry, score);
    }

    // Composition: Stickman has-a StickLength
    private class StickLength {
        private double length;

        public StickLength(double length) {
            this.length = length;
        }

        public double getLength() {
            return length;
        }
    }

    // Composition: Stickman has-a CherriesCollection
    private class CherriesCollection {
        public void collectCherry(Cherries cherry, Score score) {
            cherry.collect();

        }
    }
}



class Obstacle extends Orientation {

    // Instance variables
    private double width;
    private double height;
    private boolean isMoving;

    // Constructor
    public Obstacle(double xPosition, double yPosition, double width, double height, boolean isMoving) {
        super(xPosition, yPosition);
        this.width = width;
        this.height = height;
        this.isMoving = isMoving;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean isMoving() {
        return isMoving;
    }
    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void handleCollision(Stickman stickman) {
        // Implement obstacle collision logic with Stickman
    }


}

// Association: Cherries has an Orientation
class Cherries extends Orientation implements Collection {
    public Cherries(double xPosition, double yPosition) {
        super(xPosition, yPosition);
    }

    @Override
    public void collect() {
        // Implement cherry collection logic
    }
}

// Association: Platform has an Orientation
class Platform extends Orientation {
    private double width;

    public Platform(double xPosition, double width) {
        super(xPosition, 0);
        this.width = width;
    }

    public double getWidth() {
        return width;
    }
}

// Association: Win has an Orientation
class Win {
    private String message;


}


abstract class Score {
    protected int score;



    public abstract void updateScore(int points);

    public int getScore() {
        return score;
    }
}

// Association: Home has-a Menu
class Home extends Menu {


    public void displayHomeScreen() {
    }
}


class Menu  {
    private List<String> options;



    public void displayMenu() {

    }

    public void selectOption(int option) {

    }
}

class Controls {
    private String currentState;

    private Orientation orientation;

    public void startGame() {

    }

    public void pauseGame() {

    }

    public void endGame() {

    }

    public void restartGame() {

    }
}

// Inheritance: RestartGame extends Controls
class RestartGame extends Controls {
    @Override
    public void restartGame() {
        super.restartGame();
    }
}

// Association: Bonus has an Orientation
class Bonus extends Orientation {
    private String bonusCode;

    public Bonus(double xPosition, double yPosition, String bonusCode) {
        super(xPosition, yPosition);
        this.bonusCode = bonusCode;
    }

    // Composition: Bonus has-a BonusDetails
    private class BonusDetails {
        private String description;

        public BonusDetails(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}