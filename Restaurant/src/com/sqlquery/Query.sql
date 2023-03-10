CREATE DATABASE RESTAURANT;

CREATE TABLE CUSTOMER(
CUSTOMER_ID VARCHAR(7) PRIMARY KEY,
CUSTOMER_NAME VARCHAR(20) NOT NULL,
GENDER VARCHAR(7) NOT NULL,
EMAIL VARCHAR(20) NOT NULL,
ADDRESS VARCHAR(30) NOT NULL,
MOBILE_NUMBER BIGINT );

CREATE TABLE MENU(
PRODUCT_ID VARCHAR(45) PRIMARY KEY,
PRODUCT_NAME VARCHAR(45) NOT NULL,
PRICE DECIMAL NOT NULL);

CREATE TABLE SALES(
CUSTOMER_ID VARCHAR(8),
ORDER_DATE DATE NOT NULL,
PRODUCT_ID VARCHAR(45),
FOREIGN KEY(CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID),
FOREIGN KEY(PRODUCT_ID) REFERENCES MENU(PRODUCT_ID));

CREATE TABLE MEMBERS(
CUSTOMER_ID VARCHAR(45) PRIMARY KEY,
JOIN_DATE DATE NOT NULL);
