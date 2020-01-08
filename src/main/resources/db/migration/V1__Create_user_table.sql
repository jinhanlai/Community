CREATE TABLE user(
  id INT PRIMARY KEY AUTO_INCREMENT ,
  account_id VARCHAR(100) not NULL ,
  nsame VARCHAR(50),
  tokon char(36),
  gmt_create BIGINT,
  gmt_modified BIGINT
)