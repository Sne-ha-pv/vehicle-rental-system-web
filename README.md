# 🚗 Vehicle Rental System

A web-based Vehicle Rental System developed using **Java, Spring Boot, Thymeleaf, HTML, and CSS**.

The system allows users to view available vehicles, book vehicles, return rented vehicles, calculate rental costs and security deposit refunds, and manage vehicle details.

---

## 📌 Features

- 🚘 View available vehicles
- 🔍 Display vehicle details
- 📅 Book a vehicle
- 💰 Calculate rental cost based on rental days
- 🔑 Manage security deposits
- 🔄 Return rented vehicles
- ⚠️ Calculate late-return penalties
- 💵 Calculate security deposit refund
- ✏️ Update vehicle details
- 🗑️ Remove available vehicles
- 📱 Responsive web interface
- 🔒 Real-time vehicle availability tracking

---

## 🛠️ Technologies Used

- **Java 21**
- **Spring Boot**
- **Spring Web**
- **Thymeleaf**
- **HTML5**
- **CSS3**
- **Maven**
- **Git & GitHub**

---

## 🏗️ Project Structure

```text
vehicleRentalWeb
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.vehicleRentalWeb
│   │   │       ├── VehicleRentalWebApplication.java
│   │   │       ├── VehicleRentalController.java
│   │   │       │
│   │   │       ├── model
│   │   │       │   ├── Vehicle.java
│   │   │       │   └── Rental.java
│   │   │       │
│   │   │       └── service
│   │   │           └── VehicleService.java
│   │   │
│   │   └── resources
│   │       ├── static
│   │       │   └── style.css
│   │       │
│   │       └── templates
│   │           ├── index.html
│   │           ├── vehicles.html
│   │           ├── book.html
│   │           ├── booking-success.html
│   │           ├── return.html
│   │           ├── return-success.html
│   │           └── update.html
│   │
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md