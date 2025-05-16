// ServiceFactory.java
package com.example.hotel;

public class ServiceFactory {

    // This factory creates Service objects from category, name, price and quantity
    public static Service createService(String category, String name, double price, int quantity) {
        switch (category.toUpperCase()) {
            case "FOOD & BEVERAGE":
                return new FoodService(name, price, quantity);
            case "HOUSEKEEPING":
                return new HousekeepingService(name, quantity);
            case "MAINTENANCE":
                return new MaintenanceService(name, quantity);
            case "EXTRAS":
                return new ExtraService(name, quantity);
            default:
                throw new IllegalArgumentException("Unknown service category: " + category);
        }
    }
}
