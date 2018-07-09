CREATE TABLE IF NOT EXISTS logins(
  username text NOT NULL UNIQUE,
  password text NOT NULL,
  salt text NOT NULL,
  token text,
  tokenExpireDate date,
  userID integer,
  FOREIGN KEY(userID) REFERENCES users(userID)
);

CREATE TABLE IF NOT EXISTS users(
  userID integer PRIMARY KEY NOT NULL,

  name text NOT NULL,
  image_path text,
  role integer default 0,
  settings integer,
  musicProfile integer,

  FOREIGN KEY(role) REFERENCES role(roleID),
  FOREIGN KEY(settings) REFERENCES userSettings(settingID),
  FOREIGN KEY(musicProfile) REFERENCES musicProfile(musicProfileID)
);

CREATE TABLE IF NOT EXISTS userSettings(
  settingID integer PRIMARY KEY
);


CREATE TABLE IF NOT EXISTS role(
  roleID integer PRIMARY KEY,
  name text NOT NULL
);

CREATE TABLE IF NOT EXISTS musicProfile(
  musicProfileID integer PRIMARY KEY,
  songs text
)



