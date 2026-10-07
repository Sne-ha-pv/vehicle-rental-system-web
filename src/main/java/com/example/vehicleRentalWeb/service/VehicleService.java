package com.example.vehicleRentalWeb.service;

import com.example.vehicleRentalWeb.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    private final List<Vehicle> vehicles = new ArrayList<>();

    public VehicleService() {

        vehicles.add(
            new Vehicle(101, "Car", "Toyota", "Etios", 1500)
        );

        vehicles.add(
            new Vehicle(102, "Bike", "Honda", "Shine", 700)
        );

        vehicles.add(
            new Vehicle(103, "Van", "Toyota", "Hiace", 2000)
        );

        vehicles.add(
            new Vehicle(104, "Car", "Hyundai", "i20", 1800)
        );
    }

    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    public Vehicle findVehicle(int vehicleId) {

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getVehicleId() == vehicleId) {
                return vehicle;
            }
        }

        return null;
    }

    public boolean removeVehicle(int vehicleId) {

        Vehicle vehicle = findVehicle(vehicleId);

        if (vehicle != null && vehicle.isAvailable()) {

            vehicles.remove(vehicle);

            return true;
        }

        return false;
    }
        // Add new vehicle
    public void addVehicle(Vehicle vehicle) {

        vehicle.setAvailable(true);

        vehicles.add(vehicle);
    }
}