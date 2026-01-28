CREATE DATABASE library_db;

USE library_db;


CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100),
    available BOOLEAN DEFAULT TRUE
);

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100)
);


CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    book_id INT,
    borrow_date DATE,
    return_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);


INSERT INTO books (title, author, available) VALUES
('Introduction to Java', 'James Gosling', TRUE),
('Clean Code', 'Robert C. Martin', TRUE),
('Database Systems', 'Thomas Connolly', TRUE),
('Operating Systems', 'Andrew Tanenbaum', TRUE),
('Computer Networks', 'James Kurose', TRUE);

INSERT INTO users (name, email) VALUES
('Alice Johnson', 'alice@example.com'),
('Brian Smith', 'brian@example.com'),
('Carol Williams', 'carol@example.com');

INSERT INTO transactions (user_id, book_id, borrow_date, return_date) VALUES
(1, 2, '2025-01-10', NULL),
(2, 4, '2025-01-12', NULL);

UPDATE books SET available = FALSE WHERE id IN (2, 4);
