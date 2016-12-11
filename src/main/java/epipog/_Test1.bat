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

echo Test: delete collection
java -cp .;%BUILD% epipog -c foo -x 
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: alternate volume
mkdir \tmp\epipog
java -cp .;%BUILD% epipog -v /tmp/epipog -c tom
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: show collections in storage
java -cp .;%BUILD% epipog -v /tmp/epipog -L



del err res \tmp\*.dat \tmp\*.sch