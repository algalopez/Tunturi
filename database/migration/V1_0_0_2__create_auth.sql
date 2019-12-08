USE `tunturi`;

CREATE TABLE `user_auth` (
    `id` int(11) AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(128) UNIQUE NOT NULL,
    `password` VARCHAR(256) NOT NULL,
    `enabled` BOOL NOT NULL,
    `locked` BOOL NOT NULL,
    `role` VARCHAR(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `oauth_access_token` (
    `authentication_id` VARCHAR(256),
    `token_id` VARCHAR(256),
    `token` BLOB,
    `user_name` VARCHAR(256),
    `client_id` VARCHAR(256),
    `authentication` BLOB,
    `refresh_token` VARCHAR(256)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `oauth_refresh_token` (
    `token_id` VARCHAR(256),
    `token` BLOB,
    `authentication` BLOB
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
