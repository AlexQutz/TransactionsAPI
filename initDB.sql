CREATE TABLE account (
                         id SERIAL PRIMARY KEY,
                         balance DOUBLE PRECISION,
                         currency VARCHAR(3),
                         createdAt TIMESTAMP WITH TIME ZONE
);

INSERT INTO account (balance, currency, createdAt) VALUES (1000.0, 'GBP', '2024-01-15T12:00:00Z');
INSERT INTO account (balance, currency, createdAt) VALUES (500.0, 'GBP', '2024-01-16T12:00:00Z');
INSERT INTO account (balance, currency, createdAt) VALUES (750.0, 'USD', '2024-01-17T12:00:00Z');
INSERT INTO account (balance, currency, createdAt) VALUES (1200.0, 'EUR', '2024-01-18T12:00:00Z');
INSERT INTO account (balance, currency, createdAt) VALUES (300.0, 'GBP', '2024-01-19T12:00:00Z');
INSERT INTO account (balance, currency, createdAt) VALUES (1000.0, 'GBP', '2024-01-15T12:00:00Z');
INSERT INTO account (balance, currency, createdAt) VALUES (500.0, 'GBP', '2024-01-16T12:00:00Z');
INSERT INTO account (balance, currency, createdAt) VALUES (750.0, 'USD', '2024-01-17T12:00:00Z');
INSERT INTO account (balance, currency, createdAt) VALUES (1200.0, 'EUR', '2024-01-18T12:00:00Z');
INSERT INTO account (balance, currency, createdAt) VALUES (300.0, 'GBP', '2024-01-19T12:00:00Z');
