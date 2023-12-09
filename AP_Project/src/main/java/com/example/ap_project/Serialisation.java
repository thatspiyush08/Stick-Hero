package com.example.ap_project;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class Serialisation {
    public static void main(String[] args) {
        GameControl gc = new GameControl();

        // Store the original score value
        int originalScore = gc.getScore();
        int ccount = gc.getCherrycount();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gamestate.ser"))) {
            gc.setScore(originalScore);
            gc.setCherryCount(ccount);

            oos.writeObject(gc);
            System.out.println("Game state serialized successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
