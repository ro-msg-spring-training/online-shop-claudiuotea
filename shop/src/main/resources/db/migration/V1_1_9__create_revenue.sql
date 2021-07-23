CREATE TABLE IF NOT EXISTS `Revenue`(

`Id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`Location` int,
`Date`  date,
`Sum` decimal,
FOREIGN KEY (Location) REFERENCES Location(Id),
CONSTRAINT uq_revenue UNIQUE(Location,Date)
)