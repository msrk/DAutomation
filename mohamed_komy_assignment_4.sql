-- database: ./exsm_3937_a3.db
PRAGMA foreign_keys = ON;
Drop table if exists orders;
DROP TABLE IF EXISTS customer;
Create table if not exists customer(
    id integer PRIMARY KEY AUTOINCREMENT,
    name text,
    address text,
    phone_number text,
    join_date text

);
Create Table if not exists orders (
    id integer primary key AUTOINCREMENT,
    amount number,
    total_cost real,
    order_date text,
    customer_id number,
    FOREIGN KEY (customer_id)  REFERENCES customer(id) on DELETE CASCADE on UPDATE CASCADE

);







insert into customer (name, address, phone_number, join_date)
values ('Jane Doe', '123 Main Street','780-123-4567', '2021-02-02 11:00:00'),
('Li Wei', '985 Boardwalk','406-158-4198', '2001-12-02 11:00:00'),
('Jane Simons', '523 Example Way','158-365-4589', '2022-12-01 11:00:00'),
('John Doe', '123 Main Street','780-123-4567', '2000-09-23 11:00:00');


insert into orders (amount, total_cost, order_date, customer_id)
values ( 5,25.00, '2021-02-02 11:00:00',1),
( 20,100.00, '2025-07-03 11:00:00',1),
( 9,45.00, '2025-07-02 11:00:00',2),
( 11,55.00, '2022-09-23 11:00:00',4);



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
