---- Create the todo table
CREATE TABLE IF NOT EXISTS todo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    completed BOOLEAN NOT NULL
);

-- Create an index on user id for faster lookups
CREATE INDEX idx_user_id ON todo(user_id);