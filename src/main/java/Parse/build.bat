set BUILD=..\..\..\..\build
javac -d %BUILD% -cp %BUILD%;%BUILD%\classes\main *.java
java -cp .;%BUILD% _Test2 | find "FAILED"
java -cp .;%BUILD% _Test3 | find "FAILED"
java -cp .;%BUILD% _Test4 | find "FAILED"