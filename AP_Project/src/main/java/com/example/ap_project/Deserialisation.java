package com.example.ap_project;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserialisation {
    private static GameControl gameControl;

    public static void main(String[] args) {
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


