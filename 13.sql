-- database: ./exsm_3937_a3.db
PRAGMA foreign_keys = ON;
Drop table if exists orders;
DROP TABLE IF EXISTS customer;

Create Table if not exists customer (
    id integer primary key AUTOINCREMENT,
    name text,
    email text,
    street_address text,
    city text,
    province text,
    postal_code text,
    -- order_date text,
    -- customer_id number,
    -- FOREIGN KEY (customer_id)  REFERENCES customer(id) on DELETE CASCADE on UPDATE CASCADE

);

Create Table if not exists product (
    id integer primary key AUTOINCREMENT,
    name text,
    description text,
    price real,
    quantity_in_stock number,
    --province text,
    --postal_code text,
    -- order_date text,
    -- customer_id number,
    -- FOREIGN KEY (customer_id)  REFERENCES customer(id) on DELETE CASCADE on UPDATE CASCADE

);

--Create table if not exists customer(
--    id integer PRIMARY KEY AUTOINCREMENT,
--    name text,
--    address text,
--    phone_number text,
--    join_date text

--);
Create Table if not exists orders_header (
    id integer primary key AUTOINCREMENT,
    order_date text,
    total_price real,
    customer_id number,
    FOREIGN KEY (customer_id)  REFERENCES customer(id) on DELETE CASCADE on UPDATE CASCADE

);


Create Table if not exists orders_detail (
    id integer primary key AUTOINCREMENT,
    order_qty number,
    sub_total real,
    product_id number,
    order_header_id,
    FOREIGN KEY (product_id)  REFERENCES product(id) on DELETE CASCADE on UPDATE CASCADE,
FOREIGN KEY (order_header_id)  REFERENCES orders_header(id) on DELETE CASCADE on UPDATE CASCADE
);



Insert Script

INSERT INTO Customer (customer_name, customer_email, street_address, city, province, postal_code)

    VALUES 

        ('Aaron Champagne', 'achampag@ualberta.ca', '2317 138 A Avenue', 'Edmonton', 'AB', 'T5Y1B9'),

        ('James Grieve', 'jgrieve@ualberta.ca', '1234 123 Street', 'Edmonton', 'AB', 'T2B1G4'),

        ('Bo Cen', 'bcen@ualberta.ca', '5672 98 Avenue', 'Edmonton', 'AB', 'T3C4B7'),

        ('Stephanie Smothers', 'ssmoth@ualberta.ca', NULL, NULL, NULL, NULL),

        ('Emily Nelson', NULL, '1 Winston Churchill Square', 'Edmonton', 'AB', 'T4A1B7'),

        ('Sean Townsend', 'stown@ualberta.ca', NULL, NULL, NULL, NULL),

        ('Diana Hyland', NULL, NULL, NULL, NULL, NULL),

        ('Dennis Nylon', 'dnylon@ualberta.ca', '1298 76 Street', 'Edmonton', 'AB', 'T5R6F8'),

        ('Chloe Beale', NULL, '7393 78 Ave', 'Edmonton', 'AB', 'T8FW7C');


INSERT INTO Product (product_name, product_description, price, quantity_in_stock)

    VALUES

        ('Guitar', 'An acoustic guitar made by Epiphone.', 375.99, 5),

        ('Microphone', 'A Shure microphone ideal for stage.', 276.5, 3),

        ('Tambourine', 'Mother of pearl handle.', 23.60, 15),

        ('Bass Guitar', 'A four-string, fretless bass guitar by Ibanez.', 450.25, 2),

        ('Electric Guitar', 'An electric guitar made by Epiphone', 775.99, 1),

        ('MIDI Keyboard', 'A two-octave keyboard with USB cable for making digital music.', 550, 4),

        ('50-Watt Amplifier', 'A medium sized amp by Marshall.', 425.99, 6);


INSERT INTO Order_Header (customer_id, order_date, total_price)

    VALUES

        (1, '2022-01-01', 775.99),

        (2, '2022-01-02', 1177.97),

        (3, '2022-01-04', 1103),

        (1, '2022-01-12', 23.6),

        (4, '2022-01-14', 876.24),

        (5, '2022-01-20', 1951.98),

        (6, '2022-01-22', 375.99);

INSERT INTO Order_Detail (order_header_id, product_id, order_qty, sub_total)

    VALUES

        (1, 5, 1, 775.99),

        (2, 1, 2, 751.98),

        (2, 7, 1, 425.99),

        (3, 6, 1, 550),

        (3, 2, 2, 553),

        (4, 3, 1, 23.6),

        (5, 4, 1, 450.25),

        (5, 7, 1, 425.99),

        (6, 6, 2, 1100),

        (6, 7, 2, 851.98),

        (7, 1, 1, 375.99);



-- A query which locates the names and addresses of all customers who have placed an order above $50.00.
SELECT customer.name, customer.address
FROM customer
LEFT JOIN orders
    ON customer.id = orders.customer_id
    where orders.total_cost > 50.00;

-- A query which locates the names and addresses of all customers who placed an order on the same day they joined as a customer.
SELECT customer.name, customer.address
FROM customer
LEFT JOIN orders
    ON customer.id = orders.customer_id
    where orders.order_date = customer.join_date;

-- A query which locates the names and addresses of all customers who placed an order in the current year (there is a function to find the current year to make your query dynamic), with the results outputted in customer name alphabetical order.

SELECT customer.name, customer.address
FROM customer
LEFT JOIN orders
    ON customer.id = orders.customer_id
   
WHERE orders.order_date BETWEEN '2025-01-01 00:00:00' AND '2025-12-31 23:59:59';
