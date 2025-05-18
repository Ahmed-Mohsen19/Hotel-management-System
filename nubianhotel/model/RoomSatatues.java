package com.example.nubianhotel.model;

interface RoomStatus {
    String getStatus();
    boolean isAvailable();
}
class Available implements RoomStatus {
    public String getStatus() {
        return "Available";
    }

    public boolean isAvailable() {
        return true;
    }
}
class Booked implements RoomStatus {
    public String getStatus() {
        return "Booked";
    }

    public boolean isAvailable() {
        return false;
    }
}

