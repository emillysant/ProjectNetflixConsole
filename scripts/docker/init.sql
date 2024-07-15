CREATE TABLE accounts
(
    id            INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    email         TEXT NOT NULL,
    password_hash TEXT NOT NULL,
    created_at    TIMESTAMP DEFAULT (NOW() AT TIME ZONE 'UTC')
);

CREATE TABLE account_profiles
(
    id      INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    account INTEGER NOT NULL REFERENCES accounts,
    name    TEXT    NOT NULL,

    CONSTRAINT account_name UNIQUE (account, name)
);

CREATE TABLE films
(
    id           INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title        TEXT NOT NULL,
    description  TEXT NOT NULL,
    release_date DATE NOT NULL
);

CREATE TABLE series
(
    id           INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title        TEXT NOT NULL,
    description  TEXT NOT NULL,
    release_date DATE NOT NULL
);

CREATE TABLE series_seasons
(
    id           INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    series       INTEGER NOT NULL REFERENCES series,
    order_number INTEGER NOT NULL,

    CONSTRAINT series_number UNIQUE (series, order_number)
);

CREATE TABLE series_episodes
(
    id           INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    season       INTEGER NOT NULL REFERENCES series_seasons,
    order_number INTEGER NOT NULL,
    title        TEXT    NOT NULL,

    CONSTRAINT season_number UNIQUE (season, order_number)
);

CREATE TABLE watched_films
(
    id         INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    profile    INTEGER NOT NULL REFERENCES account_profiles,
    film       INTEGER NOT NULL REFERENCES films,
    created_at TIMESTAMP DEFAULT (NOW() AT TIME ZONE 'UTC'),

    CONSTRAINT profile_film UNIQUE (profile, film)
);

CREATE TABLE watched_series
(
    id         INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    profile    INTEGER NOT NULL REFERENCES account_profiles,
    episode    INTEGER NOT NULL REFERENCES series_episodes,
    created_at TIMESTAMP DEFAULT (NOW() AT TIME ZONE 'UTC'),

    CONSTRAINT profile_episode UNIQUE (profile, episode)
);

