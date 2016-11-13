"# epipog v1.1" 

EpiPog is an “open source” program for developing design models for NoSQL databases. It’s target audience includes both academic 
researchers/students as well as those looking to deploy a NoSQL database for commercial uses.

# This Release

This release is a demonstration of the second phase of creating IP (1st stage), turning IP into Technology (2nd stage), and
Technology into Product.

# Build

Epipog is setup to build using Gradle. If you have not installed gradle, it is recommended
you use version 2.14.1 or later, which can be found at: http://www.gradle.org .

## Local

	> gradle build 
	
## Portable

	> .\gradlew
	
# Executing

This project has a builtin web server interface using Spring.io RESTful web service. If you are not
familiar with spring.io or using it to build a RESTful web service, a very good tutorial can be found
at: http://spring.io/guides/gs/rest-service/ .

## Starting the web server

	> java -jar build\libs\epipog-v1.1.jar
	
## Making REST api calls to the packages

Each package has its own URL endpoint:

	http://localhost:8080/data			# Data (Model)
	http://localhost:8080/parse			# Parse
	http://localhost:8080/schema		# Schema
	
Each API takes a set of paramters (explained below) and returns a JSON response object in
the form:

	[ "id": number, "status": code, "content": string ]
	
Each invocation will set a unique ID number, incrementing as an ordinal starting at 1. The
status field is the HTTP status code. Either 200 will be returned (OK), or 500 indicating an
error occurred.

The content field holds a JSON object as the response, when the status code is 200, or an
error string when the status code is 500.

### Data (Model)

The Data (Model) endpoint can take the following parameters:
	
	method=method-name							# Required
	type=data-type								# Optional
	arg=value-to-pass-as-an-argument-to-method	# Optional

#### Example: Instantiate a data object of type short, and set its value to 6

	http://localhost:8080/data?type=short&method=set&arg=6
	
	Response: {"id":2,"status":200,"content":""}
	
#### Example: Get the value of the existing data object

	http://localhost:8080/data?method=get
	
	Response: {"id":3,"status":200,"content":"6"}
	
#### Example: Parse (invalid) input for data object

	http://localhost:8080/data?method=parse&arg=0x2
	
	Response: {"id":4,"status":500,"content":"DataShort.Parse: invalid input: 0x2"}
	
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



	

