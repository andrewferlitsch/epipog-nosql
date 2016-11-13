/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.parse;

// Abstract Layer for reading input data
//
public abstract class Reader {
	public Reader( String inputFile ) {
		this.inputFile = inputFile;
	}
	 
	protected String inputFile = null;		// input file to parse
	protected long   fileSize  = 0;			// size of input file
	 
	// Getter to return the input file path
	public String InputFile() {
		return inputFile;
	}
	
	// Setter to set the file size
	public void FileSize( long fileSize ) {
		this.fileSize = fileSize;
	}
	
	// enum flags for types of derived readers
	public enum ReaderType {
		READERMEM,
		READERLINE,
		READERMAPPED
	}
	 
	// Method to open input file
	//
	public abstract void Open() throws ParseException;
	 
	// Method to close input file
	//
	public abstract void Close();
	 
	// Method to read a line from an input file
	//	One record per line (e.g., CSV)
	// Return: line or null for EOF
	//
	public abstract String ReadLine() throws ParseException;

}