CREATE TABLE IF NOT EXISTS financial.tb_product (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    invoice_id UUID NOT NULL REFERENCES financial.tb_invoice(id),
    name VARCHAR(200) NOT NULL,
    description VARCHAR(300) NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity integer NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
