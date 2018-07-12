
/* region users */
/* ============================================== USERS ===============================================  */

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
  groups integer UNIQUE,

  FOREIGN KEY(role) REFERENCES role(id),
  FOREIGN KEY(settings) REFERENCES userSettings(id),
  FOREIGN KEY(musicProfile) REFERENCES musicProfiles(id),
  FOREIGN KEY (groups) REFERENCES group_list(id)

);

/* region groups */
/* ============================================== GROUPS ==============================================  */

CREATE TABLE IF NOT EXISTS groups(

  id integer,
  userID integer,

  FOREIGN KEY (userID) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS group_list(

  id integer,
  groupdID,

  FOREIGN KEY (groupdID) REFERENCES groups(id)

);
/* endregion */

CREATE TABLE IF NOT EXISTS musicProfiles(
  id integer PRIMARY KEY,
  songs text
);

/* region misc */
/* =============================================== MISC ===============================================  */
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


/* endregion */
/* endregion */




/* region music */
/* ============================================== MUSIC ===============================================  */
create table IF NOT EXISTS songs(
  id integer PRIMARY KEY,
  name text NOT NULL,
  path text NOT NULL,
  album integer,
  artist integer NOT NULL,
  number integer NOT NULL,

  FOREIGN KEY (album) REFERENCES albums(id),
  FOREIGN KEY (artist) REFERENCES multi_artist(id)
);

create table IF NOT EXISTS albums(
  id integer PRIMARY KEY,
  name text NOT NULL,
  artist integer NOT NULL,
  total_track integer NOT NULL,
  year integer NOT NULL,
  genre text NOT NULL,
  metadata text NOT NULL,

  FOREIGN KEY (artist) REFERENCES artists(id)
);

create table IF NOT EXISTS artists(
  id integer PRIMARY KEY,
  name text NOT NULL,
  genre integer,
  metadata text,

  FOREIGN KEY (genre) REFERENCES genres(id)
);

create table if NOT EXISTS multi_artist(
  id integer,
  artistID integer,
  FOREIGN KEY (artistID) REFERENCES artists(id)
);

create table IF NOT EXISTS genres(
  id integer PRIMARY KEY,
  name text NOT NULL
);

/* endregion */

/* region playlist */
/* ============================================= PLAYLIST =============================================  */

create table IF NOT EXISTS playlist(
  id integer PRIMARY KEY,
  groupID integer,
  description text,

  FOREIGN KEY (groupID) REFERENCES groups(id)
);

create table IF NOT EXISTS playlist_item(
  id integer PRIMARY KEY,
  playlistID integer,
  songID integer,
  FOREIGN KEY (playlistID) REFERENCES playlist(id),
  FOREIGN KEY (songID) REFERENCES songs(id)
);


/* endregion */

