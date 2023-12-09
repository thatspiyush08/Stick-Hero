package com.example.ap_project;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
        import javafx.animation.*;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;

        import java.io.*;

        import javafx.fxml.FXMLLoader;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.image.ImageView;
        import javafx.scene.input.KeyCode;
        import javafx.scene.input.KeyEvent;
        import javafx.scene.layout.AnchorPane;
        import javafx.scene.shape.Rectangle;
        import javafx.stage.Stage;
        import javafx.util.Duration;

        import java.util.Random;
        import java.util.concurrent.TimeUnit;

        import static com.example.ap_project.SceneController.*;
        import static com.example.ap_project.SceneController.cherries;


        interface Sserialization{
            public void  serialization();
        }

        interface DeSerialization{
            public void deserialization(ActionEvent event);
        }

public class GameControl implements Serializable ,Sserialization ,DeSerialization{

            @FXML
            private  AnchorPane reloadAnchor;

    @FXML
    private AnchorPane FallPane;

    @FXML
    private Label MYSCORE;
    private static final long serialVersionUID = 1L;

    @FXML
    private Label HIGHSCORE;

    @FXML
    private Label CHERRYLABLE;

    @FXML
    private AnchorPane PAUSEPANE;
    @FXML
    private Button RESTART;

    @FXML
    private Button RESUME;

    @FXML
    private Button QUIT;

    @FXML
    private Button SAVE;
    @FXML
    private Label PAUSELABLE;



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
    int cherryCount=0;
    double stickLength = 0;
    private boolean extending = false;
    int platformFlag=0;
    @FXML
    private Button pause;
    private static GameControl instance;

    public static GameControl getInstance() {   //SINGLETON DESIGN PATTERN USED IN GAME CONTROL!
        if (instance == null) {
            instance = new GameControl();
        }
        return instance;
    }

    private void setCherriesOpacity(int platformIndex) {

        Random random = new Random();
        int randomNumber = random.nextInt(2);
        System.out.println(randomNumber);
        if (randomNumber==1){
            cherries[platformIndex].setOpacity(1);
        }
    }

    public int getScore() {
        return score;
    }
    public int getCherrycount() {
        return cherryCount;
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
        platformFlag=1;
        rotateTransition.setOnFinished(event -> {
            movement();
        });
    }
    public void flipHero(ImageView Shero, int score) {
        double centerY = Shero.getLayoutY() + Shero.getBoundsInLocal().getHeight() / 2;

        if (isFlipped) {
            Shero.setScaleY(1); // Revert to normal
            Shero.setLayoutY(centerY - (Shero.getBoundsInLocal().getHeight() / 2) - 34.0);
        } else {
            Shero.setScaleY(-1); // Flip vertically
            Shero.setLayoutY(centerY + (Shero.getBoundsInLocal().getHeight() / 2) - 7.0);
        }
        isFlipped = !isFlipped;

        // Check if player is flipped and update cherry opacity
        if (isFlipped) {
            double playerXLayout = Shero.getLayoutX() + Shero.getBoundsInLocal().getWidth() / 2;
            double playerYLayout = Shero.getLayoutY() + Shero.getBoundsInLocal().getHeight() / 2;

            // Iterate over cherries to check conditions
//            for (int i = 0; i < cherries.length; i++) {
//                ImageView cherry = cherries[i];
            double cherryXLayout = cherries[score].getLayoutX() + cherries[score].getBoundsInLocal().getWidth() / 2;
            double cherryYLayout = cherries[score].getLayoutY() + cherries[score].getBoundsInLocal().getHeight() / 2;
//            System.out.println(Math.abs(playerXLayout - cherries[score].getLayoutX()));
            // Check conditions for opacity change using bounding boxes
            if (cherries[score].getOpacity() == 1 && Math.abs(playerXLayout - cherries[score].getLayoutX()) < 5
                    && Math.abs(playerYLayout - cherryYLayout) < 5) {
                cherries[score].setOpacity(0);
                cherryCount++;
                CherryLabel.setText("Cherries: " + cherryCount);
            }
        }
    }



