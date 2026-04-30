INSERT INTO roles (name) VALUES
    ('ROLE_USER'),
    ('ROLE_GUEST'),
    ('ROLE_VIEWER')
;

INSERT INTO users (handle, email, password, created_at)
VALUES 
    ('marcio', 'marcio@mail.com', 'password', CURRENT_TIMESTAMP),
    ('josue', 'josue@mail.com', 'password', CURRENT_TIMESTAMP)
;

INSERT INTO users_roles (user_id, role_id) 
VALUES
    (1, 1), -- Marcio has ROLE_USER
    (1, 3), -- Marcio has ROLE_VIEWER
    (2, 2)  -- Josue has ROLE_GUEST
;

INSERT INTO profiles (id, name, company, type)
VALUES
    (1, 'Marcio Ramos', 'Empresa 1', 'PROFESSIONAL'),
    (2, 'Josue Torres', 'Empresa 2', 'FREE')
;

-- RELACIONAL
INSERT INTO vulnerability_reports (system_under_test, created_at, updated_at, user_id) 
VALUES
    ('System A', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System B', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System C', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System D', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System E', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System F', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System G', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System H', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System I', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System J', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System K', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System L', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System M', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System O', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System P', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System Q', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System R', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System S', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System T', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System U', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System V', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System W', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System X', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System Y', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System Z', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System AA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System AB', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System AC', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System AD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System AE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System AF', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System AG', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System AH', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System AI', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System AJ', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1)
;

INSERT INTO vulnerabilities (description, severity, report_id, created_at, updated_at)
VALUES
    ('SQL Injection vulnerability in login form', 'HIGH', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Cross-Site Scripting (XSS) in user profile page', 'MEDIUM', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Insecure Direct Object Reference (IDOR) in file download feature', 'HIGH', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;