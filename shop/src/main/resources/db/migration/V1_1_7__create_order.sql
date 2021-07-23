CREATE TABLE IF NOT EXISTS `OrderTable`(
`Id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`ShippedFrom` int,
`Customer` int,
`CreatedAt` timestamp,
`Address` int,
FOREIGN KEY (ShippedFrom) REFERENCES Location(Id),
FOREIGN KEY (Customer) REFERENCES Customer(Id),
FOREIGN KEY (Address) REFERENCES Address(Id),
CONSTRAINT uq_order UNIQUE(Customer,CreatedAt)
)