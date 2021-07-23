CREATE TABLE IF NOT EXISTS `Stock`(
`Product` int,
`Location` int,
`Quantity` int,
FOREIGN KEY (Product) REFERENCES Product(Id),
FOREIGN KEY (Location) REFERENCES Location(Id),
CONSTRAINT uq_stock UNIQUE(Product,Location)
)