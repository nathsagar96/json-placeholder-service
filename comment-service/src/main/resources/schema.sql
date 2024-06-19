-- Create the comment table
CREATE TABLE comment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    body TEXT
);

-- Create an index on post_id for faster lookups
CREATE INDEX idx_post_id ON comment(post_id);