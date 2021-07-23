CREATE TABLE IF NOT EXISTS `Location`(

`Id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`Name` varchar(30),
`Address` int,
FOREIGN KEY (Address) REFERENCES Address(Id),
CONSTRAINT uq_location UNIQUE(Address)
)