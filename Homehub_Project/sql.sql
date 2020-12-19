use homehub;


Create table services
(
servicetype varchar(40),
id varchar(500),
servicename varchar(500),
serviceprice double,
serviceimage varchar(500),
url varchar(500),
rating varchar(40),
latitude varchar(40),
longitude double,
address varchar(300),
city varchar(40),
zipcode varchar(20),
state varchar(30),
phonenumber varchar(20),
discount varchar(20),
Primary key(id)
);


Create table product
(
category varchar(20),
product_id varchar(15),
product_name varchar(40),
productPrice numeric(20,0),
productImage varchar(40),
productManufacturer varchar(40),
productCondition varchar(40),
productDiscount double,
rating varchar(40),
Primary key(product_id)
);

create table address (
       		address_id  	              	varchar(15),
            customer_id						varchar(15),
            staff_id						varchar(15),
            warehouse_id					varchar(15),	
       		apt_no         	              	int,
       		street_number             	int,
       		street_name                 	varchar(30),
       		city                	        varchar(20),
       		state             	        varchar(20),
       		zip                 	        varchar(10),
			address_type			varchar(10),
       		primary key (address_id)
);



create table customer (
			customer_id  	           	varchar(15),
       		name            	        varchar(35),
			username					varchar(35),
			password					varchar(35),
            address_id					varchar(15),
       		age               	        numeric(2,0),
			outstanding_balance    		numeric(10,2),
       		primary key(customer_id),
            foreign key (address_id) references address(address_id) ON DELETE SET NULL ON UPDATE CASCADE
);



create table staff (
       		staff_ID                   	varchar(15),
       		name                        varchar(20) not null,
			username					varchar(35),
			password					varchar(35),
       		address_ID                 	varchar(15),
       		salary                      numeric(8,2),
       		job_title                   varchar(20) not null,
       		primary key (staff_ID),
       		foreign key (address_ID) references address(address_ID) ON DELETE SET NULL ON UPDATE CASCADE
);



INSERT INTO staff(staff_ID, name, username, password, address_ID, salary, job_title)
VALUES('1', 'James', 'storem', 'a', null, 8000.00, 'StoreManager'),
	('2', 'Marie', 'salesm', 'a',null , 6000.00, 'Salesman');



create table credit_card (
       		customer_id                 	varchar(15),
       		card_number                	numeric(16,0),
       		address_id  	              	varchar(15),
       		primary key(card_number),
       		foreign key(customer_id) references customer(customer_id) ON DELETE SET NULL ON UPDATE CASCADE,
       		foreign key(address_id) references address(address_id) ON DELETE SET NULL ON UPDATE CASCADE
);

delete from orders;
create table orders (
			order_id      	              	varchar(15),
			customer_id		               	varchar(15),
			card_number                		numeric(16,0),
			issued_date	              		DATE DEFAULT (CURRENT_DATE),
			status                      	varchar(10) not null,
            userAddress 				varchar(100),
            deliveryDate 				varchar(40),
            deliveryType 				varchar(40),
            orderreturned				varchar(40),
            outstanding_balance      	numeric(10,2),
			primary key (order_id),
			foreign key (customer_id) references customer(customer_id) ON DELETE SET NULL ON UPDATE CASCADE
);


create table orders_products (
           	id SERIAL NOT NULL,
       		order_id      	              	varchar(15),
       		product_id  	              	varchar(15),
            review_rating					varchar(15),
       		quantity       	              	numeric(2,0) default 1,
			price_per_unit					numeric(20,0),	
            product_name varchar(40),
           	primary key (id),
       		foreign key (order_id) references orders (order_id) ON DELETE SET NULL ON UPDATE CASCADE,
       		foreign key (product_id) references product(product_id) ON DELETE SET NULL ON UPDATE CASCADE
);


create table orders_services (
           	id SERIAL NOT NULL,
       		order_id      	        varchar(15),
       		service_id  	              	varchar(500),
            review_rating					varchar(15),
			price					numeric(20,0),	
            servicename varchar(500),
           	primary key (id),
       		foreign key (order_id) references orders(order_id) ON DELETE SET NULL ON UPDATE CASCADE,
       		foreign key (service_id) references services(id) ON DELETE SET NULL ON UPDATE CASCADE
);

create table shopping_cart_products (
       		customer_id                	varchar(15),
       		product_id                	varchar(15),
       		product_name          	   	varchar(20),
       		quantity                   	numeric(2,0) default 1,
			price_per_unit				numeric(20,0),
       		primary key (customer_id, product_id),
     	  	foreign key (customer_id) references customer(customer_id) ON DELETE CASCADE,
       		foreign key (product_id) references product(product_id) ON DELETE CASCADE
);




create table shopping_cart_services (
       		customer_ID                	varchar(15),
       		id		                	varchar(500),
       		servicename          	   	varchar(500),
       		quantity                   	numeric(2,0) default 1,
			price_per_unit				numeric(20,0),
       		primary key (customer_id, id),
     	  	foreign key (customer_id) references customer(customer_id) ON DELETE CASCADE ON UPDATE CASCADE,
       		foreign key (id) references services(id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table Transactions
(
	login_ID varchar(40), 
	Customer_Name varchar(40),
    Customer_Age numeric(2,0), 
    Customer_Occupation varchar(40), 
    Credit_Card_Number varchar(40), 
    Order_ID int, 
    Order_Date date, 
    Expected_Delivery_Date date,
    Actual_Delivery_Date date,
    Product_ID varchar(500), 
    Product_Name varchar(500),
    Review_Rating varchar(40),
    Delivery_Tracking_ID varchar(40),
    Delivery_Type varchar(40),
    Delivery_Zip_Code varchar(40),
    Transaction_Status varchar(40),
    Order_Returned int,
    Order_Delivered_on_Time int
);



