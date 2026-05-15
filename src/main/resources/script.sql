-- ==========================================================
-- Local Test Database Script for E-Commerce Project
-- Purpose:
-- This script creates required tables and inserts sample data.
-- It is useful for local testing so that we do not need to
-- create test data again and again from Swagger or Postman.
-- ==========================================================


-- =========================
-- Drop tables if needed
-- =========================
-- Uncomment these only when you want a fresh database.
-- DROP TABLE IF EXISTS tb_order_items;
-- DROP TABLE IF EXISTS tb_orders;
-- DROP TABLE IF EXISTS tb_products;
-- DROP TABLE IF EXISTS tb_users;
-- DROP TABLE IF EXISTS tb_order_status;
-- DROP TABLE IF EXISTS tb_roles;


-- =========================
-- Create Roles Table
-- =========================
CREATE TABLE IF NOT EXISTS tb_roles (
    role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);


-- =========================
-- Create Users Table
-- =========================
CREATE TABLE IF NOT EXISTS tb_users (
    user_id CHAR(36) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role_id BIGINT NOT NULL,
    phone_number VARCHAR(20) NOT NULL UNIQUE,

    created_by VARCHAR(50),
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_tb_users_roles
        FOREIGN KEY (role_id) REFERENCES tb_roles(role_id)
        ON DELETE RESTRICT
);


-- =========================
-- Create Products Table
-- =========================
CREATE TABLE IF NOT EXISTS tb_products (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,

    created_by VARCHAR(50),
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


-- =========================
-- Create Order Status Table
-- =========================
CREATE TABLE IF NOT EXISTS tb_order_status (
    status_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    status_name VARCHAR(50) NOT NULL UNIQUE
);


-- =========================
-- Create Orders Table
-- =========================
CREATE TABLE IF NOT EXISTS tb_orders (
    order_id CHAR(36) PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    status_id BIGINT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    total_quantity INT NOT NULL,

    created_by VARCHAR(50),
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_tb_orders_users
        FOREIGN KEY (user_id) REFERENCES tb_users(user_id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_tb_orders_status
        FOREIGN KEY (status_id) REFERENCES tb_order_status(status_id)
        ON DELETE RESTRICT
);


-- =========================
-- Create Order Items Table
-- =========================
CREATE TABLE IF NOT EXISTS tb_order_items (
    order_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id CHAR(36) NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,

    CONSTRAINT fk_tb_order_items_orders
        FOREIGN KEY (order_id) REFERENCES tb_orders(order_id),

    CONSTRAINT fk_tb_order_items_products
        FOREIGN KEY (product_id) REFERENCES tb_products(product_id)
);


-- =========================
-- Create Indexes
-- =========================
CREATE INDEX idx_tb_orders_user ON tb_orders(user_id);
CREATE INDEX idx_tb_orders_status ON tb_orders(status_id);
CREATE INDEX idx_tb_orders_created_by ON tb_orders(created_by);
CREATE INDEX idx_tb_products_price ON tb_products(price);
CREATE INDEX idx_tb_products_quantity ON tb_products(quantity);


-- =========================
-- Insert Roles
-- =========================
INSERT INTO tb_roles (role_id, role_name)
VALUES
(1, 'ADMIN'),
(2, 'USER'),
(3, 'MANAGER'),

ON DUPLICATE KEY UPDATE
role_name = VALUES(role_name);


-- =========================
-- Insert Order Status
-- =========================
INSERT INTO tb_order_status (status_id, status_name)
VALUES
(1, 'PLACED'),
(2, 'CANCELLED'),
(3, 'DELIVERED')
ON DUPLICATE KEY UPDATE
status_name = VALUES(status_name);


-- =========================
-- Insert Sample Users
-- =========================
INSERT INTO tb_users (
    user_id,
    username,
    password,
    email,
    role_id,
    phone_number,
    created_by,
    updated_by
)
VALUES
(
    '11111111-1111-1111-1111-111111111111',
    'admin',
    '$2a$10$dummyEncryptedPasswordForAdmin',
    'admin@gmail.com',
    1,
    '9999999999',
    'system',
    'system'
),
(
    '22222222-2222-2222-2222-222222222222',
    'kavita',
    '$2a$10$dummyEncryptedPasswordForUser',
    'kavita@gmail.com',
    2,
    '8888888888',
    'system',
    'system'
),
(
    '33333333-3333-3333-3333-333333333333',
    'rahul',
    '$2a$10$dummyEncryptedPasswordForUser',
    'rahul@gmail.com',
    2,
    '7777777777',
    'system',
    'system'
)
ON DUPLICATE KEY UPDATE
username = VALUES(username),
password = VALUES(password),
email = VALUES(email),
role_id = VALUES(role_id),
phone_number = VALUES(phone_number),
updated_by = VALUES(updated_by);


-- =========================
-- Insert Sample Products
-- =========================
INSERT INTO tb_products (
    product_id,
    name,
    description,
    price,
    quantity,
    created_by,
    updated_by
)
VALUES
(1, 'Laptop', 'HP laptop used for testing order flow', 40000.00, 15, 'system', 'system'),
(2, 'Mobile', 'Samsung A35 5G phone', 20000.00, 30, 'system', 'system'),
(3, 'Keyboard', 'Mechanical keyboard sample product', 1500.00, 50, 'system', 'system'),
(4, 'Mouse', 'Wireless mouse sample product', 800.00, 60, 'system', 'system'),
(5, 'Headphones', 'Bluetooth headphones sample product', 2500.00, 25, 'system', 'system')
ON DUPLICATE KEY UPDATE
name = VALUES(name),
description = VALUES(description),
price = VALUES(price),
quantity = VALUES(quantity),
updated_by = VALUES(updated_by);


-- =========================
-- Insert Sample Orders
-- =========================
INSERT INTO tb_orders (
    order_id,
    user_id,
    status_id,
    total_amount,
    total_quantity,
    created_by,
    updated_by
)
VALUES
(
    'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
    '22222222-2222-2222-2222-222222222222',
    1,
    80000.00,
    2,
    'kavita',
    'kavita'
),
(
    'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb',
    '33333333-3333-3333-3333-333333333333',
    1,
    21500.00,
    2,
    'rahul',
    'rahul'
)
ON DUPLICATE KEY UPDATE
user_id = VALUES(user_id),
status_id = VALUES(status_id),
total_amount = VALUES(total_amount),
total_quantity = VALUES(total_quantity),
updated_by = VALUES(updated_by);


-- =========================
-- Insert Sample Order Items
-- =========================
INSERT INTO tb_order_items (
    order_item_id,
    order_id,
    product_id,
    quantity
)
VALUES
(1, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 1, 2),
(2, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 2, 1),
(3, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 3, 1)
ON DUPLICATE KEY UPDATE
order_id = VALUES(order_id),
product_id = VALUES(product_id),
quantity = VALUES(quantity);