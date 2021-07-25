CREATE TABLE IF NOT EXISTS `Supplier`(

`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` varchar(30),
CONSTRAINT uq_supplier UNIQUE(name)
)