CREATE TABLE building (
	id integer PRIMARY KEY,
	version bigint,
	name varchar(256),
	street_address varchar(256),
	zip varchar(5),
	city varchar(150),
	state varchar(40),
	owner varchar(40)
);

CREATE TABLE floor (
	id integer PRIMARY KEY,
	version bigint,
	building_id integer REFERENCES building,
	floor_number integer
);

CREATE TABLE room (
	id integer PRIMARY KEY,
	version bigint,
	floor_id integer REFERENCES floor,
	room_name varchar(256)
);