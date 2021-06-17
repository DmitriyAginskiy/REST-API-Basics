CREATE TABLE gift_certificates (
    certificate_id bigint NOT NULL AUTO_INCREMENT,
    certificate_name varchar(256) NOT NULL,
    description text NOT NULL,
    price decimal(10,0) NOT NULL,
    duration int NOT NULL,
    create_date datetime NOT NULL,
    last_update_date datetime NOT NULL,
    PRIMARY KEY (certificate_id)
);

CREATE TABLE `tags` (
    `tag_id` bigint NOT NULL AUTO_INCREMENT,
    `tag_name` varchar(256) NOT NULL,
    PRIMARY KEY (`tag_id`)
);

CREATE TABLE gift_certificates_has_tags (
    id bigint NOT NULL AUTO_INCREMENT,
    gift_certificates_id_fk bigint NOT NULL REFERENCES gift_certificates (certificate_id),
    tags_id_fk bigint NOT NULL REFERENCES tags (tag_id),
    PRIMARY KEY (id)
);