CREATE TABLE categories
(
    id   INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE film_categories
(
    id       INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    film     INTEGER NOT NULL REFERENCES films,
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



INSERT INTO accounts (email, password_hash, created_at)
VALUES ('leovano@example.com',
        '$argon2id$v=19$m=60000,t=10,p=1$SzNSTklWaUtOek56cXNKQg$8g2stOMR1zWDYEdPnH5Q799BhFJDrI3o9j2MVsYo4pE',
        NOW() AT TIME ZONE 'UTC'),
       ('douglas@example.com',
        '$argon2id$v=19$m=60000,t=10,p=1$SzNSTklWaUtOek56cXNKQg$0vg7OPka9UuAVQHaUGNm6wccRy2PAlatw4/2jPaK8Xc',
        NOW() AT TIME ZONE 'UTC');

INSERT INTO account_profiles (account, name)
VALUES (1, 'Leo'),
       (1, 'Mary'),
       (2, 'Douglas');

INSERT INTO films (title, description, release_date)
VALUES ('The Shawshank Redemption', 'A wrongly convicted man plans his escape from prison over a long period of time.',
        '1994-10-14'),
       ('The Godfather',
        'The story of the Corleone family under patriarch Vito Corleone, focusing on the transformation of his youngest son, Michael, from reluctant family outsider to ruthless mafia boss.',
        '1972-03-24'),
       ('The Dark Knight',
        'When the mentally disturbed Joker wreaks havoc and chaos on Gotham City, Batman must face him and take ultimate responsibility for ending his reign of anarchy.',
        '2008-07-18'),
       ('Goodfellas',
        'The story of the rise and fall of an Italian-American mafia gangster, based on the book "Wiseguy" by Nicholas Pileggi.',
        '1990-09-19'),
       ('Home Alone',
        'An eight-year-old boy is left behind at home during the holidays and must defend himself against two bumbling burglars.',
        '1990-11-16'),
       ('Pretty Woman',
        'A romantic comedy about a wealthy businessman who hires a street prostitute to be his escort for a week.',
        '1990-03-23'),
       ('The Silence of the Lambs',
        'An FBI agent seeks the help of a jailed cannibalistic psychopath to catch another serial killer.',
        '1991-02-14'),
       ('Beauty and the Beast',
        'A Disney animation that tells the love story between a young woman and a prince transformed into a beast.',
        '1991-11-22'),
       ('Terminator 2: Judgment Day',
        'A cyborg is sent from the future to protect young John Connor from a more advanced and deadly cyborg.',
        '1991-07-03'),
       ('Reservoir Dogs',
        'A heist goes wrong and the members of a gang begin to suspect there is an informant among them.',
        '1992-09-02'),
       ('Aladdin',
        'A poor young man finds a magical lamp housing a genie who can grant wishes.',
        '1992-11-25'),
       ('A Few Good Men',
        'A military lawyer defends two Marines accused of murdering a colleague within an extremely rigid ethical code context.',
        '1992-12-11'),
       ('Jurassic Park',
        'A dinosaur theme park suffers a catastrophic security failure.',
        '1993-06-11'),
       ('Forrest Gump',
        'The life of a simple Alabama man who witnesses and sometimes influences some of the most important events of the 20th century in the United States.',
        '1994-07-06'),
       ('Braveheart',
        'The story of William Wallace, a 13th-century Scotsman who leads a revolt against King Edward I of England.',
        '1995-05-24'),
       ('Titanic',
        'An epic tale of romance and tragedy based on the RMS Titanic disaster.',
        '1997-12-19'),
       ('The Matrix',
        'A hacker discovers the truth about his reality and his connection to a war against intelligent machines.',
        '1999-03-31'),
       ('Gladiator',
        'A betrayed Roman general^s family is murdered by a corrupt prince. He returns as a gladiator to seek revenge.',
        '2000-05-05'),
       ('The Lord of the Rings: The Fellowship of the Ring',
        'A group of nine members of the hobbit race embark on a journey to destroy a powerful ring and save Middle-earth from darkness.',
        '2001-12-19'),
       ('Pirates of the Caribbean: The Curse of the Black Pearl',
        'Captain Jack Sparrow tries to reclaim his ship, the Black Pearl, from a cursed captain and his crew of zombies.',
        '2003-07-09'),
       ('Batman Begins',
        'The beginning of Batman''s saga, from his training in the mountains to his fight against crime in Gotham City.',
        '2005-06-15'),
       ('The Departed',
        'An undercover cop and a criminal mole try to uncover each other''s identities while infiltrating opposing organizations.',
        '2006-10-06'),
       ('The Dark Knight',
        'Batman faces off against the Joker, a psychotic criminal who plunges Gotham City into chaos.',
        '2008-07-18'),
       ('Inception',
        'A thief skilled in stealing corporate secrets through the use of dream-sharing technology is offered a chance to have his charges dropped if he performs the inverse task.',
        '2010-07-16'),
       ('The Avengers',
        'Nick Fury, director of the international espionage agency S.H.I.E.L.D., assembles a team of superheroes to save the world from an alien invasion led by Asgardian Loki.',
        '2012-04-11'),
       ('Gravity',
        'A medical engineer and an astronaut attempt to survive after an accident destroys their space shuttle and leaves them adrift in space.',
        '2013-10-04'),
       ('Mad Max: Fury Road',
        'In a post-apocalyptic desert, Max helps a rebellious woman and her companions escape from a ruthless tyrant.',
        '2015-05-15'),
       ('La La Land',
        'A jazz pianist falls in love with an aspiring actress in Los Angeles, but their divergent paths to success put them at odds.',
        '2016-12-25'),
       ('Black Panther',
        'After the death of his father, T''Challa returns home to take his place as king of Wakanda and confront an ancient enemy.',
        '2018-02-16'),
       ('Parasite',
        'A poor, unemployed family manipulates a wealthy family into employing them one by one.',
        '2019-05-30'),
       ('Nomadland',
        'A sixty-year-old woman loses everything in the Great Recession and embarks on a journey through the American West,
        living as a modern-day nomad.',
        '2021-02-19'),
       ('Dune',
        'A science fiction story about a young nobleman who embarks on an epic journey to save his people.',
        '2021-09-03'),
       ('The Batman',
        'Gotham City''s vigilante investigates a series of murders that lead to a conspiracy involving his own family.',
        '2022-03-04');

INSERT INTO series (title, description, release_date)
VALUES ('Stranger Things',
        'A group of kids uncover a mysterious laboratory experiment and a strange, little girl with telekinetic powers.',
        '2016-07-15'),
       ('Breaking Bad',
        'A high school chemistry teacher diagnosed with terminal cancer uses his chemical knowledge to cook and sell crystal meth to ensure his family''s financial security.',
        '2008-01-20'),
       ('Game of Thrones',
        'Nine noble families fight for control over the mythical lands of Westeros, while an ancient enemy returns after being dormant for millennia.',
        '2011-04-17');

INSERT INTO series_seasons (series, order_number)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 2);

INSERT INTO series_episodes (season, order_number, title)
VALUES (1, 1, 'Chapter One: The Vanishing of Will Byers'),
       (1, 2, 'Chapter Two: MKUltra'),
       (1, 3, 'Chapter Three: Holly, Jolly'),
       (2, 1, 'Chapter One: Trick or Treat, Freak'),
       (2, 2, 'Chapter Two: When Barb Hangs, You Hang'),
       (2, 3, 'Chapter Three: My Poor Monster'),
       (5, 1, 'Winter Is Coming'),
       (5, 2, 'The Kingsroad'),
       (5, 3, 'Lord Snow'),
       (6, 1, 'The North Remembers'),
       (6, 2, 'The Night Lands'),
       (6, 3, 'What Is Dead May Never Die');

INSERT INTO categories (name)
VALUES ('Crime'),
       ('Comedy'),
       ('Romance'),
       ('Thriller'),
       ('Animation'),
       ('Adventure'),
       ('Action'),
       ('Drama'),
       ('Fantasy'),
       ('Sci-Fi');

INSERT INTO film_categories (film, category)
VALUES (1, 1),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 6),
       (6, 8),
       (7, 2),
       (8, 6),
       (9, 2),
       (10, 2),
       (11, 6),
       (12, 2),
       (13, 6),
       (14, 1),
       (15, 1),
       (16, 1),
       (17, 6),
       (18, 7),
       (19, 7),
       (20, 7),
       (21, 7),
       (22, 7),
       (23, 7),
       (24, 7),
       (25, 7),
       (26, 6),
       (27, 7),
       (28, 1),
       (29, 7),
       (30, 1),
       (31, 1),
       (32, 7);

INSERT INTO series_categories (series, category)
VALUES (1, 1),
       (1, 3),
       (2, 1),
       (3, 1),
       (3, 3);
