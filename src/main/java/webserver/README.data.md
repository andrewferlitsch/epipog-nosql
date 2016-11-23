### Data (Model)

The Data (Model) micro-service is accessed from the following endpoint:

	http://localhost:8080/data

#### Example: Instantiate a data object of type short, and set its value to 6

	http://localhost:8080/data?type=short&method=set&arg=6
	
	Response: {"id":2,"status":200,"content":""}
	
#### Example: Get the value of the existing data object

	http://localhost:8080/data?method=get
	
	Response: {"id":3,"status":200,"content":"6"}
	
#### Example: Parse (invalid) input for data object

	http://localhost:8080/data?method=parse&arg=0x2
	
	Response: {"id":4,"status":500,"content":"DataShort.Parse: invalid input: 0x2"}
	
### Example: Compare existing data value to another value

	http://localhost:8080/data?method=eq&arg=6
	
	Response: {"id":5,"status":200,"content":"true"}

	http://localhost:8080/data?method=ne&arg=6
	
	Response: {"id":6,"status":200,"content":"false"}
	
### Data State (Model)

The Data State (Model), which is an abstract class extended from the Data (Model) is accessed
by adding /state to the endpoint path:

	http://localhost:8080/data/state
	
#### Example: Instantiate a data state object of type short, and set its value to 6

	http://localhost:8080/data/state?type=short&method=set&arg=6
	
	Response: {"id":1,"status":200,"content":""}
	
#### Example: Get the value of the existing data object

	http://localhost:8080/data/state?method=get
	
	Response: {"id":2,"status":200,"content":"6"}
	
#### Example: Parse (valid) input for data object

	http://localhost:8080/data/state?method=parse&arg=0x2
	
	Response: {"id":3,"status":200,"content":""}
