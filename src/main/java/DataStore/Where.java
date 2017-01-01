/*
 * Epipog, Copyright(c) 2016-17, Andrew Ferlitsch, CC-BY
 */
package epipog.datastore;

import epipog.data.Data;

// Class for Where Clause
public class Where {
	public String  key;			// key to check
	public Data    value;		// value to compare to
	public WhereOp op;			// comparison operation

	public enum WhereOp {
		EQ,
		NE,
		LT,
		GT,
		LE,
		GE,
		IN
	}
}