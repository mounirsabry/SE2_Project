#Creating a table.
CREATE TABLE table_name (
	attrib1_name data_type1,
	.
	.
	.
	attrib2_name data_type2
);

Creates the table if it doesn't exist.
If the table exists it will display an error message.

To avoid the error use IF NOT EXISTS.
Like.
CREATE TABLE IF NOT EXISTS table_name (
...
);

Will work fine if the table doesn't exist,
however if the table already exists it will display an warning message
not no error.

Ex.
CREATE TABLE IF NOT EXISTS students (
int INT,
name VARCHAR(255),
email VARCHAR(255)
);

#Describing a table.
DESCRIBE table_name;
SHOW COLUMNS FROM table_name;
SHOW FIELDS FROM table_name;

Will display the table structure in a nice table.
Field Type NULL KEY Default Extra
.................................

#Display the status of all tables.
SHOW TABLE STATUS;
Will display all alot of details of all tables like
name engine version row_format rows avg_row_length ... etc.
However it will not display the attributes inside each table like
describing do.

#Display the code of the table which can be used to create the table.
SHOW CREATE TABLE table_name;

#Drop the table;
Drops TABLE table_name;
Drops the table if exists.
Will display an error message if the table doesn't exist.

DROP TABLE IF EXISTS table_name;
Drops the table if exists.
Will not display an error message if the table doesn't exist,
however it will display an warning message.

#Renaming the table.
RENAME TABLE table_name TO new_table_name;
Will rename the table_name to the new_table_name.
Will display an error message if the old name is the same name as the 
new name.
You can rename multiple tables with this command each one separated by comma.
Like
RENAME TABLE table_name1 TO new_table_name1, table_name2 TO new_table_name2,....;

#Renaming table using alter.
ALTER TABLE table_name RENAME new_table_name;
Will not display an error message if the new_table_name is
the same as the old one.

#Change the storage engine type.
ALTER TABLE table_name ENGINE = storage_engine_name;
Ex.
ALTER TABLE new1 ENGINE = MYISAN;

#Add an attribute to a table.
The data type is required.
Will display an error if the attribute already exists.

ALTER TABLE table_name ADD attribute_name data_type;
Default behaviour, will add this attribute to the end of table (add it as the last column).

ALTER TABLE table_name ADD attribute_name data_type AFTER attribute_name2;
Will add this attribute after the attribute_name2.

ALTER TABLE table_name ADD attribute_ane data_type FIRST;
Will add this attribute at the first of the table (as the first column).

#Drop an attribute from a table.
ALTER TABLE table_name DROP attribute_name;
Will display an error message if the attribute doesn't exist.

#Changing an attribute's name or data type or both.
#Can be used to change the position of an attribute if it already exists.
We must specify the data_type even if we didn't change it.

ALTER TABLE table_name CHANGE attribute_name new_attribute_name new_data_type newLocation;
The new_attribute_name can be new one or old one.
The new_datatype can be new one or old one.
You can specify new_location or leave it as it.

Will not display an error message even there are no changes.
Ex.
if we have a table called students in which we have an attribute 
called id which is INT.
if we exec the following command.
ALTER TABLE students id id INT;
no error will be displayed even if we have no changes.

We can modify and change inside the table using one alter table.
ALTER TABLE table_name MODIFY ..., CHANGE ...;

#Change a table charset.
ALTER TABLE table_name CONVERT TO CHARACTER SET charset_name;
Ex, ALTER TABLE table_name CONVERT TO CHARACTER SET utf8;

This will convert the charset to utf8_genearl_ci which is currently alias 
for utf8mb3, but in future it will be utf8mb4.
by default the current charset for any new table is utf8mb4, so
you don't have to change the charset to utf8, it already support unicode.