CREATE TABLE addressApp(
	firstName varchar(255),
	lastName varchar(255),
	street varchar(255),
	city varchar(255),
	postalCode int
);

SELECT * FROM addressApp
SELECT * FROM addressApp WHERE firstName = "user" ORDER BY firstName asc
SELECT * FROM addressApp WHERE firstName = "*" ORDER BY firstName asc
INSERT INTO addressApp (firstName,lastName,street,city,postalCode) VALUES("test","takuya","sampleStreet","sampleCity",123);

