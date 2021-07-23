CREATE TABLE IF NOT EXISTS `OrderDetail`(
`OrderObj` int,
`Product` int,
`Quantity` int,
FOREIGN KEY (OrderObj) REFERENCES OrderTable(Id),
FOREIGN KEY (Product) REFERENCES Product(Id),
CONSTRAINT uq_order_detail UNIQUE(OrderObj,Product)
)