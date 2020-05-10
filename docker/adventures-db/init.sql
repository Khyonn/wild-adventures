CREATE TABLE adventures(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    start_date DATETIME NOT NULL,
    price DOUBLE NOT NULL,
    description TEXT NOT NULL,
    max_participant_number INT NOT NULL,
    category VARCHAR(150) NOT NULL,
    PRIMARY KEY(id)
) ENGINE=InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


CREATE TABLE adventure_images(
    id INT NOT NULL,
    adventure_id INT NOT NULL,
    is_main BOOLEAN NOT NULL,
    width INT NOT NULL,
    height INT NOT NULL,
    url TEXT NOT NULL,
    label VARCHAR(150) NOT NULL,
    PRIMARY KEY(id, adventure_id)
) ENGINE=InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

ALTER TABLE adventure_images ADD CONSTRAINT fk_adventures_adv_images FOREIGN KEY (adventure_id) REFERENCES adventures (id);

INSERT INTO adventures(id, category, start_date, description, max_participant_number, name, price) VALUES (1, 'Plage', '2020-09-08 17:51:04.777', 'Voyage dans le pas de calais', 6, 'Berck Plage', 192.30);
INSERT INTO adventures(id, category, start_date, description, max_participant_number, name, price) VALUES (2, 'Montagne', '2021-02-08 17:51:04.777', 'Voyage à valberg', 4, 'Valberg', 450.74);
INSERT INTO adventures(id, category, start_date, description, max_participant_number, name, price) VALUES (3, 'Ville', '2021-04-08 17:51:04.777', 'Voyage à Tokyo pendant la saison des cerisiers', 4, 'Tokyo', 1229.99);
INSERT INTO adventures(id, category, start_date, description, max_participant_number, name, price) VALUES (4, 'Ville', '2021-07-08 17:51:04.777', 'Voyage à Montréal en juillet', 4, 'Montréal', 1179.99);
INSERT INTO adventures(id, category, start_date, description, max_participant_number, name, price) VALUES (5, 'Plage', '2021-07-08 17:51:04.777', 'Voyage à St-tropez en juillet', 4, 'St-Tropez', 789.99);



INSERT INTO adventure_images(id, adventure_id, height, is_main, label, url, width) VALUES(1, 1, 300, true, 'berckplage main', 'https://media.routard.com/image/10/2/berck-plage-nord.1526102.w630.jpeg',200);
INSERT INTO adventure_images(id, adventure_id, height, is_main, label, url, width) VALUES(2, 1, 300, false, 'berck hotel', 'https://as1.ftcdn.net/jpg/02/43/89/66/500_F_243896699_URu7OVUqnKhNVO17qAzekrFRpghq0TQL.jpg', 200);
INSERT INTO adventure_images(id, adventure_id, height, is_main, label, url, width) VALUES(1, 2, 300, true, 'valberg main','https://docs.ski-planet.com/stations/source/6020.jpg', 200);
INSERT INTO adventure_images(id, adventure_id, height, is_main, label, url, width) VALUES(2, 2, 300, false, 'valberg station','https://vcdn.bergfex.at/images/downsized/0b/2128de371d8cce0b_d93b088c9706f2ce@2x.jpg', 200);
INSERT INTO adventure_images(id, adventure_id, height, is_main, label, url, width) VALUES(1, 3, 300, true, 'Tokyo cerisiers','https://media.routard.com/image/69/9/geisha.1573699.w740.jpg', 200);
INSERT INTO adventure_images(id, adventure_id, height, is_main, label, url, width) VALUES(2, 3, 300, false, 'Tokyo ville','https://voyages.michelin.fr/sites/default/files/tokyo-pop.jpg', 200);
INSERT INTO adventure_images(id, adventure_id, height, is_main, label, url, width) VALUES(1, 4, 300, true, 'Montreal main','https://media.routard.com/image/52/5/photo.1406525.w630.jpg', 200);
INSERT INTO adventure_images(id, adventure_id, height, is_main, label, url, width) VALUES(2, 4, 300, false, 'Montreal center','https://media.routard.com/image/28/2/montreal-skyline.1476282.c1000x300.jpg', 200);
INSERT INTO adventure_images(id, adventure_id, height, is_main, label, url, width) VALUES(1, 5, 300, true, 'Gendarme StTropez','https://www.rts.ch/2019/11/26/13/02/10896110.image/16x9/scale/width/624', 200);
INSERT INTO adventure_images(id, adventure_id, height, is_main, label, url, width) VALUES(2, 5, 300, false, 'St-Tropez plage','https://media-cdn.tripadvisor.com/media/photo-s/15/4b/e9/6b/la-bouillabaisse-plage.jpg', 200);




