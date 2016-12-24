set BUILD=..\..\..\..\build
echo off
del \tmp\*.dat \tmp\*.sch 2>err

echo Test: No Arguments
java -cp .;%BUILD% epipog 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Usage" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set collection w/o arguments - invalid
java -cp .;%BUILD% epipog -c 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Missing argument for -c option" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set datastore w/o arguments - invalid
java -cp .;%BUILD% epipog -D 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Missing argument for -D option" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set datastore with invalid argument
java -cp .;%BUILD% epipog -D notvalid 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Invalid argument for -D option: notvalid" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: insert w/o arguments - invalid
java -cp .;%BUILD% epipog -i 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Missing argument for -i option" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: import file w/o arguments - invalid
java -cp .;%BUILD% epipog -I 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Missing argument for -I option" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: import file, nonexist file
java -cp .;%BUILD% epipog -I nonexist 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "File does not exist: nonexist" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set index w/o arguments - invalid
java -cp .;%BUILD% epipog -k 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Missing argument for -k option" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set reader w/o arguments - invalid
java -cp .;%BUILD% epipog -R -I tests\1.csv 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Missing argument for -R option" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set reader with invalid argument
java -cp .;%BUILD% epipog -R notvalid -I tests\1.csv 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Invalid argument for -R option: notvalid" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set schema w/o arguments - invalid
java -cp .;%BUILD% epipog -s 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Missing argument for -s option" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set schema w/o arguments - invalid
java -cp .;%BUILD% epipog -S 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Missing argument for -S option" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set file type w/o arguments - invalid
java -cp .;%BUILD% epipog -t 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Missing argument for -t option" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set file type with invalid argument
echo >out
java -cp .;%BUILD% epipog -t notvalid -I out 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Invalid argument for -t option: notvalid" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set storage w/o arguments - invalid
java -cp .;%BUILD% epipog -T 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Missing argument for -T option" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set storage with invalid argument
java -cp .;%BUILD% epipog -T notvalid 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Invalid argument for -T option: notvalid" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set volume w/o arguments - invalid
java -cp .;%BUILD% epipog -v 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Missing argument for -v option" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set volume, non-existent
java -cp .;%BUILD% epipog -v /nonexist 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Storage Volume does not exist: /nonexist" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set volume, not a directory
java -cp .;%BUILD% epipog -v /Windows/write.exe 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Storage Volume is not a directory: /Windows/write.exe" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set collection with arguments - valid
java -cp .;%BUILD% epipog -c foo 2>err
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: alternate volume
rmdir \tmp\epipog \s 2>err
mkdir \tmp\epipog
java -cp .;%BUILD% epipog -v /tmp/epipog -c tom
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: show collections in storage
java -cp .;%BUILD% epipog -v /tmp/epipog -l >out
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
find "tom" out >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: Show schema without specifying collection
java -cp .;%BUILD% epipog -L 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "No schema found" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: Show schema 
java -cp .;%BUILD% epipog -v /tmp/epipog -c tom -S field1,field2
java -cp .;%BUILD% epipog -L -v /tmp/epipog -c tom >out
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
find "[field1=2, field2=2]" out >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: delete collection
java -cp .;%BUILD% epipog -c foo -x 
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: delete collection alternate volume
java -cp .;%BUILD% epipog -c tom -v /tmp/epipog -x
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: Create Collection, then set schema
java -cp .;%BUILD% epipog -c cat 
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -c cat -S field1,field2:integer
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: Collection already has schema
java -cp .;%BUILD% epipog -c cat -S field1,field2:integer 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Collection already has a schema" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: Unrecognized file type and no -t option
java -cp .;%BUILD% epipog -I out 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Unrecognized file type: out" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )


