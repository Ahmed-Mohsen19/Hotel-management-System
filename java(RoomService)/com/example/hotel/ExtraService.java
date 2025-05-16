// ExtraService.java
package com.example.hotel;

public class ExtraService implements Service {
    private String name;
    private int quantity;

    public ExtraService(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getCategory() { return "EXTRAS"; }

    @Override
    public double getPrice() { return 0.0; }

    @Override
    public int getQuantity() { return quantity; }
}
