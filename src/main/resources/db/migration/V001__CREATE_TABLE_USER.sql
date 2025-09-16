CREATE TABLE `user` (
    id CHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(125) NOT NULL,
    email VARCHAR(200) NOT NULL,
    login VARCHAR(200) NOT NULL,
    password VARCHAR(100) NOT NULL,
    user_type VARCHAR(30) NOT NULL,
    expertise VARCHAR(255) NULL,

    CONSTRAINT uk_user_email UNIQUE (email),
    CONSTRAINT uk_user_login UNIQUE (login)
);