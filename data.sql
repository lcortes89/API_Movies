-- Sample data for API Movies. Add this to your existing data.sql.

INSERT INTO genres (name) VALUES ('Sci-Fi'), ('Drama'), ('Action');

INSERT INTO years (release_year) VALUES (1999), (2010), (1994);

INSERT INTO actors (name) VALUES ('Keanu Reeves'), ('Leonardo DiCaprio'), ('Morgan Freeman');

INSERT INTO movies (title, synopsis, genre_id, year_id) VALUES
('The Matrix', 'A hacker discovers his reality is a simulation.', 1, 1),
('Inception', 'A thief who steals secrets through dream-sharing technology.', 1, 2),
('The Shawshank Redemption', 'Two imprisoned men bond over a number of years.', 2, 3);

INSERT INTO movie_actors (movie_id, actor_id) VALUES
(1, 1),
(2, 2),
(3, 3);
