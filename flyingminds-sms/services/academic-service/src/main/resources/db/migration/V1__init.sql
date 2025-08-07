CREATE TABLE IF NOT EXISTS school_classes (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(64) NOT NULL UNIQUE,
    name VARCHAR(255),
    class_teacher_email VARCHAR(255)
);
CREATE INDEX IF NOT EXISTS idx_school_classes_code ON school_classes(code);