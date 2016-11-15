set BUILD=..\..\..\..\build
javac -d %BUILD% -cp %BUILD% *.java
java -cp .;%BUILD% _Test1 | find "FAILED"