    public void movement() {

        TranslateTransition move = new TranslateTransition(Duration.millis(1000), Shero);

        move.setCycleCount(1);


        move.setOnFinished(event -> {
            boolean isOnPlatform = false;
            int platformIndex = -1;

            for (int i = 1; i < rectangles.length; i++) {
                if (350 - 40 <= stickLine.getHeight() && (285 + rectangles[i].getWidth() >= stickLine.getHeight())) {
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
                    int i=j;


                    Timeline checkPlatform=new Timeline((new KeyFrame(Duration.millis(1), Event -> {
                        if (Shero.getBoundsInParent().intersects(rectangles[i].getBoundsInParent()) && isFlipped )
                        {
                            double Xcoor=Shero.getX();
                            double Ycoor=Shero.getY();
//                            System.out.println("yes");
                            TranslateTransition falldown = new TranslateTransition(Duration.millis(500), Shero);
                            falldown.setByY(131);
                            RotateTransition somersault = new RotateTransition(Duration.millis(500), Shero);
                            somersault.setByAngle(360);
                            somersault.setCycleCount(1);

                            ParallelTransition pT = new ParallelTransition(falldown, somersault);
                            pT.play();
                            pT.setOnFinished(event1->{
                                String s= String.valueOf(score);
                                String c= String.valueOf(cherryCount);
                                CHERRYLABLE.setText(c);
                                MYSCORE.setText(s);
                                FallPane.toFront();
                                Shero.setRotate(0);
                                TranslateTransition restore = new TranslateTransition(Duration.millis(100), Shero);
                                restore.setToY(Ycoor);
                                restore.setToX(Xcoor);

                                if (isFlipped){
                                    flipHero(Shero,score);
                                }
                                TranslateTransition shiftStick = new TranslateTransition(Duration.millis(1000), stickLine);
                                shiftStick.setToX(rectangles[score-1].getX());
                                shiftStick.play();
                                stickLine.setRotate(0);
                                stickLine.setHeight(0);
                                restore.play();
                            });


                        }

                    })));
                    checkPlatform.setCycleCount(Timeline.INDEFINITE);
                    checkPlatform.play();

                }

                // Translate cherries along with the platforms
                for (ImageView cherry : cherries) {
                    TranslateTransition shiftCherry = new TranslateTransition(Duration.millis(1000), cherry);
                    shiftCherry.setToX(cherry.getTranslateX() - shiftDistance);
                    shiftCherry.play();

                    Timeline Check = new Timeline((new KeyFrame(Duration.millis(10), Event -> {
                        if (Shero.getBoundsInParent().intersects(cherries[score-1].getBoundsInParent()) && isFlipped && cherries[score-1].getOpacity() == 1)
                        {
                            cherries[score-1].setOpacity(0);
                            cherryCount += 1;
                            CherryLabel.setText(String.valueOf(cherryCount));
                        }

                    })));
                    Check.setCycleCount(Timeline.INDEFINITE);
                    Check.play();
                }

                TranslateTransition shiftPlayer = new TranslateTransition(Duration.millis(1000), Shero);
                shiftPlayer.setToX(rectangles[platformIndex].getX());
                shiftPlayer.play();



                Shero.setX(rectangles[platformIndex].getX());


                stickman.Current_Platform = rectangles[platformIndex];
                if (platformIndex < rectangles.length - 1) {
                    stickman.Next_Platform = rectangles[platformIndex + 1];
//                    stickLine.setHeight(0);
//                    stickLine.setRotate(0);
                    TranslateTransition shiftStick = new TranslateTransition(Duration.millis(1000), stickLine);
                    shiftStick.setToX(rectangles[platformIndex].getX()-(stickLine.getHeight()/2)-16);
                    shiftStick.play();
                    int fl=platformIndex;
                    shiftStick.setOnFinished(event3->{
                        stickLine.setHeight(0);
                        stickLine.setRotate(0);
                        TranslateTransition shiftS = new TranslateTransition(Duration.millis(100), stickLine);
                        shiftS.setToX(rectangles[fl].getX());
                        shiftS.play();
                    });

//                    int finalPlatformIndex = platformIndex;
//                    Timeline stayStick = new Timeline(new KeyFrame(Duration.millis(1), event1 -> {
//                        if (Shero.getBoundsInParent().intersects(rectangles[finalPlatformIndex ].getBoundsInParent())) {
//                            stickLine.setHeight(0);
//                            stickLine.setRotate(0);
////                            stayStick.stop();
//                        }
//                    }));
//                    stayStick.setCycleCount(Timeline.INDEFINITE);
//                    stayStick.play();




//
//                     Check if isFlipped is true and update cherry opacity


                    Random random = new Random();
                    int randomNumber = random.nextInt(2);
                    ScoreLabel.setText("Score: " + score);

                } else {
                    stickman.Next_Platform = null;
                }
            } else {
                double Xcoord=Shero.getX();
                double Ycoord=Shero.getY();

                TranslateTransition shiftPlayer1 = new TranslateTransition(Duration.millis(1000), Shero);
                shiftPlayer1.setToX(stickLine.getHeight() + 30);
                shiftPlayer1.play();

                Timeline Check = new Timeline((new KeyFrame(Duration.millis(10), Event -> {
                    if (Shero.getBoundsInParent().intersects(cherries[score].getBoundsInParent()) && isFlipped && cherries[score].getOpacity() == 1)
                    {
                        cherries[score].setOpacity(0);
//                        cherryCount += 1;
//                        CherryLabel.setText(String.valueOf(cherryCount));
                    }

                })));
                Check.setCycleCount(Timeline.INDEFINITE);
                Check.play();

                shiftPlayer1.setOnFinished(eve -> {
                    TranslateTransition falldown = new TranslateTransition(Duration.millis(500), Shero);
                    falldown.setByY(131);
                    RotateTransition somersault = new RotateTransition(Duration.millis(500), Shero);
                    somersault.setByAngle(360);
                    somersault.setCycleCount(1);

                    ParallelTransition pT = new ParallelTransition(falldown, somersault);
                    pT.play();
                    pT.setOnFinished(event1->{
                        String s= String.valueOf(score);
                        String c= String.valueOf(cherryCount);
                        CHERRYLABLE.setText(c);
                        MYSCORE.setText(s);
                        FallPane.toFront();

                        TranslateTransition restore = new TranslateTransition(Duration.millis(100), Shero);
                        restore.setToY(Ycoord);
                        restore.setToX(Xcoord);
                        if (isFlipped){
                            flipHero(Shero,score);
                        }
                        TranslateTransition shiftStick = new TranslateTransition(Duration.millis(1000), stickLine);
                        shiftStick.setToX(rectangles[score].getX());
                        shiftStick.play();
                        stickLine.setRotate(0);
                        stickLine.setHeight(0);
                        restore.play();


                    });

                });

            }
        });

        move.play();


    }

