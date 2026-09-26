CREATE TABLE location (
    location_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(20) NOT NULL,
    parent_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (parent_id) REFERENCES location(location_id)
);
CREATE TABLE hotel (
    hotel_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    location_id BIGINT,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    address VARCHAR(255) NOT NULL,
    star_rating DECIMAL(2,1),
    amenities TEXT,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (location_id) REFERENCES location(location_id),
    CHECK (star_rating IS NULL OR (star_rating >= 0 AND star_rating <= 5))
);
CREATE TABLE room (
    room_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    hotel_id BIGINT NOT NULL,
    room_number VARCHAR(20) NOT NULL,
    room_type VARCHAR(50) NOT NULL,
    capacity INT NOT NULL,
    base_price DECIMAL(12,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id),
    CHECK (capacity > 0),
    CHECK (base_price >= 0)
);
CREATE TABLE booking (
    booking_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    hotel_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    guests INT NOT NULL,
    total_amount DECIMAL(12,2) NOT NULL,
    booking_status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES `user`(user_id),
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id),
    FOREIGN KEY (room_id) REFERENCES room(room_id),
    CHECK (check_out_date > check_in_date),
    CHECK (guests > 0),
    CHECK (total_amount >= 0)
);
CREATE TABLE payment (
    payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    booking_id BIGINT NOT NULL,
    amount DECIMAL(12,2) NOT NULL,
    payment_status VARCHAR(20) NOT NULL,
    transaction_ref VARCHAR(100) UNIQUE,
    paid_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (booking_id) REFERENCES booking(booking_id),
    CHECK (amount >= 0)
);
CREATE TABLE review (
    review_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    hotel_id BIGINT NOT NULL,
    rating INT NOT NULL,
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id),
    CHECK (rating BETWEEN 1 AND 5)
);
CREATE TABLE hotel_image (
    image_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    hotel_id BIGINT NOT NULL,
    image_url VARCHAR(500) NOT NULL,
    caption VARCHAR(255),

    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id)
);
SHOW TABLES;
USE hotel_booking_system;
SELECT * FROM user;
USE hotel_booking_system;
SELECT * FROM user;

USE hotel_booking_system;

SELECT hotel_id, name
FROM hotel;
SELECT hotel_id, name FROM hotel;

USE hotel_booking_system;

INSERT INTO hotel
(location_id, name, description, address, star_rating, amenities, status)
VALUES
(1,
 'Test Hotel',
 'Test hotel for Room DAO',
 'Hyderabad',
 5.0,
 'WiFi, Parking',
 'ACTIVE');
 
 SELECT hotel_id, location_id, name
FROM hotel;

USE hotel_booking_system;

SELECT user_id, username, email
FROM user;

SELECT hotel_id, name
FROM hotel;

SELECT room_id, hotel_id, room_number, room_type
FROM room;

USE hotel_booking_system;

DESCRIBE user;
SELECT * FROM user;

USE hotel_booking_system;

INSERT INTO user
(full_name, email, password_hash, phone, role, status)
VALUES
('Booking Test User',
 'bookingtest@gmail.com',
 'test_password_hash',
 '9876543210',
 'CUSTOMER',
 'ACTIVE');
 
 SELECT user_id, full_name, email, role, status
FROM user;
SELECT room_id, hotel_id, room_number, room_type, capacity, base_price, status
FROM room;
USE hotel_booking_system;

INSERT INTO room
(hotel_id, room_number, room_type, capacity, base_price, status)
VALUES
(2, '101', 'DELUXE', 2, 250.00, 'AVAILABLE');

SELECT room_id, hotel_id, room_number, room_type,
       capacity, base_price, status
FROM room;
SELECT hotel_id, name
FROM hotel;

USE hotel_booking_system;

SELECT * FROM booking;
SELECT *FROM hotel;
SELECT *FROM room;

