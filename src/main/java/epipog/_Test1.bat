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
find "Invalid argument for -D option" err >res
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
find "Invalid argument for -T option" err >res
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: set collection with arguments - valid
java -cp .;%BUILD% epipog -c foo 2>err
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

echo Test: delete collection
java -cp .;%BUILD% epipog -c foo -x 
IF %ERRORLEVEL% NEQ 0 ( echo FAILED ) ELSE ( echo PASSED )

del err res