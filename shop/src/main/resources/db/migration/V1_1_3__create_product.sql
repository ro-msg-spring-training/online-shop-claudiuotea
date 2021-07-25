CREATE TABLE IF NOT EXISTS `Product`(

`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` varchar(30),
`description` varchar(30),
`price` decimal,
`weight` float,
`product_category` int,
`supplier` int,
`image_url` varchar(30),
FOREIGN KEY (product_category) REFERENCES Product_Category(Id),
FOREIGN KEY (supplier) REFERENCES Supplier(Id),
CONSTRAINT uq_product UNIQUE(name)
)