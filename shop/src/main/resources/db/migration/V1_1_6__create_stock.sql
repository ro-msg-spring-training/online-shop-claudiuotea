CREATE TABLE IF NOT EXISTS `Stock`(
`product` int,
`location` int,
`quantity` int,
FOREIGN KEY (product) REFERENCES Product(id),
FOREIGN KEY (location) REFERENCES Location(id),
CONSTRAINT uq_stock UNIQUE(product,location)
)