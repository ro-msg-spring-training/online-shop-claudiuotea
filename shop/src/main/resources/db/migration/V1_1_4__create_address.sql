CREATE TABLE IF NOT EXISTS `Address`(
`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`country` varchar(30),
`city` varchar(30),
`county` varchar(30),
`street_address` varchar(30),
CONSTRAINT uq_address UNIQUE(country,city,county,street_address)
)