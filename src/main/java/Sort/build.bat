set BUILD=..\..\..\..\build
javac -d %BUILD% -cp %BUILD%;%BUILD%\classes\main *.java

echo off
IF %ERRORLEVEL% NEQ 1 ( 
	goto test
)

goto end

:test	
java -cp .;%BUILD% _Test14 >out
type out | find "FAILED"
type out | find "Test:" /c >res
set /p t14= <res
echo %t14%
	
set /a tc=%t14%
echo TC %tc%
del out res
