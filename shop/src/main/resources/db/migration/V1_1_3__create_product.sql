CREATE TABLE IF NOT EXISTS `Product`(

`Id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`Name` varchar(30),
`Description` varchar(30),
`Price` decimal,
`Weight` float,
`ProductCategory` int,
`Supplier` int,
`ImageUrl` varchar(30),
FOREIGN KEY (ProductCategory) REFERENCES ProductCategory(Id),
FOREIGN KEY (Supplier) REFERENCES Supplier(Id),
CONSTRAINT uq_product UNIQUE(Name)
)