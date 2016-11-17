set BUILD=..\..\..\..\build

javac -d %BUILD% -cp %BUILD%;%BUILD%\classes\main *.java

IF %ERRORLEVEL% NEQ 1 ( 
	java -cp .;%BUILD% _Test5 >out
	type out | find "FAILED"
	type out | find "Test:" /c
	
	java -cp .;%BUILD% _Test6 >out
	type out | find "FAILED"
	type out | find "Test:" /c
	del out
)