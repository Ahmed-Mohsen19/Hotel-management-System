package com.example.nubian_hotel.model;


 class RoomType {
    private String name;
    private double price;
    private int available;

    public RoomType(String name, double price, int available) {
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailable() {
        return available;
    }
}

