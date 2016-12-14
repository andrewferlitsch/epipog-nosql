set BUILD=..\..\..\..\build
javac -d %BUILD% -cp %BUILD%;%BUILD%\classes\main *.java

echo off
IF %ERRORLEVEL% NEQ 1 ( 
	goto test
)

goto end

:test	
java -cp .;%BUILD% _Test2 >out
type out | find "FAILED"
type out | find "Test:" /c >res
set /p t2= <res
echo %t2%
	
java -cp .;%BUILD% _Test3 >out
type out | find "FAILED"
type out | find "Test:" /c >res
set /p t3= <res
echo %t3%
	
java -cp .;%BUILD% _Test4 >out
type out | find "FAILED"
type out | find "Test:" /c >res
set /p t4= <res
echo %t4%
	
set /a tc=%t2% + %t3% + %t4%
echo TC %tc%
del out res

:end
echo done