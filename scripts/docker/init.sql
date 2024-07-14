CREATE TABLE accounts (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    email TEXT NOT NULL,
    password_hash TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT (NOW() AT TIME ZONE 'UTC')
);

CREATE TABLE account_profiles (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    account INTEGER NOT NULL REFERENCES accounts,
    name TEXT NOT NULL
);

CREATE TABLE films (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title TEXT NOT NULL,
    release_date DATE NOT NULL
);

CREATE TABLE series (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    release_date DATE NOT NULL
);

CREATE TABLE series_seasons (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    series INTEGER NOT NULL REFERENCES series,
    order_number INTEGER NOT NULL,

    CONSTRAINT series_number UNIQUE (series, order_number)
);

CREATE TABLE series_episodes (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    season INTEGER NOT NULL REFERENCES series_seasons,
    order_number INTEGER NOT NULL,
    title TEXT NOT NULL,

    CONSTRAINT season_number UNIQUE (season, order_number)
);

CREATE TABLE watched_films (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    profile INTEGER NOT NULL REFERENCES account_profiles,
    film INTEGER NOT NULL REFERENCES films,
    created_at TIMESTAMP DEFAULT (NOW() AT TIME ZONE 'UTC'),

    CONSTRAINT profile_film UNIQUE (profile, film)
);

CREATE TABLE watched_series (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    profile INTEGER NOT NULL REFERENCES account_profiles,
    episode INTEGER NOT NULL REFERENCES series_episodes,
    created_at TIMESTAMP DEFAULT (NOW() AT TIME ZONE 'UTC'),

    CONSTRAINT profile_episode UNIQUE (profile, episode)
);

CREATE TABLE categories (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE film_categories (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    film INTEGER NOT NULL REFERENCES films,
    category INTEGER NOT NULL REFERENCES categories,

    CONSTRAINT film_category UNIQUE (film, category)
);

CREATE TABLE series_categories
(
    id       INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    series   INTEGER NOT NULL REFERENCES films,
    category INTEGER NOT NULL REFERENCES categories,

    CONSTRAINT series_category UNIQUE (series, category)
);

-- Example data

INSERT INTO accounts (email, password_hash, created_at)
VALUES ('leovano@example.com', '1234', NOW() AT TIME ZONE 'UTC'),
       ('douglas@example.com', '12345', NOW() AT TIME ZONE 'UTC');

INSERT INTO account_profiles (account, name)
VALUES (1, 'Leo'),
       (1, 'Mary'),
       (2, 'Douglas');

INSERT INTO films (title, release_date)
VALUES ('The Shawshank Redemption', '1994-10-14'),
       ('The Godfather', '1972-03-24'),
       ('The Dark Knight', '2008-07-18');

INSERT INTO series (title, description, release_date)
VALUES ('Stranger Things',
        'A group of kids uncover a mysterious laboratory experiment and a strange, little girl with telekinetic powers.',
        '2016-07-15'),
       ('Breaking Bad',
        'A high school chemistry teacher diagnosed with terminal cancer uses his chemical knowledge to cook and sell crystal meth to ensure his family''s financial security.',
        '2008-01-20');

INSERT INTO series_seasons (series, order_number)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2);

INSERT INTO series_episodes (season, order_number, title)
VALUES (1, 1, 'Chapter One: The Vanishing of Will Byers'), (1, 2, 'Chapter Two: MKUltra'), (1, 3, 'Chapter Three: Holly, Jolly'), (2, 1, 'Chapter One: Trick or Treat, Freak'), (2, 2, 'Chapter Two: When Barb Hangs, You Hang'), (2, 3, 'Chapter Three: My Poor Monster'), (3, 1, 'Pilot'), (3, 2, 'Cat''s in the Bag...'), (4, 1, 'Seven Thirty Seven'), (4, 2, 'Grilled');

INSERT INTO categories (name)
VALUES ('Drama'),
       ('Sci-Fi'),
       ('Comedy');

INSERT INTO film_categories (film, category)
VALUES (1, 1),
       (2, 2);

INSERT INTO series_categories (series, category)
VALUES (1, 1),
       (1, 3),
       (2, 1);
