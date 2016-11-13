javac -d ..\..\..\..\build -cp ..\..\..\..\build *.java
java -cp .;..\..\..\..\build _Test1 | find "FAILED"