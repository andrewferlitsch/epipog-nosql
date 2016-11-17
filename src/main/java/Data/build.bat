set BUILD=..\..\..\..\build
javac -d %BUILD% -cp %BUILD% *.java

IF %ERRORLEVEL% NEQ 1 ( 
	java -cp .;%BUILD% _Test1 >out
	type out | find "FAILED"
	type out | find "Test:" /c
	del out
)