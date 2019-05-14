USE `ranking`

INSERT INTO sample (`id`, `sample`) VALUES (1, 'sample 1');

INSERT INTO user (user_id, username, password, enabled) VALUES
	('1', 'peter@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true),
	('2', 'john@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true),
	('3', 'katie@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true);
