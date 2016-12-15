set BUILD=..\..\..\..\build
echo off

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

echo Test: -I with valid file type (csv)
echo >foo.csv
java -cp .;%BUILD% epipog -I foo.csv
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
del foo.csv

echo Test: -I with valid file type (psv)
echo >foo.psv
java -cp .;%BUILD% epipog -I foo.psv
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
del foo.psv

echo Test: -I with valid file type (tsv)
echo >foo.tsv
java -cp .;%BUILD% epipog -I foo.tsv
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
del foo.tsv

echo Test: -I with valid file type (json)
echo >foo.json
java -cp .;%BUILD% epipog -I foo.json
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )
del foo.json



del err out res \tmp\*.dat \tmp\*.sch