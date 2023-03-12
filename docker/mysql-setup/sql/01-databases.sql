# Create databases
CREATE DATABASE IF NOT EXISTS restaurant_db;

# Create user and grant rights
CREATE USER 'restaurant_user'@'%' IDENTIFIED BY 'password';
GRANT ALL ON *.* TO 'restaurant_user'@'%';
