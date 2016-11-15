set BUILD=..\..\..\..\build

javac -d %BUILD% -cp %BUILD%;%BUILD%\classes\main *.java

IF %ERRORLEVEL% NEQ 1 ( 
	java -cp .;%BUILD% _Test5 | find "FAILED"
	java -cp .;%BUILD% _Test6 | find "FAILED"
)