CREATE TABLE `user` (
    id CHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(125) NOT NULL,
    mail VARCHAR(200) NOT NULL,
    user_type VARCHAR(30) NOT NULL,
    expertise VARCHAR(255) NULL,

    CONSTRAINT uk_user_mail UNIQUE (mail)
);