set BUILD=..\..\..\..\build
javac -d %BUILD% -cp %BUILD%;%BUILD%\classes\main *.java

echo off
IF %ERRORLEVEL% NEQ 1 (
	goto test
)

goto end

:test  
	_Test1 | find "FAILED"
	type _Test1.bat | find "Test:" /c
	
:end
echo done