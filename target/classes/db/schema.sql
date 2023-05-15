CREATE SCHEMA IF NOT EXISTS gift_certificates;

CREATE TABLE if not exists gift_certificates.gift_certificate
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(255)                        NOT NULL,
    description      VARCHAR(255),
    price            DECIMAL(19, 2)                      NOT NULL,
    duration         INT                                 NOT NULL,
    create_date      TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    last_update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE if not exists gift_certificates.tag
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT name UNIQUE (name)
);

CREATE TABLE if not exists gift_certificates.tag_gift_certificate
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    gift_certificate_id INT NOT NULL,
    tag_id              INT NOT NULL,
    CONSTRAINT tag_gift_certificate_ibfk_1
        FOREIGN KEY (gift_certificate_id) REFERENCES gift_certificate (id)
            ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT tag_gift_certificate_ibfk_2
        FOREIGN KEY (tag_id) REFERENCES tag (id)
            ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO gift_certificates.gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('Spa Day', 'A relaxing day at the spa', 100.00, 60, '2022-05-10 12:00:00', '2022-05-10 12:00:00'),
       ('Gourmet Dinner', 'A fancy meal at a top restaurant', 200.00, 90, '2022-05-11 09:00:00', '2022-05-11 09:00:00'),
       ('Wine Tasting', 'A guided tour and tasting at a winery', 50.00, 120, '2022-05-12 15:30:00', '2022-05-12 15:30:00'),
       ('Hot Air Balloon Ride', 'A scenic hot air balloon ride', 150.00, 60, '2022-05-13 11:45:00', '2022-05-13 11:45:00'),
       ('Art Class', 'A beginner-level painting class', 75.00, 180, '2022-05-14 14:15:00', '2022-05-14 14:15:00'),
       ('Cooking Class', 'A cooking class with a professional chef', 125.00, 120, '2022-05-15 10:30:00', '2022-05-15 10:30:00'),
       ('Sailing Trip', 'A half-day sailing trip on the ocean', 225.00, 240, '2022-05-16 13:00:00', '2022-05-16 13:00:00'),
       ('Horseback Riding', 'A guided horseback riding tour', 80.00, 90, '2022-05-17 16:45:00', '2022-05-17 16:45:00'),
       ('Golf Lesson', 'A private golf lesson with a pro', 100.00, 60, '2022-05-18 08:00:00', '2022-05-18 08:00:00'),
       ('Scuba Diving', 'A beginner-level scuba diving lesson', 175.00, 120, '2022-05-19 12:30:00', '2022-05-19 12:30:00');

INSERT INTO gift_certificates.tag (name)
VALUES ('Relaxation'), ('Fine Dining'), ('Wine'), ('Adventure'), ('Art'), ('Cooking'), ('Sailing'), ('Horses'), ('Golf'), ('Scuba Diving');

INSERT INTO gift_certificates.tag_gift_certificate (gift_certificate_id, tag_id)
VALUES (1, 1), (1, 5), (2, 2), (2, 5), (3, 3), (3, 5), (4, 4), (4, 5), (5, 5), (6, 6), (7, 4), (7, 7), (8, 6), (9, 8), (10, 9), (10, 4), (10, 5);

