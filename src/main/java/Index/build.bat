set BUILD=..\..\..\..\build
javac -d %BUILD% -cp %BUILD%;%BUILD%\classes\main *.java

echo off
IF %ERRORLEVEL% NEQ 1 ( 
	goto test
)

goto end

:test	
java -cp .;%BUILD% _Test13 >out
type out | find "FAILED"
type out | find "Test:" /c >res
set /p t13= <res
echo %t13%
	
set /a tc=%t13%
echo TC %tc%
del out res

:end
echo done
