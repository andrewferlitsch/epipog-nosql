set BUILD=..\..\..\..\build

javac -d %BUILD% -cp %BUILD%;%BUILD%\classes\main *.java

IF %ERRORLEVEL% NEQ 1 ( 
	java -cp .;%BUILD% _Test5 >out
	type out | find "FAILED"
	type out | find "Test:" /c >res
	@set /p t5= <res
	@echo %t5%
	
	java -cp .;%BUILD% _Test6 >out
	type out | find "FAILED"
	type out | find "Test:" /c >res
	@set /p t6= <res
	@echo %t6%
	
	
	set /a tc=%t5% + %t6%
	@echo TC %tc%
	del out res
)