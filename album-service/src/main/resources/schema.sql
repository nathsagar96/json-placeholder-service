-- Create the album table
CREATE TABLE IF NOT EXISTS album (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL
);

-- Create an index on user_id for faster lookups
CREATE INDEX idx_user_id ON album(user_id);