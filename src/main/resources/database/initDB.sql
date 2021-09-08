CREATE TABLE IF NOT EXISTS users
(
    chat_id    BIGSERIAL PRIMARY KEY ,
    name  VARCHAR(200) NOT NULL ,
    location VARCHAR(254)
    );