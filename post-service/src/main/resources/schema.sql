-- Create the post table
CREATE TABLE IF NOT EXISTS post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    body TEXT
);

-- Create an index on user_id for faster lookups
CREATE INDEX idx_user_id ON post(user_id);