CREATE TABLE IF NOT EXISTS `Orders`(
`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`shipped_from` int,
`customer` int,
`created_at` timestamp,
`address` int,
FOREIGN KEY (shipped_from) REFERENCES Location(id),
FOREIGN KEY (customer) REFERENCES Customer(id),
FOREIGN KEY (address) REFERENCES Address(id),
CONSTRAINT uq_order UNIQUE(customer,created_at)
)