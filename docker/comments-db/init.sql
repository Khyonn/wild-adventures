CREATE TABLE comments(
	user_id VARCHAR(150) NOT NULL,
	adventure_id INT NOT NULL,
	comment_id INT NOT NULL,
	content TEXT NOT NULL,
	date DATETIME NOT NULL,
	PRIMARY KEY(user_id, adventure_id, comment_id)
) ENGINE=InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT INTO comments(user_id, adventure_id, comment_id, content, date) VALUES ('1339dfb1-df52-4aa7-9569-2b1ab7dc0d06', 1, 1, 'Preums !', '2019-09-08 17:51:04.777');
INSERT INTO comments(user_id, adventure_id, comment_id, content, date) VALUES ('1339dfb1-df52-4aa7-9569-2b1ab7dc0d06', 1, 2, 'Un vrai commentaire !', '2019-09-08 17:51:04.777');
INSERT INTO comments(user_id, adventure_id, comment_id, content, date) VALUES ('46402c93-44ed-43f2-a647-1cf02e63bf54', 1, 1, 'J''ai raté le preums :(', '2019-10-08 17:51:04.777');
INSERT INTO comments(user_id, adventure_id, comment_id, content, date) VALUES ('46402c93-44ed-43f2-a647-1cf02e63bf54', 2, 1, 'Mon premier commentaire sur cette aventure', '2019-12-08 17:51:04.777');
