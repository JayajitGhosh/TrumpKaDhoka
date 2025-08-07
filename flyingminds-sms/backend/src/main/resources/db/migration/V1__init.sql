-- Schema initialization for FlyingMinds SMS

CREATE TABLE IF NOT EXISTS user_accounts (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    approval_status VARCHAR(32) NOT NULL,
    active BOOLEAN NOT NULL,
    full_name VARCHAR(255),
    last_login_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT NOT NULL REFERENCES user_accounts(id) ON DELETE CASCADE,
    role VARCHAR(64) NOT NULL,
    PRIMARY KEY (user_id, role)
);

CREATE TABLE IF NOT EXISTS teacher_profiles (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE REFERENCES user_accounts(id) ON DELETE CASCADE,
    subject VARCHAR(255),
    qualifications VARCHAR(255),
    contact_number VARCHAR(64),
    documents_url VARCHAR(1024)
);

CREATE TABLE IF NOT EXISTS school_classes (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(64) NOT NULL UNIQUE,
    name VARCHAR(255),
    teacher_id BIGINT REFERENCES teacher_profiles(id)
);

-- Helpful indexes
CREATE INDEX IF NOT EXISTS idx_user_accounts_approval ON user_accounts(approval_status);
CREATE INDEX IF NOT EXISTS idx_user_accounts_active ON user_accounts(active);