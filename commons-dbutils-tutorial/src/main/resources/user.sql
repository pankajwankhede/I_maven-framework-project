CREATE TABLE IF NOT EXISTS user (
  userId int(11) NOT NULL AUTO_INCREMENT,
  firstName varchar(50) NOT NULL,
  lastName varchar(50) NOT NULL,
  phoneNo varchar(50) NOT NULL,
  emailId varchar(50) NOT NULL,
  PRIMARY KEY (userId)
);

INSERT INTO user (userId, firstName, lastName, phoneNo, emailId) VALUES
(1, 'Pramod', 'Ganta', '9889885566', 'pramod@codesuggestions.com'),
(2, 'Suman', 'Manthena', '8858863456', 'suman@codesuggestions.com'),
(3, 'Prakash', 'Puli', '9889885566', 'prakash@codesuggestions.com'),
(4, 'Rohit', 'Sunkari', '8858863456', 'rohit@codesuggestions.com');