set BUILD=..\..\..\..\build
javac -d %BUILD% -cp %BUILD%;%BUILD%\classes\main *.java

IF %ERRORLEVEL% NEQ 1 (  
	java -cp .;%BUILD% _Test7 >out
	type out | find "FAILED"
	type out | find "Test:" /c >res
	@set /p t7= <res
	@echo %t7%
	
	del out res
)