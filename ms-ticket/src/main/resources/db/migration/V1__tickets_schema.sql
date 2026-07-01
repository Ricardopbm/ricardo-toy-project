CREATE TABLE tickets (
    id           SERIAL       PRIMARY KEY,
    action       VARCHAR(255) NOT NULL,
    object       VARCHAR(255) NOT NULL,
    details      TEXT         NOT NULL,
    creator      VARCHAR(255) NOT NULL,
    recipient    VARCHAR(255) NOT NULL,
    assignee     VARCHAR(255),
    status       VARCHAR(50)  NOT NULL,
    motivo       TEXT,
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP    NOT NULL
);

CREATE TABLE ticket_observers (
    ticket_id INTEGER      NOT NULL,
    email     VARCHAR(255) NOT NULL,
    PRIMARY KEY (ticket_id, email),
    FOREIGN KEY (ticket_id) REFERENCES tickets(id) ON DELETE CASCADE
);
