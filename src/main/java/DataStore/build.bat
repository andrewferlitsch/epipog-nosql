set BUILD=..\..\..\..\build
javac -d %BUILD% -cp %BUILD%;%BUILD%\classes\main *.java

IF %ERRORLEVEL% NEQ 1 (  
	java -cp .;%BUILD% _Test10 >out
	type out | find "FAILED"
	type out | find "Test:" /c >res
	@set /p t10= <res
	@echo %t10%
	
	java -cp .;%BUILD% _Test11 >out
	type out | find "FAILED"
	type out | find "Test:" /c >res
	@set /p t11= <res
	@echo %t11%
	
	java -cp .;%BUILD% _Test12 >out
	type out | find "FAILED"
	type out | find "Test:" /c >res
	@set /p t12= <res
	@echo %t12%
	
	@set /a tc=%t10% + %t11% + %t12%
	@echo TC %tc%
	del out res
)
