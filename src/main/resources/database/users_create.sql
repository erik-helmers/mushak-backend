CREATE TABLE IF NOT EXISTS users(

  id integer PRIMARY KEY NOT NULL,

  username text NOT NULL,
  name text NOT NULL,

  password text NOT NULL,
  salt text NOT NULL,
  
  has_image boolean,

  role integer default 1,
  settings integer UNIQUE,
  musicProfile integer UNIQUE,

  FOREIGN KEY(role) REFERENCES role(id),
  FOREIGN KEY(settings) REFERENCES userSettings(id),
  FOREIGN KEY(musicProfile) REFERENCES musicProfile(id)

);

CREATE TABLE IF NOT EXISTS userSettings(
  id integer PRIMARY KEY
);


CREATE TABLE IF NOT EXISTS role(
  id integer PRIMARY KEY,
  name text NOT NULL
);

CREATE TABLE IF NOT EXISTS tokens(
  token text,
  expireDate integer,
  userID integer,
  FOREIGN KEY(userID) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS musicProfiles(
  id integer PRIMARY KEY,
  songs text
)



