create table users (
	id SERIAL primary key,
	name varchar(20) NOT NULL,
	email varchar(80) NOT null,
	password varchar(80) NOT NULL
)