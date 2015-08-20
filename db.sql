DROP SEQUENCE building_id_seq;
DROP SEQUENCE floor_id_seq;
DROP SEQUENCE room_id_seq;

DROP TABLE building;
DROP TABLE floor;
DROP TABLE room;

CREATE SEQUENCE building_id_seq;
CREATE TABLE building (
	uid integer PRIMARY KEY DEFAULT nextval('building_id_seq'),
	name varchar(256),
	street_address varchar(256),
	zip varchar(5),
	city varchar(150),
	state varchar(40),
	owner varchar(40)
);

CREATE SEQUENCE floor_id_seq;
CREATE TABLE floor (
	uid integer PRIMARY KEY DEFAULT nextval('floor_id_seq'),
	building_uid integer REFERENCES building,
	floor_number integer
);

CREATE SEQUENCE room_id_seq;
CREATE TABLE room (
	uid integer PRIMARY KEY DEFAULT nextval('room_id_seq'),
	floor_uid integer REFERENCES floor,
	room_number integer
);