
### Schema

The Schema micro-service is accessed from the following endpoint:

	http://localhost:8080/schema
	

#### Example: Create a table-based schema

	http://localhost:8080/schema?method=cons&type=table
	
	Response: {"id":1,"status":200,"content":""}
	
#### Example: Set the field names of a table-based schema, of default type variable string

	http://localhost:8080/schema?method=set&arg=field1,field2
	
	Response: {"id":2,"status":200,"content":""} 
	
#### Example: Get the number of columns and then column names in a schema 

	http://localhost:8080/schema?method=ncols

	Response: {"id":3,"status":200,"content":"2"} 

	http://localhost:8080/schema?method=columns

	Response: {"id":4,"status":200,"content":"[field1,field2]"}
	
IN PROGRESS

