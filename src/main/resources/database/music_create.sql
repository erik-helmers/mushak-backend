create table IF NOT EXISTS songs(
    id integer PRIMARY KEY,
    name text NOT NULL,
    path text NOT NULL,
    album text,
    artist text,
    number integer
    );

create table IF NOT EXISTS albums(
  id integer PRIMARY KEY,
  name text NOT NULL,
  songs text,
  artist text,
  total_track integer,
  year integer,
  genre text,
  metadata text
);

create table IF NOT EXISTS artists(
  id integer PRIMARY KEY,
  name text NOT NULL,
  songs text,
  albums text,
  metadata text
);

create table IF NOT EXISTS genres(
  id integer PRIMARY KEY,
  name text NOT NULL
)