CREATE DATABASE db_braine_lomoni_168864_lb_4;

USE db_braine_lomoni_168864_lb_4;

CREATE TABLE Orders(
	OrderNo INT AUTO_INCREMENT,
    Cust INT NOT NULL,
    Product VARCHAR(50) NOT NULL,
    Quantity INT NOT NULL,
    Amt INT NOT NULL,
    Disc DECIMAL(10,2) NOT NULL,
    PRIMARY KEY(OrderNo),
    FOREIGN KEY (Cust) REFERENCES Customers(CustNo)
);

CREATE TABLE Customers(
	CustNo INT NOT NULL,
    Company VARCHAR(50) NOT NULL,
    CustRep INT NOT NULL,
    CreditLimit INT NOT NULL,
    PRIMARY KEY (CustNo),
    FOREIGN KEY (CustRep) REFERENCES SalesRep(RepNo)
);

CREATE TABLE SalesRep(
	RepNo INT NOT NULL,
    `Name` VARCHAR(50) NOT NULL,
    RepOffice INT NOT NULL,
    Quota INT NOT NULL,
    Sales INT NOT NULL,
    PRIMARY KEY (RepNo),
    FOREIGN KEY (RepOffice) REFERENCES Offices(OfficeNo)
);

CREATE TABLE Offices(
	OfficeNo INT NOT NULL,
    City VARCHAR(50) NOT NULL,
    State CHAR(5) NOT NULL,
    Region VARCHAR(50) NOT NULL,
    Target BIGINT NOT NULL,
    Sales INT NOT NULL,
    Phone INT NOT NULL,
    PRIMARY KEY (OfficeNo)
);


 