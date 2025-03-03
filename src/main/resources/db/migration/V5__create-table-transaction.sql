CREATE TABLE IF NOT EXISTS financial.tb_transaction (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    payment_id UUID REFERENCES financial.tb_payment(id),
    status STATUS_TRANSACTION NOT NULL,
    gateway_response VARCHAR NOT NULL,
    transaction_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);