--Show the current constraints or indexes of a given table.
SHOW INDEXES FROM table_name;

--Show the current constraints of a given table using information_schema database.
SELECT * FROM information_schema.KEY_COLUMN_USAGE WHERE 
TABLE_NAME = "table_name";

--Drop an index or constraint from table.
DROP INDEX index_name ON table_name;
ALTER TABLE table_name DROP INDEX index1, DROP INDEX index2,....;


--Mark an existing attribute as not null;
ALTER TABLE table_name MODIFY attribute_name datatype NOT NULL;

--Create new attribute and mark it as not null at the same time.
ALTER TABLE table_name ADD attribute_name datatype NOT NULL;

/* When you specify a attribute as not null, you can't insert null value
into this attribute (obivously), however you can insert empty value
string into it, like '' or "" in case of it doesn't have numeric
datatype. */

--Mark an attribute as unique.
ALTER TABLE table_name ADD UNIQUE(attribute_name);
ALTER TABLE	table_name ADD CONSTRAINT constraint_name
UNIQUE(attribute1, attribute2, .....);

/* In both, if the column already has duplicate values, mysql will
display an error duplicate entry, and the constraint will not be created. */

/* In the first one you didn't specify specify the constraint name,
so by default it will use the attribute_name as the name of the constraint. */

/* In the second one it is a better way, since specifing the constraint name
allows you to exactly know the name given to the constraint. */

/* In case you assigned the same constraint twice, mysql will show an error
duplicate key name. */

/* In case you assigned two different constraint names but the same contraint it will
not get rejected however mysql will display an warning,
duplicate index, this is deprecated and will be disallowed in a future release. */

--You can mix the not null and unique into one constraint.
ALTER TABLE table_name add attribute_name datatype NOT NULL UNIQUE;

/* PRIMARY KEY, when we mark an attribute as primary key, then the attribute will be not null and unique.
Not only this, also we can only have one primary key per table.	 */

--Specifying the primary key while creating the table.
--First way.
CREATE TABLE table_name (
	........
	attribute_name datatype PRIMARY KEY,
	........
);

--Second way.
CREATE TABLE table_name (
	.......
	.......
	PRIMARY KEY (attribute_name),
	.......
);

--Specifying the primary key after the table created.
ALTER TABLE table_name ADD PRIMARY KEY(attribute_name);

/* In case you tried to add another primary key to the table while the table already has one,
you will get the error "multiple primary key defined", even you assigned the same attribute(s) in the current primary key
as the new primary key.
Note: You will get this error if you use PHPMYADMIN, apparently it checks for the existence of primary key to delete it,
before trying to assign a new primary key behind the scenes. */

--Drop the primary key of a table.
ALTER TABLE table_name DROP PRIMARY KEY;

/* Notice since we only have one primary key per table, we don't have to specify the primary key.
In case you tried to drop a primary key of a table that doesn't have a primary key,
mysql will show an error.
The PRIMARY KEY INDEX IS ALWAYS PRIMARY. */

/* When we mark a attribute as a foreign key, that means that the attribute references another attribute in another table,
the other attribute must be primary key, the foreign key is used to represent relationship between tables.
Ex, when we have a table of orders, each order has been made by a client, we should be able to tell who made this order. */

--Specifying the foreign key while creating the table.
CREATE TABLE table_name (
	--Defining the attributes.
	FOREIGN KEY attribute_name REFERENCES the_other_table_name(attribute_name);
	--The rest.
);
/* You can't create the foreign in the same sentence you create the attribute.
The following query will result in error. */
CREATE TABLE table_name (
	--Defining some attriubtes.
	attribute_name datatype FOREIGN KEY REFERENCES the_other_table_name(attriubte_name);
	--Rest.
);

/* You can't TRUNCATE or DROP a table which is referenced by another
table(s), either DELETE * instead of TRUNCATE in case of deleting the records,
or break all the foreign keys that references this table before trying to drop it. */

--Specifying the foreign key after creating the table.
--It is highly recommended to specify a constraint name to ease out the process of updateing or droping the foreign key.
ALTER TABLE table_name ADD CONSTRAINT constraint_name FOREIGN KEY attribute_name
REFERENCES the_other_table_name(attriubte_name)
/* On updata and on delete options. */;


--The child table: is the table who has the foreign key.
--The parent table: is the table who is referenced by the foreign key.

--Updates and deletes options.
/* We have 5 reference options for each one, so we have 25 combinations.
CASCASE, SET NULL, RESTRICT, NO ACTION, SET DEFAULT.
This options describes what will happen to the child table when we update or delete on the parent table.
By default, if you didn't specify the action will be RESTRICT.

Cascade: any change in the parent table, reflects on the child table.
On update the referencd attribute, its value will be updated in the child table
with the new value.
On delete, the values of the referencd attribute of the removed records, will be removed with 
its records from the child table.

Set Null: when we update or delete a record(s) from the parent table
the foreign key value in the child table will be set to null on the affected records.
Ex, if the client of the software asks me to not delete the made orders
if the clients has been deleted from the database.

RESTRICT: Will reject delete or update any value in the parent table which exists in child table.

NO ACTION: the same as RESTRICT, but NO ACTION performs an check while RESTRICT doesn't.

SET DEFAULT: as the name implies, when we delete or update a value in the parent table which exists in the child table, 
the value in the child table will be set to the default value specified to this attribute.
NOTE: this is recongized by the mysql parser. however this action is rejected by both InnoDB and NDB tables.

*/

--Updating or droping the foreign key.
/* If you want to modify the foreign key constraint, you have to drop it and recreate it, there is no modify foreign key.
When you create a foreign key in the table, mysql adds a foreign key constraint to the table and an index to the table.
If you specify the constraint name it will be used as the constraint name and the index name.
If you don't, then mysql will use default generated name for the constraint name and add the attribute's name as the index'name,
They will always be the same.
Therefore it is highly recommended to give the foreign key constraint a name when you create it, so you can delete the constraint
and the index with the same name. */

/* The steps to drop the foreign key is to drop the foreign key and drop the created index.
First you need to know their names, if you specify a name when creating it you use */
SHOW CREATE TABLE table_name;
/* to get the name of constraint(and index). */

/* If you didn't, then you have to get both names from the database.
To get the name of the name of the constraint name you will use */
SHOW CREATE TABLE table_name;

/* Then you will use */
SHOW INDEXES FROM table_name;
/* to get the index name */

--The following steps must be done in this order.
ALTER TABLE table_name DROP FOREIGN KEY foreign_key_constraint_name;

ALTER TABLE table_name DROP INDEX foreign_key_index_name;
/* OR */
DROP INDEX foreign_key_index_name ON table_name;




