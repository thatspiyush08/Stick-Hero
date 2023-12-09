package com.example.ap_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Random;




public class SceneController implements DeSerialization {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Stickman stickman; // Add this field
    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane;
    public static   Rectangle[] rectangles;
    public static ImageView[] cherries = new ImageView[50];






    public void HomeToRunController(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/ap_project/Theme.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root, 1100, 520);
//        root = fxmlLoader.load();
//        Scene scene = new Scene(root, 1100, 630);
//        rectangles = new CustomRectangle[2000];
        double xPosition = 213;
        double gapWidth = 450;
        Random random = new Random();


        // Generate a random number between -0.5 and 1

        rectangles = new Rectangle[2000];
        xPosition = 208;
        gapWidth = 350;
        Group G1 = new Group();
        for (int i = 0; i < 2000; i++) {
            rectangles[i] = new Rectangle();
            double randomNumber = random.nextDouble() * 1.5 - 0.5;
            rectangles[i].setWidth(Math.random() * 50 + 50);
            rectangles[i].setHeight(135);
            rectangles[i].setLayoutY(425-(rectangles[i].getHeight()/2));
            rectangles[i].setLayoutX(xPosition);

            xPosition += gapWidth; // Adjust the gap width as needed
            G1.getChildren().add(rectangles[i]);
        }
        int num=350;
        ((Pane)root).getChildren().add(G1);
        for (int i = 0; i < 50; i++) {
            cherries[i] = new ImageView();
            String imagePath = "/com/example/ap_project/Cherry.png";
            InputStream stream = getClass().getResourceAsStream(imagePath);

            if (stream != null) {
                Image image = new Image(stream);
                cherries[i].setImage(image);

                cherries[i].setFitWidth(41);
                cherries[i].setFitHeight(41);

                // Ensure the index is within bounds
                int index = i < rectangles.length ? i : rectangles.length - 1;
                Random random2=new Random();
                double randi=random2.nextDouble()*1.5-0.5;

                double randomX = num + randi * 100;
                double randomY = rectangles[index].getLayoutY() - cherries[i].getFitHeight();

                cherries[i].setLayoutX(randomX);
                cherries[i].setLayoutY(355);
                cherries[i].opacityProperty().setValue(0);

                ((Pane)root).getChildren().add(cherries[i]); // Add cherries to the root
            } else {
                System.err.println("Image file not found: " + imagePath);
            }
            num+=350;
        }

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }


    public void Exit(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/ap_project/HomeScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Resume(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/ap_project/Theme.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private static GameControl gameControl;
    @Override
    public void deserialization() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gamestate.ser"))) {
            GameControl dgc = (GameControl) ois.readObject();
            int dscore=dgc.getScore();
            int dcherrycount=dgc.getCherrycount();
            System.out.println("Game state deserialized successfully.");
            System.out.println("Deserialised Score= "+ dscore);
            System.out.println("Deserialised Cherry Count= "+ dcherrycount);

            gameControl = dgc;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
