-- Create the comment table
CREATE TABLE Comment (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    body VARCHAR(255),
    post_id INT
);