echo Test: insert, malformed argument
java -cp .;%BUILD% epipog -i field1,field2 2>err
IF %ERRORLEVEL% NEQ 1 ( echo FAILED ) ELSE ( echo PASSED )
find "Invalid field format for -i option: field1" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: insert into CSV store
java -cp .;%BUILD% epipog -i field1:1,field2:2 -S field1,field2 -D csv
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: insert into binary store
java -cp .;%BUILD% epipog -i field1:1,field2:2 -S field1:short,field2:integer
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: insert into json store
java -cp .;%BUILD% epipog -i field1:1,field2:2 -S field1,field2 -D json
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: import w/o schema
java -cp .;%BUILD% epipog -I tests/1.csv -c tmp -D json
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: import w/o schema with reader = mem
java -cp .;%BUILD% epipog -I tests/1.csv -c tmp -D json -R mem
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: import w/o schema with reader = line
java -cp .;%BUILD% epipog -I tests/1.csv -c tmp -D json -R line
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: import w/o schema with reader = mapped
java -cp .;%BUILD% epipog -I tests/1.csv -c tmp -D json -R mapped
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: -I with valid file type (psv)
echo >foo.psv
java -cp .;%BUILD% epipog -I foo.psv
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp
del foo.psv

echo Test: -I with valid file type (tsv)
echo >foo.tsv
java -cp .;%BUILD% epipog -I foo.tsv
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp
del foo.tsv

echo Test: -I with valid file type (json)
echo >foo.json
java -cp .;%BUILD% epipog -I foo.json
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp
del foo.json

echo Test: select all (strings) from binary store
java -cp .;%BUILD% epipog -I tests/1.csv -c tmp -D binary
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "one,two" out >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: select all (strings) from csv store
java -cp .;%BUILD% epipog -I tests/1.csv -c tmp -D csv
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "one,two" out >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: select fields from csv store
java -cp .;%BUILD% epipog -s "field2,field1" -c tmp >out
find "two,one" out >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: select all (strings) from psv store
java -cp .;%BUILD% epipog -I tests/1.csv -c tmp -D psv
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "one,two" out >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: select fields from psv store
java -cp .;%BUILD% epipog -s "field2,field1" -c tmp >out
find "two,one" out >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: select all (strings) from tsv store
java -cp .;%BUILD% epipog -I tests/1.csv -c tmp -D tsv
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "one,two" out >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: select fields from tsv store
java -cp .;%BUILD% epipog -s "field2,field1" -c tmp >out
find "two,one" out >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: select all (strings) from json store
java -cp .;%BUILD% epipog -I tests/1.csv -c tmp -D json
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "one,two" out >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: select fields from json store
java -cp .;%BUILD% epipog -s "field2,field1" -c tmp >out
find "two,one" out >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: select all (int,short,long) from binary store
java -cp .;%BUILD% epipog -I tests/1a.csv -c tmp -Sfield1:short,field2:integer,field3:long
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "1,2,3" out >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: select all (float,double) from binary store
java -cp .;%BUILD% epipog -I tests/1b.csv -c tmp -Sfield1:float,field2:double
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "1.0,2.0" out >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: select all (char,boolean) from binary store
java -cp .;%BUILD% epipog -I tests/1c.csv -c tmp -Sfield1:char,field2:boolean
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "x,true" out >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: select all (fixed string) from binary store
java -cp .;%BUILD% epipog -I tests/1d.csv -c tmp -Sfield1:string16,field2:string32,field3:string64,field4:string128,field5:string256
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "one,two,three,four,five" out >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: select all (date,time) from binary store
java -cp .;%BUILD% epipog -I tests/1e.csv -c tmp -Sfield1:date,field2:time
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "2016-04-02,10:23:16" out >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -x tmp

