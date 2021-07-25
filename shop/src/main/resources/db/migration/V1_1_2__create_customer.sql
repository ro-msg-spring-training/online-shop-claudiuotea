CREATE TABLE IF NOT EXISTS `Customer`(

`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`first_name` varchar(30),
`last_name` varchar(30),
`username` varchar(30),
`password` varchar(30),
`email_address` varchar(30),
CONSTRAINT uq_customer_email UNIQUE(email_address),
CONSTRAINT uq_customer_username UNIQUE(username)
)