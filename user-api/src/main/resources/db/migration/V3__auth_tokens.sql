CREATE TABLE auth_tokens (
    id         SERIAL       PRIMARY KEY,
    token      VARCHAR(255) UNIQUE NOT NULL,
    user_id    INTEGER             NOT NULL,
    created_at TIMESTAMP           NOT NULL,
    expires_at TIMESTAMP           NOT NULL,
    revoked_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
