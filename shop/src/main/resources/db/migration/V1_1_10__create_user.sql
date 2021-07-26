CREATE TABLE IF NOT EXISTS `Users`(
`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`username` varchar(30),
`password` varchar(80),
`full_name` varchar(30),
`email` varchar(31),
CONSTRAINT uq_user UNIQUE(username)
)