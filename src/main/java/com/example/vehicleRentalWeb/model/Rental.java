package com.example.vehicleRentalWeb.model;

public class Rental {

    private int rentalId;
    private String customerName;
    private String contactDetails;
    private Vehicle vehicle;
    private int days;
    private double rentalCost;
    private double securityDeposit;
    private double latePenalty;

    public Rental(
            int rentalId,
            String customerName,
            String contactDetails,
            Vehicle vehicle,
            int days,
            double rentalCost,
            double securityDeposit) {

        this.rentalId = rentalId;
        this.customerName = customerName;
        this.contactDetails = contactDetails;
        this.vehicle = vehicle;
        this.days = days;
        this.rentalCost = rentalCost;
        this.securityDeposit = securityDeposit;
        this.latePenalty = 0;
    }

    public int getRentalId() {
        return rentalId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getDays() {
        return days;
    }

    public double getRentalCost() {
        return rentalCost;
    }

    public double getSecurityDeposit() {
        return securityDeposit;
    }

    public double getLatePenalty() {
        return latePenalty;
    }

    public void calculateLatePenalty(int lateDays) {

        latePenalty = lateDays * 200;
    }

    public double getRefundAmount() {

        return Math.max(
                0,
                securityDeposit - latePenalty
        );
    }
}