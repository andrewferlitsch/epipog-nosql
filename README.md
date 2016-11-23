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

The Data (Model) package handles the representation of data types within the data pipeline. This package can be imported
as a Java library with the following import statement:

	import epipog.data.*;
	
[Specification](https://github.com/andrewferlitsch/epipog/blob/master/specs/modules/Data.docx)
	
When using the epipog web server appication, it is accessed as a micro-service.

[Micro-Service](https://github.com/andrewferlitsch/epipog/blob/master/src/main/java/webserver/README.data.md)


### Parse

The Parse package handles parsing of input data sets. This package can be imported as a Java library with the following
import statement:

	import epipog.parse.*;
	
[Specification - Parse](https://github.com/andrewferlitsch/epipog/blob/master/specs/modules/Parse.docx)
	
[Specification - Reader](https://github.com/andrewferlitsch/epipog/blob/master/specs/modules/Reader.docx)
	
[Specification - Ejector](https://github.com/andrewferlitsch/epipog/blob/master/specs/modules/Ejector.docx)
	
When using the epipog web server appication, it is accessed as a micro-service.

[Micro-Service](https://github.com/andrewferlitsch/epipog/blob/master/src/main/java/webserver/README.parse.md)