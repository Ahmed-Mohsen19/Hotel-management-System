package com.example.nubian_hotel.model;


public class RoomFactory {
    public static RoomType createRoom(String name, double price, int available) {
        return new RoomType(name, price, available);
    }
}
