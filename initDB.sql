CREATE TABLE Account (
                         id BIGSERIAL PRIMARY KEY,
                         balance DOUBLE PRECISION,
                         currency VARCHAR(3),
                         createdAt TIMESTAMP WITH TIME ZONE
);

INSERT INTO Account (balance, currency, createdAt) VALUES (1000.0, 'GBP', '2024-01-15T12:00:00Z');
INSERT INTO Account (balance, currency, createdAt) VALUES (500.0, 'GBP', '2024-01-16T12:00:00Z');
INSERT INTO Account (balance, currency, createdAt) VALUES (750.0, 'USD', '2024-01-17T12:00:00Z');
INSERT INTO Account (balance, currency, createdAt) VALUES (1200.0, 'EUR', '2024-01-18T12:00:00Z');
INSERT INTO Account (balance, currency, createdAt) VALUES (300.0, 'GBP', '2024-01-19T12:00:00Z');


CREATE TABLE Transaction (
                             id BIGSERIAL PRIMARY KEY,
                             sourceAccountId BIGINT NOT NULL,
                             targetAccountId BIGINT NOT NULL,
                             amount DOUBLE PRECISION,
                             currency VARCHAR(3),
                             FOREIGN KEY (sourceAccountId) REFERENCES Account(id),
                             FOREIGN KEY (targetAccountId) REFERENCES Account(id)
);


INSERT INTO Transaction (sourceAccountId, targetAccountId, amount, currency) VALUES (1, 2, 50.0, 'GBP');
INSERT INTO Transaction (sourceAccountId, targetAccountId, amount, currency) VALUES (2, 3, 100.0, 'GBP');
INSERT INTO Transaction (sourceAccountId, targetAccountId, amount, currency) VALUES (3, 4, 75.0, 'USD');
INSERT INTO Transaction (sourceAccountId, targetAccountId, amount, currency) VALUES (4, 5, 120.0, 'EUR');
INSERT INTO Transaction (sourceAccountId, targetAccountId, amount, currency) VALUES (5, 1, 30.0, 'GBP');