set BUILD=..\..\..\..\build

javac -d %BUILD% -cp %BUILD%;%BUILD%\classes\main *.java

IF %ERRORLEVEL% NEQ 1 ( 
	java -cp .;%BUILD% _Test5 >out
	type out | find "FAILED"
	type out | find "Test:" /c >res
	set /p t1= <res
	@echo %t1%
	
	java -cp .;%BUILD% _Test6 >out
	type out | find "FAILED"
	type out | find "Test:" /c >res
	set /p t2= <res
	@echo %t2%
	
	
	set /a tc=%t1% + %t2%
	@echo TC %tc%
	del out res
)