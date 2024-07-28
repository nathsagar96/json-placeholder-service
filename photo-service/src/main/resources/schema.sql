-- Create photo table
CREATE TABLE Photo (
    id INT PRIMARY KEY,
    title VARCHAR(255),
    url VARCHAR(255),
    thumbnail_url VARCHAR(255),
    album_id INT
);