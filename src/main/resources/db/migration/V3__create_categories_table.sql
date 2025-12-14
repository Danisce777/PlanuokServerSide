CREATE TABLE categories (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            type VARCHAR(20) NOT NULL CHECK (type IN ('EXPENSE', 'INCOME')),
                            is_default BOOLEAN DEFAULT FALSE,
                            user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
                            created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                            CONSTRAINT unique_category_per_user UNIQUE(name, user_id)
);

CREATE UNIQUE INDEX unique_default_category_name
    ON categories(name)
    WHERE is_default = TRUE;

INSERT INTO categories (name, type, is_default, user_id) VALUES
        ('Food', 'EXPENSE', TRUE, NULL),
        ('Transportation', 'EXPENSE', TRUE, NULL),
        ('Entertainment', 'EXPENSE', TRUE, NULL),
        ('Shopping', 'EXPENSE', TRUE, NULL),
        ('Bills', 'EXPENSE', TRUE, NULL),
        ('Healthcare', 'EXPENSE', TRUE, NULL),
        ('Other Expense', 'EXPENSE', TRUE, NULL);

INSERT INTO categories (name, type, is_default, user_id) VALUES
        ('Salary', 'INCOME', TRUE, NULL),
        ('Freelance', 'INCOME', TRUE, NULL),
        ('Investment', 'INCOME', TRUE, NULL),
        ('Gift', 'INCOME', TRUE, NULL),
        ('Other Income', 'INCOME', TRUE, NULL);