// HousekeepingService.java
package com.example.hotel;

public class HousekeepingService implements Service {
    private String name;
    private int quantity;

    public HousekeepingService(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getCategory() { return "HOUSEKEEPING"; }

    @Override
    public double getPrice() { return 0.0; } // Always zero per your DB design

    @Override
    public int getQuantity() { return quantity; }
}
