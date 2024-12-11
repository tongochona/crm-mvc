use web_customer_tracker ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

INSERT INTO `user` VALUES 
	(1,'Admin','Admin','admin@gmail.com', 'admin', 'admin', 'admin', '999999'),
	(2,'Harry','Potter','harryp@gmail.com', 'harry', '123456', 'user',''),
    (3,'Ron','Weasley','ron@gmail.com', 'ron', '123456', 'user', '')