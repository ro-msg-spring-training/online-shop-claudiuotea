CREATE TABLE IF NOT EXISTS `Order_Detail`(
`orders` int,
`product` int,
`quantity` int,
FOREIGN KEY (orders) REFERENCES Orders(id),
FOREIGN KEY (product) REFERENCES Product(id),
CONSTRAINT uq_order_detail UNIQUE(orders,product)
)