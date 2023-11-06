CREATE DATABASE db_braine_lomoni_168864;

USE db_braine_lomoni_168864;

-- Create the tables
CREATE TABLE tbl_users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(100) NOT NULL UNIQUE,
    user_password VARCHAR(200) NOT NULL,
    user_type VARCHAR(200) NOT NULL
);

CREATE TABLE tbl_inventory (
    inventory_id INT PRIMARY KEY AUTO_INCREMENT,
    medicine_name VARCHAR(100) NOT NULL UNIQUE,
    medicine_quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

CREATE TABLE tbl_prescription (
    prescription_id INT PRIMARY KEY AUTO_INCREMENT,
    medicine_name VARCHAR(200) NOT NULL,
    medicine_inventory_id INT,
    patient_birth_certificate INT,
    frequency VARCHAR(20) NOT NULL,
    dosage VARCHAR(200) NOT NULL,
    FOREIGN KEY (medicine_inventory_id) REFERENCES tbl_inventory(inventory_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE tbl_prescription_items (
    prescription_id INT NOT NULL,
    medicine_inventory_id INT NOT NULL,
    frequency VARCHAR(20) NOT NULL DEFAULT '1',
    dosage VARCHAR(200) NOT NULL DEFAULT '1',
    PRIMARY KEY (prescription_id, medicine_inventory_id),
    FOREIGN KEY (prescription_id) REFERENCES tbl_prescription(prescription_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (medicine_inventory_id) REFERENCES tbl_inventory(inventory_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE tbl_transaction_items (
    transaction_id INT NOT NULL,
    medicine_inventory_id INT NOT NULL,
    dosage VARCHAR(200) NOT NULL,
    prescription_id INT,
    date_of_dispensing TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total_cost INT NOT NULL,
    PRIMARY KEY (transaction_id, medicine_inventory_id),
    FOREIGN KEY (medicine_inventory_id) REFERENCES tbl_inventory(inventory_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (prescription_id) REFERENCES tbl_prescription(prescription_id) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE tbl_medicines (
    medicine_id INT PRIMARY KEY AUTO_INCREMENT,
    medicine_name VARCHAR(100) NOT NULL UNIQUE
);

-- Insert some sample data
INSERT INTO tbl_users (user_name, user_password, user_type) VALUES ('admin', 'password', 'admin');
INSERT INTO tbl_inventory (medicine_name, medicine_quantity, price) VALUES ('Paracetamol', 100, 10);
INSERT INTO tbl_prescription (medicine_name, medicine_inventory_id, patient_birth_certificate, frequency, dosage) VALUES ('Paracetamol', 1, 123456789, '2x a day', '1 tablet');
INSERT INTO tbl_prescription_items (prescription_id, medicine_inventory_id, frequency, dosage) VALUES (1, 1, '2x a day', '1 tablet');
INSERT INTO tbl_transaction_items (transaction_id, medicine_inventory_id, dosage, prescription_id, date_of_dispensing, total_cost) VALUES (1, 1, '1 tablet', 1, '2023-11-06', 10);
INSERT INTO tbl_medicines (medicine_name) VALUES ('Paracetamol');
