package com.example.ap_project;

public class PowerUp extends Orientation implements Collection {
    private String powerUpType;
    private boolean isActive;

    public PowerUp(double xPosition, double yPosition, String powerUpType) {
        super(xPosition, yPosition);
        this.powerUpType = powerUpType;
        this.isActive = false;  // By default, the power-up is not active
    }

    // Method to activate the power-up
    public void activate() {
        isActive = true;
    }

    // Method to deactivate the power-up
    public void deactivate() {
        isActive = false;
    }

    // Method to check if the power-up is active
    public boolean isActive() {
        return isActive;
    }

    // Implementation of the collect method from the Collection interface
    @Override
    public void collect() {

    }

    // Getter method for the power-up type
    public String getPowerUpType() {
        return powerUpType;
    }
}
