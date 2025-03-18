CREATE TABLE IF NOT EXISTS financial.tb_product (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    payment_id UUID REFERENCES financial.tb_payment(id),
    name VARCHAR(200) NOT NULL,
    description VARCHAR(300) NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity integer NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
