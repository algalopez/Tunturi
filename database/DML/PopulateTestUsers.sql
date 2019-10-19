USE `tunturi`

INSERT INTO `user_auth` (id, username, password, enabled, locked, role) VALUES
	('1', 'user1@tunturi.com', '$2y$12$B1esoIXpxaxbuDQCqgCDEeOi.XkesMVObAqF/NbvQcxBiJncbh3Bi', true, false, 'USER'), -- pass1
	('2', 'user2@tunturi.com', '$2y$12$Xt2skMlo59OJNQpy30q2p.YyHLgjPcZIUuk4PE7K38vSbNg62ikYa', true, false, 'USER'), -- pass2
	('3', 'user3@tunturi.com', '$2y$12$VugwdmBe0r/WawvQRe9a4u3LCMvSwG3pjNx62qVUfzKkV1S3mQKX6', true, false, 'USER'), -- pass3
	('4', 'user4@tunturi.com', '$2y$12$AvnHG9xZUwDVIH1xvsQjheYD2R/L1r1o0xkNubjBMK9cYM90E1fnq', true, false, 'USER'), -- pass4
	('5', 'admin@tunturi.com', '$2y$12$9jFyi2Q1brWZXsaFs3NdKeTC8K2LehErLr6csr2/86Fiv./H4x6tO', true, false, 'ADMIN'); -- pass5