echo Test: select datamodel=state for all boolean values with Binary Store
java -cp .;%BUILD% epipog -I tests/1f.csv -c tmp -Sfield1:boolean,field2:boolean,field3:boolean,field4:boolean,field5:boolean,field6:boolean -V
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "true,true,false,false,true,false" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: select datamodel=state for all boolean values with CSV Store
java -cp .;%BUILD% epipog -I tests/1f.csv -c tmp -Sfield1:boolean,field2:boolean,field3:boolean,field4:boolean,field5:boolean,field6:boolean -V -D csv
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "true,true,false,false,true,false" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: select datamodel=state for all boolean values with JSON Store
java -cp .;%BUILD% epipog -I tests/1f.csv -c tmp -Sfield1:boolean,field2:boolean,field3:boolean,field4:boolean,field5:boolean,field6:boolean -V -D json
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "true,true,false,false,true,false" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: select datamodel=state for hex integer values with Binary Store
java -cp .;%BUILD% epipog -I tests/1g.csv -c tmp -Sfield1:short,field2:integer,field3:long -V
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "1,10,2000" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: select datamodel=state for hex integer values with CSV Store
java -cp .;%BUILD% epipog -I tests/1g.csv -c tmp -Sfield1:short,field2:integer,field3:long -V -D csv
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "1,10,2000" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: select datamodel=state for hex integer values with JSON Store
java -cp .;%BUILD% epipog -I tests/1g.csv -c tmp -Sfield1:short,field2:integer,field3:long -V -D json
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "1,10,2000" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: select datamodel=state for unicode char values with Binary Store
java -cp .;%BUILD% epipog -I tests/1h.csv -c tmp -Sfield1:char -V
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "1,6" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: select datamodel=state for unicode char values with CSV Store
java -cp .;%BUILD% epipog -I tests/1h.csv -c tmp -Sfield1:char -V -D csv
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "1,6" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: select datamodel=state for unicode char values with JSON Store
java -cp .;%BUILD% epipog -I tests/1h.csv -c tmp -Sfield1:char -V -D json
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "1,6" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: select datamodel=state for oct integer values with Binary Store
java -cp .;%BUILD% epipog -I tests/1i.csv -c tmp -Sfield1:short,field2:integer,field3:long -V
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "97,97,97" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: select datamodel=state for oct integer values with CSV Store
java -cp .;%BUILD% epipog -I tests/1i.csv -c tmp -Sfield1:short,field2:integer,field3:long -V -D csv
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "97,97,97" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: select datamodel=state for oct integer values with JSON Store
java -cp .;%BUILD% epipog -I tests/1i.csv -c tmp -Sfield1:short,field2:integer,field3:long -V -D json
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "97,97,97" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: select datamodel=state for multipler integer values with Binary Store
java -cp .;%BUILD% epipog -I tests/1j.csv -c tmp -Sfield1:short,field2:integer,field3:long -V
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "1000,2000000,3000000000" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: select datamodel=state for multipler integer values with CSV Store
java -cp .;%BUILD% epipog -I tests/1j.csv -c tmp -Sfield1:short,field2:integer,field3:long -V -D csv
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "1000,2000000,3000000000" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: select datamodel=state for multipler integer values with JSON Store
java -cp .;%BUILD% epipog -I tests/1j.csv -c tmp -Sfield1:short,field2:integer,field3:long -V -D json
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "1000,2000000,3000000000" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: insert/select - no header CSV in binary store
java -cp .;%BUILD% epipog -I tests/1k.csv -c tmp -Sfield1:short,field2:integer,field3:long -n
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "10,20,30" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: insert/select - no header CSV in CSV store
java -cp .;%BUILD% epipog -I tests/1k.csv -c tmp -Sfield1:short,field2:integer,field3:long -n -D csv
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "10,20,30" out >res
java -cp .;%BUILD% epipog -x tmp

echo Test: insert/select - no header CSV in json store
java -cp .;%BUILD% epipog -I tests/1k.csv -c tmp -Sfield1:short,field2:integer,field3:long -n -D json
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
java -cp .;%BUILD% epipog -s "*" -c tmp >out
find "10,20,30" out >res
java -cp .;%BUILD% epipog -x tmp

REM null fields

del err out res \tmp\*.dat \tmp\*.sch