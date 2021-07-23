CREATE TABLE IF NOT EXISTS `ProductCategory`(

`Id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`Name` varchar(30),
`Description` varchar(30),
CONSTRAINT uq_product_category UNIQUE(Name)
)