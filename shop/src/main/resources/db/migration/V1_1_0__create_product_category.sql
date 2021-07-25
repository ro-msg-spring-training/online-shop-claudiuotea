CREATE TABLE IF NOT EXISTS `Product_Category`(

`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` varchar(30),
`description` varchar(30),
CONSTRAINT uq_product_category UNIQUE(name)
)