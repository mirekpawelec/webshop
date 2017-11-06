CREATE TABLE product (
product_id int not null auto_increment,
product_no varchar(20) not null,
name varchar(50) not null,
manufacturer varchar(50) not null,
category varchar(25) not null,
description text not null,
unitPrice decimal(9,2) not null,
quantityInBox int(4) not null,
is_promotion varchar(1) default 'N',
discount int(3) default 0,
status varchar(2) default 'OK',
createDate timestamp default current_timestamp,
PRIMARY KEY (product_id),
unique key product_no (product_no)
) ENGINE=InnoDB;


CREATE TABLE repository(
loadunit_id int not null auto_increment,
loadunit_no varchar(10) not null,
product_id int not null,
quantity int not null,
place_id int not null,
state varchar(25) default 'NEW',
qualityStatus int not null default 0,
status varchar(2) default 'OK',
lm_date timestamp default current_timestamp,
c_date timestamp default current_timestamp,
PRIMARY KEY(loadunit_id),
UNIQUE KEY loadunit_no (loadunit_no),
KEY repository_product (product_id),
KEY repository_storageplace (place_id),
CONSTRAINT repository_product FOREIGN KEY (product_id) REFERENCES product (product_id),
CONSTRAINT repository_storageplace FOREIGN KEY (place_id) REFERENCES storageplace (place_id)
) ENGINE=InnoDB;


CREATE TABLE storageplace(
place_id int not null auto_increment,
place_no varchar(25) not null,
name varchar(100) not null,
area_id int not null,
type varchar(25) default 'BLOCK',
height int(4),
width int(4),
depth int(4),
volume int(6.2),
status varchar(2) default 'OK',
c_date timestamp default current_timestamp,
PRIMARY KEY (place_id),
UNIQUE KEY (place_no),
KEY storageplace_storagearea (area_id),
CONSTRAINT storageplace_storagearea FOREIGN KEY (area_id) REFERENCES storagearea (area_id)
) ENGINE=InnoDB;


CREATE TABLE storagearea(
area_id int not null auto_increment,
name varchar(100) not null,
description varchar(255),
c_date timestamp default current_timestamp,
PRIMARY KEY (area_id),
UNIQUE KEY (name)
)ENGINE = InnoDB;


CREATE TABLE delivery(
delivery_id int not null auto_increment,
place_id int not null,
description varchar(255),
driver_name1 varchar(25),
driver_name2 varchar(25),
driver_phone varchar(25),
truck_type varchar(25),
truck_number1 varchar(25),
truck_number2 varchar(25),
c_user varchar(50),
status varchar(2) default 'RE',
c_date varchar(20),
f_date varchar(20),
PRIMARY KEY (delivery_id),
KEY delivery_storageplace (place_id),
CONSTRAINT delivery_storageplace FOREIGN KEY (place_id) REFERENCES storageplace (place_id)
)ENGINE = InnoDB;


CREATE TABLE delivery_item(
item_id int not null auto_increment,
delivery_id int not null,
loadunit_no varchar(10) not null,
product_id int not null,
quantity int not null,
status varchar(2) default 'OK',
c_date timestamp default current_timestamp,
PRIMARY KEY (item_id),
UNIQUE KEY (loadunit_no),
KEY deliveryItem_delivery (delivery_id),
KEY deliveryItem_product (product_id),
CONSTRAINT deliveryItem_delivery FOREIGN KEY (delivery_id) REFERENCES delivery (delivery_id),
CONSTRAINT deliveryItem_product FOREIGN KEY (product_id) REFERENCES product (product_id)
)ENGINE = InnoDB;


CREATE TABLE cart(
cart_id int not null auto_increment,
session_id varchar(100) not null,
user_id int,
status varchar(2) default 'OK',
lm_date datetime default current_timestamp,
c_date datetime default current_timestamp,
PRIMARY KEY(cart_id),
KEY cart_users (user_id),
CONSTRAINT cart_users FOREIGN KEY (user_id) REFERENCES users (user_id)
)ENGINE = InnoDB;


