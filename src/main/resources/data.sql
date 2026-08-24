INSERT INTO countries (id, name) VALUES (default, 'USA');
INSERT INTO countries (id, name) VALUES (default, 'France');
INSERT INTO countries (id, name) VALUES (default, 'Ucrania');
INSERT INTO countries (id, name) VALUES (default, 'Italy');
INSERT INTO countries (id, name) VALUES (default, 'Canada');
INSERT INTO countries (id, name) VALUES (default, 'Irland');

INSERT INTO users (id_user, username) VALUES (default, 'Mickey');
INSERT INTO users (id_user, username) VALUES (default, 'Minnie');

INSERT INTO profiles (id_profile, email, user_id, country_id) VALUES (default, 'mickey@disney.com', 1, 1);
INSERT INTO profiles (id_profile, email, user_id, country_id) VALUES (default, 'minnie@disney.com', 2, 1);

INSERT INTO groups (id_group, name) VALUES (default, 'Whatsapp');
INSERT INTO groups (id_group, name) VALUES (default, 'P5-DA');

INSERT INTO groups_users (group_id, user_id) VALUES (1,1);
INSERT INTO groups_users (group_id, user_id) VALUES (1,2);
INSERT INTO groups_users (group_id, user_id) VALUES (2,2);