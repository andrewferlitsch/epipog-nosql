"# epipog v1.1" 

EpiPog is an “open source” program for developing design models for NoSQL databases. It’s target audience includes both academic 
researchers/students as well as those looking to deploy a NoSQL database for commercial uses.

# This Release

This release is a demonstration of the second phase of creating IP (1st stage), turning IP into Technology (2nd stage), and
Technology into Product (3rd stage).

[Process Methodology](https://github.com/andrewferlitsch/epipog/blob/master/sprints/Methodology.pptx)

# Design

This release is designed using an OOD (Object Oriented Design) using a Factory Design model. The pipeline is partitioned into
components which are dynamically instantiated and inject from an abstract or interface definition (the "factory"). Each
component is additionally supported with a micro-service interface.

[ e2e Design](https://github.com/andrewferlitsch/epipog/blob/master/specs/stages/Data%20Pipeline.pptx)

# Build

Epipog is setup to build using Gradle. If you have not installed gradle, it is recommended
you use version 2.14.1 or later, which can be found at: http://www.gradle.org .

## Development PC

To build with Gradle pre-installed on your PC.

	> gradle build 
	
## Portable Build

To build on a PC without Gradle pre-installed, use the Gradle wrapper.

	> .\gradlew
	
## Local Build

Each package can be built separately, and it's automated unit tests ran, without a build management system, by
executing the build script in each package source directory. The build script will compile all sources in
the package, run the unit tests (_Test<N>), display any failures and report the total number of tests ran.

	> cd src\main\java\<package>	// e.g., Data, Parse, Schema, Collection, etc
	> .\build
	
# Executing

This project has a builtin web server interface using Spring.io RESTful web service. If you are not
familiar with spring.io or using it to build a RESTful web service, a very good tutorial can be found
at: http://spring.io/guides/gs/rest-service/ .

The endpoints on the service act as a micro-service, where the action has persistence across micro-service invocations.

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

Each endpoint can take the following parameters:
	
	method=method-name							# Required
	type=data-type								# Optional
	arg=value-to-pass-as-an-argument-to-method	# Optional

### Data (Model)

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


### Parse

The Parse endpoint can take the following parameters:
	
	method=method-name							# Required
	type=data-type								# Optional
	arg=value-to-pass-as-an-argument-to-method	# Optional

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

