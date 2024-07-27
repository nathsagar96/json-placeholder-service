-- Create the post table
CREATE TABLE Post (
    id INT PRIMARY KEY,
    title VARCHAR(255),
    body VARCHAR(255),
    user_id INT
);