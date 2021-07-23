CREATE TABLE IF NOT EXISTS `Customer`(

`Id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`FirstName` varchar(30),
`LastName` varchar(30),
`Username` varchar(30),
`Password` varchar(30),
`EmailAddress` varchar(30),
CONSTRAINT uq_customer_email UNIQUE(EmailAddress),
CONSTRAINT uq_customer_username UNIQUE(Username)
)