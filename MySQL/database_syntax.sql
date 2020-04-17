#Creating a database.
CREATE DATABASE database_name;
Creates database with the given name.
If the database_name doesn't exist it will be created.
If the database_name already exists then the mysql will display an error.

CREATE IF NOT EXISTS database_name;
Checks if the database doesn't exist before creating it.
If the database_name doesn't exist, the condition will be true and
the database_name will be created.
If the database_name already exists, the condition will be false and
the database_name will be attempted to be created, then mysql will
not show an error, but will display a warning.

#Droping a database.
DROP DATABASE database_name;
Drops or deletes the given database from the server.
If the database_name already exists, it will be droped.
If the database_name doesn't exist, it will display an error.

DROP DATABASE IF EXISTS database_name;
Checks if the database exists before droping it.
If the database_name exists, the condition will be true, and the
database_name will be droped.

If the database_name doesn't exist, the condition will be false,
and the database_name will not attempted to be droped,
mysql will display an error, but will display an error.

#Use a specific database, required before doing any alteration or query.
USE database_name;

#Show all available databases.
SHOW DATABASES;

#Show the database that has the same name as name.
SHOW DATABASES LIKE 'name';