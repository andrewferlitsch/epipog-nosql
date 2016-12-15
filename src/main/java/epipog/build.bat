set BUILD=..\..\..\..\build
javac -d %BUILD% -cp %BUILD%;%BUILD%\classes\main *.java

IF %ERRORLEVEL% NEQ 1 (
	goto test
)

goto end

:test  
	_Test1 

	
:end
echo done