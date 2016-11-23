
### Parse

The Parse micro-service is accessed from the following endpoint:

	http://localhost:8080/parse
	
The below examples use the following example data input file

	country,state,city,pop
	United States,Oregon,Portland,610000
	United States,Oregon,Eugene,160000
	United States,Oregon,Salem,160000
	United States,Washington,Seattle,652000
	United States,Washington,Vancouver,167000
	United States,Washington,Tacoma,203000
	United States,California,Los Angeles,3880000
	United States,California,San Francisco,837000
	United States,California,San Diego,1356000
	United States,California,San Jose,1000000
	Canada,Alberta,Calgary,1100000
	Canada,Alberta,Edmonton,812000
	Canada,British Columbia,Vancouver,603000
	Canada,British Columbia,Victoria,78000
	Canada,Quebec,Montreal,1650000
	Canada,Ontario,Toronto,2615000
	Australia,Queensland,
	
Note: the last line in the data file is in incorrect format (messy data).

#### Example: Instantiate a parse object for a CSV file

	http://localhost:8080/parse?method=cons&type=csv&arg=C:\tmp\data.csv
	
	Response: {"id":1,"status":200,"content":""}
	
#### Example: Set the type of reader for the file, open and parse the contents without error (messy) data handling, and close

	http://localhost:8080/parse?method=reader&arg=mem
	
	Response: {"id":2,"status":200,"content":""}
	
	http://localhost:8080/parse?method=open
	
	Response: {"id":3,"status":200,"content":""}
	
	http://localhost:8080/parse?method=parse
	
	Response: {"id":4,"status":500,"content":"SVParse.Parse: number of columns in row incorrect on line 18: Australia,Queensland,"}

	http://localhost:8080/parse?method=close
	
	Response: {"id":5,"status":200,"content
	
#### Example: Get the number of records imported and ejected from the file

	http://localhost:8080/parse?method=nimported
	
	Response: {"id":6,"status":200,"content":"16"}

	http://localhost:8080/parse?method=nejected
	
	Response: {"id":7,"status":200,"content":"0"}
	
#### Example: Open and (re)parse the contents with error (messy) data handling, and close	

	http://localhost:8080/parse?method=skip&arg=true
	
	Response: {"id":8,"status":200,"content":""}
	
	http://localhost:8080/parse?method=open
	
	Response: {"id":9,"status":200,"content":""}
	
	http://localhost:8080/parse?method=parse
	
	Response: {"id":10,"status":200,"content":""}
	
	http://localhost:8080/parse?method=close
	
	Response: {"id":11,"status":200,"content
	
#### Example: Get the number of records imported and ejected from the file

	http://localhost:8080/parse?method=nimported
	
	Response: {"id":12,"status":200,"content":"16"}

	http://localhost:8080/parse?method=nejected
	
	Response: {"id":13,"status":200,"content":"1"}