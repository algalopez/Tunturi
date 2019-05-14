DROP DATABASE IF EXISTS `ranking`;

CREATE DATABASE IF NOT EXISTS `ranking` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `ranking`

CREATE TABLE sample (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `sample` varchar(200) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE user (
    `user_id` BIGINT PRIMARY KEY auto_increment,
    `username` VARCHAR(128) UNIQUE,
    `password` VARCHAR(256),
    `enabled` BOOL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE oauth_access_token (
    `authentication_id` VARCHAR(256),
    `token_id` VARCHAR(256),
    `token` BLOB,
    `user_name` VARCHAR(256),
    `client_id` VARCHAR(256),
    `authentication` BLOB,
    `refresh_token` VARCHAR(256)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE oauth_refresh_token (
    `token_id` VARCHAR(256),
    `token` BLOB,
    `authentication` BLOB
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
