--MySQL datatypes.

--Note: specifying a pre-defined size datatype size is deprecated
--and will be removed in a future release.
--Ex, when you add an attribute which has for example type INT,
--since type INT already has a size associated with it, you shouldn't 
--specify a size for it, like INT(5).

/* Numerics.

Integers:
	TINYINT => Maximum Characters [4]
	SMALLINT => Maximum Characters [6] 32767
	MEDIUMINT => Maximum Characters [9] 
	INT => Maximum Characters[11] | 2,147,483,647 Maximum Value in MySQL Int Type.
	BIGINT => Maximum Characters [20]
	
Fractions:
	DECIMAL
	FLOAT
	DOUBLE
	REAL => Synonym for double.

Bit => writes in bits, must specify the size.
SERIAL => BIGINT, AUTO_INCREMENT.
BOOLEAN => true, false
		=> tinyint(1).
		
Data and Time.

DATE	=> YYYY-MM-DD 1000-01-01 to 9999-12-31 
DATETIME	=> YYYY-MM-DD HH:Mi:SS Store the date as absolute.
TIMESTAMP	=> YYYY-MM-DD HH:Mi:SS 1970-01-01 00:00:01 to 2038-01-09 03:14:07 => was the value in 2016.
time stamp supports adding or relative date or time to from specific time.
TIME	=> HH:Mi:SS -838:59:59 to 838:59:59
YEAR	=> YYYY allowed values are 70 (1970) to 69(2069) in case of entering two digits.
allowed values are 1901 to 2155 and 0000 in case of entering four digits.

String.

CHAR => character
Store fixed value.
MAX Character 255
Faster than VARCHAR
Use static memory.

VARCHAR => variable character.
Store variable value.
Max Character MySQL v 5.0.3 => 255 | v 5.0.3+ => 65,535
Slower than VARCHAR
Use dynamic memory.

TEXT => Store string.
Used for articles, etc...
Deal & compared depend on charset.

Every one of the following has its range, but all of them all TEXT.
TINYTEXT 
TEXT
MEDIUMTEXT
LONGTEXT

BLOB => Binary large object.
Used for binary files like images and files.
Deal & compared depend on numeric value of the bytes

Every one of the following has its range, but all of them all BLOB.
TINYBLOB
MEDIUMBLOB
BLOB
LONGBLOB

BINARY 0 to 255
VARBINARY 0 to 255 before MySQL 5.0.3 and 0 to 65,535 in 5.0.3 and later versions.
They are the same as CHAR and VARCHAR but stores binary strings
instead of non-binary strings.

ENUM
A group of values you choose from.
You must specify the value when you create the attribute.
When inserting you MUST choose one of the pre-defined values.
The same as enum in programming, and represents the checkbox in web.
SET
The same idea as the enum but you can choose a set (multiple values)
from the possible values.
For example, it can be used to represent tags. */