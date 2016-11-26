set BUILD=..\..\..\..\build
javac -d %BUILD% -cp %BUILD% *.java

IF %ERRORLEVEL% NEQ 1 ( 
	java -cp .;%BUILD% _Test1 >out
	type out | find "FAILED"
	type out | find "Test:" /c >res
	set /p t1= <res
	@echo TC %t1%
	
	del out res
)