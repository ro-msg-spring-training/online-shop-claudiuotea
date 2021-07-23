CREATE TABLE IF NOT EXISTS `Address`(
`Id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`Country` varchar(30),
`City` varchar(30),
`County` varchar(30),
`StreedAddress` varchar(30),
CONSTRAINT uq_address UNIQUE(Country,City,County,StreedAddress)
)