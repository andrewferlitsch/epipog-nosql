set BUILD=..\..\..\..\build
javac -d %BUILD% -cp %BUILD%;%BUILD%\classes\main *.java

java -cp .;%BUILD% _Test7 | find "FAILED"