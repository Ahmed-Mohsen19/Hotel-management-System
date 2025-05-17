CREATE DATABASE IF NOT EXISTS hotel_system;
USE hotel_system;


CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
);


CREATE TABLE rooms (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_number VARCHAR(10) NOT NULL UNIQUE,
    room_type VARCHAR(50),
    capacity INT,
    price DECIMAL(10, 2),
    is_available BOOLEAN DEFAULT TRUE
);


CREATE TABLE bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    room_id INT,
    check_in DATE,
    check_out DATE,
    first_name VARCHAR(50),
    email VARCHAR(50),
    ssn INT,
    last_name VARCHAR(50),
    number_of_guests INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);


CREATE TABLE services (
    service_id INT AUTO_INCREMENT PRIMARY KEY,
    service_name VARCHAR(100),
    category ENUM('FOOD & BEVERAGE', 'HOUSEKEEPING', 'MAINTENANCE', 'EXTRAS'),
    price DECIMAL(10, 2) DEFAULT 0.00
);


CREATE TABLE service_requests (
    request_id INT AUTO_INCREMENT PRIMARY KEY,
    room_id INT,
    request_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);


CREATE TABLE service_request_details (
    detail_id INT AUTO_INCREMENT PRIMARY KEY,
    request_id INT,
    service_id INT,
    quantity INT DEFAULT 1,
    FOREIGN KEY (request_id) REFERENCES service_requests(request_id),
    FOREIGN KEY (service_id) REFERENCES services(service_id)
);


CREATE TABLE checkouts (
    checkout_id INT AUTO_INCREMENT PRIMARY KEY,
    booking_id INT,
    checkout_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2),
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
);
