package com.example.hotel;

public class FoodService implements Service {
    private String name;
    private double price;
    private int quantity;

    public FoodService(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getCategory() { return "FOOD & BEVERAGE"; }

    @Override
    public double getPrice() { return price; }

    @Override
    public int getQuantity() { return quantity; }

}