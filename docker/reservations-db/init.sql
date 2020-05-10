
CREATE TABLE reservations(
	adventure_id INT NOT NULL,
	user_id VARCHAR(150) NOT NULL,
	is_payed BOOLEAN,
	reservations_date DATETIME NOT NULL,
	reservations_nb INT NOT NULL,
	PRIMARY KEY(adventure_id, user_id)
) ENGINE=InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE orders(
    adventure_id INT NOT NULL,
    user_id VARCHAR(150) NOT NULL,
    adventure_name VARCHAR(150) NOT NULL,
    total_price DOUBLE NOT NULL,
    adventure_date DATETIME NOT NULL,
    payment_date DATETIME NOT NULL,
    stripe_transaction VARCHAR(150) NOT NULL,
	participant_number INT NOT NULL,
    PRIMARY KEY(adventure_id, user_id)
) ENGINE=InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

ALTER TABLE orders ADD CONSTRAINT fk_orders_reservations FOREIGN KEY (adventure_id, user_id) REFERENCES reservations (adventure_id, user_id);

INSERT INTO reservations(adventure_id, user_id, is_payed, reservations_date, reservations_nb) VALUES (1, '3c5af797-366f-4a0f-bad0-144b093f8e83', 1, '2019-12-08 17:51:04.777', 3);
INSERT INTO reservations(adventure_id, user_id, is_payed, reservations_date, reservations_nb) VALUES (1, '03e19c16-7b18-465a-8c55-4ce255d42b9b', 0, '2019-12-08 18:51:04.777', 2);



