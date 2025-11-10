CREATE TABLE transactions (
                              transaction_id BIGSERIAL PRIMARY KEY,
                              description TEXT,
                              creator_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                              amount NUMERIC(15,2) NOT NULL,
                              creation_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                              transaction_type VARCHAR(20) NOT NULL CHECK (transaction_type IN ('expense', 'income')),
                              transaction_category VARCHAR(50)
);