CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(36) NOT NULL,
    name VARCHAR(200) NOT NULL,
    login VARCHAR(20) NOT NULL,
    password VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO users (id, name, login, password) VALUES ('1', 'Izac', 'izac_teste', 'senha123');
