-- Case-insensitive unique email to avoid duplicates with case variance
CREATE UNIQUE INDEX IF NOT EXISTS ux_user_accounts_email_ci ON user_accounts (LOWER(email));

-- Extra helpful indexes
CREATE INDEX IF NOT EXISTS idx_teacher_profiles_user ON teacher_profiles(user_id);