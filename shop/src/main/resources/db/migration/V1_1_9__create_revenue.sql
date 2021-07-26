CREATE TABLE IF NOT EXISTS `Revenue`(

`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`location` int,
`date`  date,
`sum` decimal,
FOREIGN KEY (location) REFERENCES Location(id),
CONSTRAINT uq_revenue UNIQUE(location,date)
)
