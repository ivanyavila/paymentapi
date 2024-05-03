CREATE TABLE IF NOT EXISTS
payment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    concept VARCHAR(255),
    quantity INT,
    payer VARCHAR(255),
    recipient VARCHAR(255),
    amount DECIMAL(19,2),
    status VARCHAR(255)
);