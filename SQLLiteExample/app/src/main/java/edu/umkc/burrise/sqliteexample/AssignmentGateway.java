package edu.umkc.burrise.sqliteexample;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AssignmentGateway {
	private Context context;

	public AssignmentGateway(Context context) {
		this.context = context;
	}
	
	public ArrayList<Assignment> findForCourse(Course c) {
		ArrayList<Assignment> assignments;
        assignments = new ArrayList<Assignment>();

		DatabaseHelper dbHelper = new DatabaseHelper(context);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String[] from = { DatabaseHelper.FIELD_ID,
				DatabaseHelper.FIELD_COURSE,
				DatabaseHelper.FIELD_ASSIGNMENT_NAME};
		Cursor cursor = db.query(DatabaseHelper.ASSIGNMENTS_TABLE,
				from,
				DatabaseHelper.FIELD_COURSE + "=" + c.courseID(),
				null,
				null,
				null,
				null);
		
		while (cursor.moveToNext()) {
			long id = cursor.getLong(0);
			// Note, we are skipping over the foreign key for
			// course.
			String assignment_name = cursor.getString(2);
			assignments.add(new Assignment(assignment_name));
		}

		dbHelper.close();
		return assignments;
	}
	
	public long insert(long courseID, // foreign key
			String assignmentName) {
		// insert record into DB
		DatabaseHelper dbHelper = new DatabaseHelper(context);
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.FIELD_COURSE, courseID);
		values.put(DatabaseHelper.FIELD_ASSIGNMENT_NAME, assignmentName);
		long row_id = db.insertOrThrow(DatabaseHelper.ASSIGNMENTS_TABLE, null, values);

		// Return value from INSERT statement (row_id above)
		//   is _id value of last record inserted.
		// Each entry in most SQLite tables (except for WITHOUT ROWID
		// tables) has a unique 64-bit signed integer key called the
		// "rowid". ... If the table has a column of type INTEGER
		// PRIMARY KEY then that column is another alias for the rowid.

		dbHelper.close();
		return row_id; // return the primary key created by the DB
	}

	// id is primary key for record to update
	public void update(int id, int courseID,
			String assignmentName, int percent,
			int pointsPossible,
			int pointsEarned) {

	}

	public void delete(int id) { // id is primary key for record
		
	}
}
