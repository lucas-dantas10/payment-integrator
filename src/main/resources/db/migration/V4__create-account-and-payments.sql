CREATE TABLE IF NOT EXISTS authentication.tb_account (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    balance DECIMAL(10,2) NOT NULL,
    api_key TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS financial.tb_invoice (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    account_id UUID NOT NULL REFERENCES authentication.tb_account(id),
    amount DECIMAL(10,2) NOT NULL,
    currency VARCHAR(3) NOT NULL DEFAULT 'BRL',
    status STATUS_PAYMENT NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    external_payment_id INTEGER DEFAULT NULL,
    pix_qr_code TEXT DEFAULT NULL,
    pix_payment_link TEXT DEFAULT NULL,
    status_details_external VARCHAR DEFAULT NULL,
    gateway_response JSONB DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_status_at TIMESTAMP,
    approved_at TIMESTAMP DEFAULT NULL
);