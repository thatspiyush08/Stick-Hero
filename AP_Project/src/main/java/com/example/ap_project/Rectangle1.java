package com.example.ap_project;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
public class Rectangle1{
    double width, height;

    Rectangle1(double width, double height) {
        this.width = width;
        this.height = height;
    }
}
    class CustomRectangle extends Rectangle1{

        public CustomRectangle(double width, double height) {
            super(width, height);
        }

        // Add any additional methods or properties as needed
    }


