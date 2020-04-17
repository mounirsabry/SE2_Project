--Insert new record into table.
INSERT INTO table_name (attribute1, attribute2, ...) VALUES (value1, value2, .....);
/* Will insert a new record in the database with each attribute selected has the coresponding value.
Using this method you must specify all the attributes that does not have default value. */

INSERT INTO table_name VALUES (value1, value2, ....);
/* Will insert a new record in the database with the order of the attributes has the coresponding value,
Ex, attribute1 in the table will has value1, attribute2 in the table will has value2, .... to the end.
Using this method you must specify the value of all the attributes in the table. */

--Update records from table.
UPDATE table_name 
SET attribute1 = value1, column2 = value2, /* To the end of desired attributes */
WHERE /* List of constraint */;

--Delete records from table.
TRUNCATE table_name;
/* Will delete the table and reconstruct the table to delete all the data.
Can not be used if the table is referenced from another table, use DELETE instead.
Will take low time, since it doesn't delete the table row by row, will be faster if the table is full of data.
However if the table has few lines or empty it will take longer. */

DELETE FROM table_name;
--Will delete all the records in the table.
 
DELETE FROM table_name WHERE constraint1, constraint2, ....;
--Will delete all the records that satisfies all the given constraints.

--Select records from table.
SELECT * FROM table_name;
--Will return all the attributes with all the data from the table.

SELECT attribute1, attribute2, /* the rest of attributes */ FROM table_name;
--Will return the selected attributes values from all the records from the table.

SELECT /* some attributes */ FROM table_name WHERE constraint1, constraint2, /* the rest of constraint */;
--Will return the selected attributes values from the records that satisfies all the constraints given.

--Joins.
SELECT * FROM table_name1 JOIN table_name2 ON table_name1.attribute = table_name2.attribute;
/* Will join the two tables and display the records, where the values of table_name2.attribute exists in the table_name1. */

SELECT * FROM table_name1 JOIN table_name2 ON table_name1.attribute = table_name2.attribute;
/* Will join the two tables and the display the records, where the values constrainted by the constraints exists in the table_name1 */