    public void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.F) {
//            System.out.println(score);
            flipHero(Shero,score);
        }
    }

    public void Pause(ActionEvent event) throws IOException {
        String s= String.valueOf(score);
        PAUSELABLE.setText(s);
        PAUSEPANE.toFront();
    }
    public void setCherryCount(int ccount) {
        this.cherryCount=ccount;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public void Restar(ActionEvent event) throws IOException{
        SceneController sc= new SceneController();
        sc.HomeToRunController(event);
    }
    public void Resum(ActionEvent event) throws IOException {
        PAUSEPANE.toBack();
    }

    public void Quits(ActionEvent e) throws IOException{
        FallPane.toBack();
        reloadAnchor.toFront();
    }

    public void ReSpawn(){
        if (cherryCount>=3){

            String s= String.valueOf(score);
            String c= String.valueOf(cherryCount);
            CHERRYLABLE.setText(c);
            MYSCORE.setText(s);
            cherryCount-=3;
            String c1= String.valueOf(cherryCount);
            CherryLabel.setText(c1);
            FallPane.toBack();
            reloadAnchor.toBack();

        }
    }

    @Override
    public void serialization() {
        GameControl gc = new GameControl();

        // Store the original score value
        int originalScore = score;
        int ccount = cherryCount;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gamestate.ser"))) {
            gc.setScore(originalScore);
            gc.setCherryCount(ccount);

            oos.writeObject(gc);
            System.out.println(ccount);
            System.out.println(originalScore);
            System.out.println("Game state serialized successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static GameControl gameControl;
    @Override
    public void deserialization(ActionEvent event) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gamestate.ser"))) {
            GameControl dgc = (GameControl) ois.readObject();
            int dscore=dgc.getScore();
            int dcherrycount=dgc.getCherrycount();
            System.out.println("Game state deserialized successfully.");
            System.out.println("Deserialised Score= "+ dscore);
            System.out.println("Deserialised Cherry Count= "+ dcherrycount);

            gameControl = dgc;
            FallPane.toBack();
            reloadAnchor.toBack();


        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    public  static class GameControlTest {
        private GameControl gcc;

        @Test
        public void testScore() throws IOException {

            assertNull(gcc.getScore());

        }

        @Test
        public void testCherryCountIsNull() {
            // Check if the initial cherry count is null
            assertNull(gcc.getCherrycount());
        }

        @Test
        public void testSerialization() {
            // Call the serialization method
            gcc.serialization();

        }
    }
}

