package com.example.vehicleRentalWeb;

import com.example.vehicleRentalWeb.model.Rental;
import com.example.vehicleRentalWeb.model.Vehicle;
import com.example.vehicleRentalWeb.service.VehicleService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VehicleRentalController {

    private final VehicleService vehicleService;

    private int nextRentalId = 1;

    private final List<Rental> rentals = new ArrayList<>();

    public VehicleRentalController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // =========================
    // HOME
    // =========================

    @GetMapping("/")
    public String home() {
        return "index";
    }

    // =========================
    // VIEW VEHICLES
    // =========================

    @GetMapping("/vehicles")
    public String vehicles(Model model) {

        model.addAttribute(
                "vehicles",
                vehicleService.getAllVehicles()
        );

        return "vehicles";
    }

    // =========================
    // ADD VEHICLE
    // =========================

    @GetMapping("/add-vehicle")
    public String addVehiclePage(Model model) {

        model.addAttribute("vehicle", new Vehicle());

        return "add-vehicle";
    }

    @PostMapping("/add-vehicle")
    public String addVehicle(
            @RequestParam int vehicleId,
            @RequestParam String type,
            @RequestParam String brand,
            @RequestParam String model,
            @RequestParam double rentalRate) {

        Vehicle vehicle = new Vehicle(
                vehicleId,
                type,
                brand,
                model,
                rentalRate
        );

        vehicleService.addVehicle(vehicle);

        return "redirect:/vehicles";
    }

    // =========================
    // BOOK PAGE
    // =========================

    @GetMapping("/book")
    public String bookPage(
            @RequestParam(required = false) Integer vehicleId,
            Model model) {

        if (vehicleId != null) {

            Vehicle vehicle =
                    vehicleService.findVehicle(vehicleId);

            model.addAttribute("vehicle", vehicle);
            model.addAttribute("vehicleId", vehicleId);
        }

        return "book";
    }

    // =========================
    // RETURN PAGE
    // =========================

    @GetMapping("/return")
    public String returnPage() {
        return "return";
    }

    // =========================
    // BOOK VEHICLE
    // =========================

    @PostMapping("/book")
    public String bookVehicle(
            @RequestParam String customerName,
            @RequestParam String contactDetails,
            @RequestParam int vehicleId,
            @RequestParam int days,
            @RequestParam double deposit,
            Model model) {

        Vehicle vehicle =
                vehicleService.findVehicle(vehicleId);

        if (vehicle == null) {

            model.addAttribute(
                    "message",
                    "Vehicle not found."
            );

            return "book";
        }

        if (!vehicle.isAvailable()) {

            model.addAttribute(
                    "message",
                    "Vehicle is already rented."
            );

            return "book";
        }

        double rentalCost =
                vehicle.calculateRentalCost(days);

        vehicle.setAvailable(false);

        int rentalId =
                nextRentalId++;

        Rental rental = new Rental(
                rentalId,
                customerName,
                contactDetails,
                vehicle,
                days,
                rentalCost,
                deposit
        );

        rentals.add(rental);

        model.addAttribute(
                "customerName",
                customerName
        );

        model.addAttribute(
                "rentalId",
                rentalId
        );

        model.addAttribute(
                "contactDetails",
                contactDetails
        );

        model.addAttribute(
                "vehicle",
                vehicle
        );

        model.addAttribute(
                "days",
                days
        );

        model.addAttribute(
                "deposit",
                deposit
        );

        model.addAttribute(
                "rentalCost",
                rentalCost
        );

        model.addAttribute(
                "message",
                "Vehicle booked successfully!"
        );

        return "booking-success";
    }

    // =========================
    // RETURN VEHICLE
    // =========================

    @PostMapping("/return")
    public String returnVehicle(
            @RequestParam int rentalId,
            @RequestParam int lateDays,
            Model model) {

        Rental rental = null;

        for (Rental r : rentals) {

            if (r.getRentalId() == rentalId) {

                rental = r;

                break;
            }
        }

        if (rental == null) {

            model.addAttribute(
                    "message",
                    "Rental ID not found."
            );

            return "return";
        }

        rental.calculateLatePenalty(lateDays);

        rental.getVehicle().setAvailable(true);

        model.addAttribute(
                "rental",
                rental
        );

        return "return-success";
    }

    // =========================
    // UPDATE VEHICLE
    // =========================

    @GetMapping("/update")
    public String updatePage(
            @RequestParam int vehicleId,
            Model model) {

        Vehicle vehicle =
                vehicleService.findVehicle(vehicleId);

        if (vehicle == null) {

            model.addAttribute(
                    "message",
                    "Vehicle not found."
            );

            return "vehicles";
        }

        model.addAttribute(
                "vehicle",
                vehicle
        );

        return "update";
    }

    @PostMapping("/update")
    public String updateVehicle(
            @RequestParam int vehicleId,
            @RequestParam String brand,
            @RequestParam String model,
            @RequestParam double rentalRate,
            Model viewModel) {

        Vehicle vehicle =
                vehicleService.findVehicle(vehicleId);

        if (vehicle == null) {

            viewModel.addAttribute(
                    "message",
                    "Vehicle not found."
            );

            return "vehicles";
        }

        vehicle.setBrand(brand);

        vehicle.setModel(model);

        vehicle.setRentalRate(rentalRate);

        return "redirect:/vehicles";
    }

    // =========================
    // REMOVE VEHICLE
    // =========================

    @PostMapping("/remove")
    public String removeVehicle(
            @RequestParam int vehicleId,
            Model model) {

        boolean removed =
                vehicleService.removeVehicle(vehicleId);

        if (!removed) {

            model.addAttribute(
                    "message",
                    "Vehicle cannot be removed. It may be rented or not found."
            );
        }

        return "redirect:/vehicles";
    }
}