CREATE TABLE IF NOT EXISTS financial.tb_customer (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    payment_id UUID REFERENCES financial.tb_payment(id),
    name VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
