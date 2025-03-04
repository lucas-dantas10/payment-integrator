CREATE TYPE status_payment AS ENUM ('PENDING', 'APPROVED', 'REJECTED');
CREATE TYPE status_transaction AS ENUM ('SUCCESS', 'FAILED');
CREATE TYPE payment_method AS ENUM ('pix', 'boleto', 'credit_card');