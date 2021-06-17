INSERT INTO tags (tag_id ,tag_name) VALUES(1,'sport');
INSERT INTO tags (tag_id ,tag_name) VALUES(2,'food');
INSERT INTO tags (tag_id ,tag_name) VALUES(3,'films');
INSERT INTO tags (tag_id ,tag_name) VALUES(4,'books');

INSERT INTO gift_certificates (certificate_id, certificate_name, description, price, duration, create_date, last_update_date)
VALUES(1, 'Football', 'The game', '11.99', 15, '2020-08-11T15:32:12', '2020-08-11T15:32:12');

INSERT INTO gift_certificates (certificate_id, certificate_name, description, price, duration, create_date, last_update_date)
VALUES(2, 'Chips', 'Tasty food', '15.99', 13, '2019-09-11T15:32:12', '2021-03-11T15:32:12');

INSERT INTO gift_certificates (certificate_id, certificate_name, description, price, duration, create_date, last_update_date)
VALUES(3, 'Black widow', 'Marvel', '99.99', 125, '2019-09-11T15:32:12', '2021-03-11T15:32:12');

INSERT INTO gift_certificates (certificate_id, certificate_name, description, price, duration, create_date, last_update_date)
VALUES(4, 'Harry Potter', 'About boy', '82.99', 79, '2019-09-11T15:32:12', '2021-03-11T15:32:12');

INSERT INTO gift_certificates_has_tags (id, gift_certificates_id_fk, tags_id_fk) VALUES (1, 1, 1);
INSERT INTO gift_certificates_has_tags (id, gift_certificates_id_fk, tags_id_fk) VALUES (2, 2, 2);
INSERT INTO gift_certificates_has_tags (id, gift_certificates_id_fk, tags_id_fk) VALUES (3, 2, 3);
INSERT INTO gift_certificates_has_tags (id, gift_certificates_id_fk, tags_id_fk) VALUES (4, 4, 3);