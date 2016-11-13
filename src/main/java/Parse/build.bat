javac -d ..\..\..\..\build -cp ..\..\..\..\build *.java
java -cp .;..\..\..\..\build _Test2 | find "FAILED"
java -cp .;..\..\..\..\build _Test3 | find "FAILED"
java -cp .;..\..\..\..\build _Test4 | find "FAILED"