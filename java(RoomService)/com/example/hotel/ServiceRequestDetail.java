package com.example.hotel;

public class ServiceRequestDetail {
    private String serviceName;
    private int quantity;

    public ServiceRequestDetail(String serviceName, int quantity) {
        this.serviceName = serviceName;
        this.quantity = quantity;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getQuantity() {
        return quantity;
    }
}
