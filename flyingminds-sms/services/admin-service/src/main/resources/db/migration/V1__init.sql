CREATE TABLE IF NOT EXISTS teacher_profiles (
    id BIGSERIAL PRIMARY KEY,
    user_email VARCHAR(255) NOT NULL UNIQUE,
    subject VARCHAR(255),
    qualifications VARCHAR(255),
    contact_number VARCHAR(64),
    documents_url VARCHAR(1024)
);
CREATE INDEX IF NOT EXISTS idx_teacher_profiles_email ON teacher_profiles(user_email);