
### Parse

The Parse micro-service is accessed from the following endpoint:

	http://localhost:8080/parse

#### Example: Instantiate a parse object for a CSV file

	http://localhost:8080/parse?method=cons&type=csv&arg=C:\tmp\2f.txt
	
	Response: {"id":1,"status":200,"content":""}
	
#### Example: Set the type of reader for the file, open and parse the contents

	http://localhost:8080/parse?method=reader&arg=mem
	
	Response: {"id":2,"status":200,"content":""}
	
	http://localhost:8080/parse?method=open
	
	Response: {"id":3,"status":200,"content":""}
	
	http://localhost:8080/parse?method=parse
	
	Response: {"id":4,"status":200,"content":""}
	
#### Example: Get the number of records imported from the file

	http://localhost:8080/parse?method=nimported
	
	Response: {"id":5,"status":200,"content":"16"}

