-- create the users table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);
