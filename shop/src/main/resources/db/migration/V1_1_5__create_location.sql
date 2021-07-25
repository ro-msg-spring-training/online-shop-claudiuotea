CREATE TABLE IF NOT EXISTS `Location`(

`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` varchar(30),
`address` int,
FOREIGN KEY (address) REFERENCES Address(id),
CONSTRAINT uq_location UNIQUE(address)
)