CREATE TABLE cart_items(
id int not null auto_increment,
cart_id int not null,
product_id int not null,
quantity int not null,
total_price decimal(9,2) not null,
status varchar(2) default 'OK',
lm_date datetime default current_timestamp,
c_date datetime default current_timestamp,
PRIMARY KEY (id),
KEY cart_items_cart (cart_id),
KEY cart_items_product (product_id),
CONSTRAINT cart_items_cart FOREIGN KEY (cart_id) REFERENCES cart (cart_id) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT cart_item_product FOREIGN KEY (product_id) REFERENCES product (product_id)
)ENGINE = InnoDB;


CREATE TABLE customers(
customer_id int not null auto_increment,
first_name varchar(25) not null,
last_name varchar(25) not null,
phone_number varchar(15) not null,
email varchar(50) not null,
address_id int,
status varchar(2) default 'OK',
c_date datetime default current_timestamp,
PRIMARY KEY (customer_id),
CONSTRAINT customers_address FOREIGN KEY (address_id) REFERENCES address (address_id)
) ENGINE = InnoDB;
 
;
CREATE TABLE address(
address_id int not null auto_increment,
door_no varchar(10) not null,
street_name varchar(25) not null,
area_name varchar(25) not null,
state varchar(25) not null,
country varchar(25) not null,
zip_code varchar(10) not null,
status varchar(2) default 'OK',
c_date datetime default current_timestamp,
PRIMARY KEY (address_id)
) ENGINE = InnoDB;


CREATE TABLE shipping_address(	
ship_address_id int not null auto_increment,
name varchar(100) not null,
phone_number varchar(15) not null,
shipping_date date,
address_id int,
c_date datetime default current_timestamp,
PRIMARY KEY (ship_address_id),
KEY shipping_address_address (address_id),
CONSTRAINT shipping_address_address FOREIGN KEY (address_id) REFERENCES address (address_id)
)ENGINE = InnoDB;


CREATE TABLE shipping_details(
ship_detail_id int not null auto_increment,
payment_method varchar(50) not null,
payment_cost int(6.2) not null,
delivery_method varchar(50) not null,
delivery_cost int(6.2) not null,
c_date datetime default current_timestamp,
PRIMARY KEY (ship_detail_id)
) ENGINE = InnoDB;


CREATE TABLE orders(
order_id int not null auto_increment,
cart_id int,
customer_id int,
shipping_address_id int,
shipping_details_id int,
status varchar(2) default 'OK',
lm_date datetime default current_timestamp,
c_date datetime default current_timestamp,
PRIMARY KEY (order_id),
UNIQUE KEY orders_cart (cart_id),
KEY orders_customers (customer_id),
KEY orders_shipping_address (shipping_address_id),
KEY orders_shipping_details (shipping_details_id),
CONSTRAINT orders_cart FOREIGN KEY (cart_id) REFERENCES cart (cart_id) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT orders_customers FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
CONSTRAINT orders_shipping_address FOREIGN KEY (shipping_address_id) REFERENCES shipping_address (ship_address_id),
CONSTRAINT orders_shipping_details FOREIGN KEY (shipping_details_id) REFERENCES shipping_details (ship_detail_id)
) ENGINE = InnoDB;


CREATE TABLE users (
user_id int not null auto_increment,
login varchar(50) not null,
passwd varchar(100) not null,
first_name varchar(25),
last_name varchar(25),
email varchar(50),
role varchar(50) not null,
status varchar(2) default 'OK',
last_login datetime,
lm_date datetime default current_timestamp,
c_date datetime default current_timestamp,
PRIMARY KEY (user_id),
UNIQUE KEY (login)
)ENGINE = InnoDB;


CREATE TABLE messages(
message_id int not null auto_increment,
name varchar(50) not null,
email varchar(50) not null,
subject varchar(50),
content text not null,
status varchar(2) default 'OK',
c_date datetime default current_timestamp,
PRIMARY KEY (message_id)
)ENGINE = InnoDB;


CREATE TABLE app_parameter(
parameter_id int not null auto_increment,
symbol varchar(50) not null,
name varchar(50) not null,
value varchar(25) not null,
description varchar(250),
lm_date datetime default current_timestamp,
c_date datetime default current_timestamp,
PRIMARY KEY (class_id),
UNIQUE KEY (symbol, name)
)ENGINE = InnoDB;
