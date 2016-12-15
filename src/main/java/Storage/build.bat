set BUILD=..\..\..\..\build
javac -d %BUILD% -cp %BUILD%;%BUILD%\classes\main *.java

echo off
IF %ERRORLEVEL% NEQ 1 (
	goto test
)

goto end

:test
java -cp .;%BUILD% _Test8 >out
type out | find "FAILED"
type out | find "Test:" /c >res
set /p t8= <res
echo %t8%  
	
java -cp .;%BUILD% _Test9 >out
type out | find "FAILED"
type out | find "Test:" /c >res
set /p t9= <res
echo %t9%
	
set /a tc=%t8% + %t9%
echo TC %tc%
del out res
	
:end
	echo done
