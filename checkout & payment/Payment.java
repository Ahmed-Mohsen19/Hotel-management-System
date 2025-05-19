package com.example.checkout;
/*
import java.util.HashMap;
import java.util.Map;

public class Payment{

    private static Map<Integer, Map<String, String>> paymentData = new HashMap<>();

    // Initialize the mock data
    static {
        Map<String, String> data = new HashMap<>();
        data.put("customername", "John Doe");
        data.put("reservationno", "12345");
        data.put("roomtype", "Double");
        data.put("services", "Breakfast, Wi-Fi");
        data.put("totalpayment", "2000");
        paymentData.put(1, data);  // Key = reservation number
    }

    public static Map<String, String> getPaymentData(int reservationNumber) {
        return paymentData.get(reservationNumber);
    }
}*/
/*
package com.example.checkout;

public interface PaymentDataAccess {
    String getCustomerName();
    String getRoomType();
    String getReservationNumber();
}

class RealPaymentDataAccess implements PaymentDataAccess {
    private String customerName;
    private String roomType;
    private String reservationNumber;

    public RealPaymentDataAccess(String customerName, String roomType, String reservationNumber) {
        this.customerName = customerName;
        this.roomType = roomType;
        this.reservationNumber = reservationNumber;
    }

    @Override
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String getRoomType() {
        return roomType;
    }

    @Override
    public String getReservationNumber() {
        return reservationNumber;
    }
}

class PaymentProxy implements PaymentDataAccess {
    private RealPaymentDataAccess realData;
    private boolean authorized;

    public PaymentProxy(String customerName, String roomType, String reservationNumber, boolean authorized) {
        this.realData = new RealPaymentDataAccess(customerName, roomType, reservationNumber);
        this.authorized = authorized;
    }

    @Override
    public String getCustomerName() {
        return authorized ? realData.getCustomerName() : "Access Denied";
    }

    @Override
    public String getRoomType() {
        return authorized ? realData.getRoomType() : "Access Denied";
    }

    @Override
    public String getReservationNumber() {
        return authorized ? realData.getReservationNumber() : "Access Denied";
    }
}

// Example Usage in Payment Controller
public class PaymentController {
    public void showPaymentDetails() {
        PaymentDataAccess proxy = new PaymentProxy("John Doe", "Single", "R1234", true);
        System.out.println("Customer Name: " + proxy.getCustomerName());
        System.out.println("Room Type: " + proxy.getRoomType());
        System.out.println("Reservation Number: " + proxy.getReservationNumber());
    }
}

*/