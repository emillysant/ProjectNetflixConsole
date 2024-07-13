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
    name INTEGER NOT NULL UNIQUE REFERENCES account_profiles
);

CREATE TABLE film_categories (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    film INTEGER NOT NULL REFERENCES films,
    category INTEGER NOT NULL REFERENCES films,

    CONSTRAINT film_category UNIQUE (film, category)
);
