javac -d ..\..\..\..\build -cp ..\..\..\..\build *.java

java -cp .;..\..\..\..\build _Test5 | find "FAILED"
java -cp .;..\..\..\..\build _Test6 | find "FAILED"