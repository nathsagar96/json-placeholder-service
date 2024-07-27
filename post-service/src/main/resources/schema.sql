-- Create the post table
CREATE TABLE Post (
    id INT PRIMARY KEY,
    title VARCHAR(255),
    body TEXT,
    userId INT
);

-- Create an index on user_id for faster lookups
CREATE INDEX idx_user_id ON post(userId);