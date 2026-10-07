package com.example.vehicleRentalWeb.model;

public class Vehicle {

    private int vehicleId;
    private String type;
    private String brand;
    private String model;
    private double rentalRate;
    private boolean available;

    // Default constructor required for Add Vehicle form
    public Vehicle() {
        this.available = true;
    }

    // Constructor
    public Vehicle(int vehicleId, String type, String brand,
                   String model, double rentalRate) {

        this.vehicleId = vehicleId;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.rentalRate = rentalRate;
        this.available = true;
    }

    // Getters
    public int getVehicleId() {
        return vehicleId;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setters
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Calculate rental cost
    public double calculateRentalCost(int days) {
        return rentalRate * days;
    }
}