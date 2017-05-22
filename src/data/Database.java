package data;

import java.sql.*;
import java.util.*;

/**
 * The Database class creates the database and tables upon construction if
 * they do not already exist. Table and column names are provided for consistency
 * and a database connection is provided.
 * 
 * This class implements the singleton pattern so only one instance is ever
 * created. The class is marked final so it cannot be sub-classed. The getInstance
 * method is synchronized to make it thread-safe.
 * 
 * @author Joseph Stewart
 */
public final class Database {
	
	/** Provides an instance of this database. */
	private static Database instance = null;
	
	/** Connection object provides a connection to the database. */
	private Connection connection;		
	
	/** Classroom table and columns. */
	public final static String CLASSROOM_TABLE = "classrooms";
	public final static String CLASSROOM_ID = "classroomId";
	public final static String CLASSROOM_TITLE = "title";
	public final static String MAXIMUM_FRONT_GROUPS = "maximumFrontGroups";
	
	/** Student table and columns. */	
	public final static String STUDENT_TABLE = "students";
	public final static String STUDENT_ID = "studentId";
	public final static String NAME = "name";
	public final static String GENDER = "gender";
	public final static String ABILITY_LEVEL = "abilityLevel";
	public final static String FRONT_SEAT_NEEDED = "frontSeatNeeded";
	public final static String PREFERRED_GROUP_OF_FIVE = "preferredGroupOfFive";
	
	/** Group table and columns */	
	public final static String GROUP_TABLE = "groups";
	public final static String GROUP_ID = "groupId";
	public final static String GROUP_NUMBER = "groupNumber";
	public final static String DATE_CREATED = "dateCreated";
	public final static String IS_FRONT_GROUP = "isFrontGroup";
	
	/** Enrollment table */	
	public final static String ENROLLMENT_TABLE = "enrollment";
	
	/** Incompatible students table */	
	public final static String INCOMPATIBLE_STUDENT_TABLE = "incompatibleStudents";
	public final static String STUDENT_1_ID = "student1Id";
	public final static String STUDENT_2_ID = "student2Id";
	
	/** Group assignments table */
	public final static String GROUP_ASSIGNMENT_TABLE = "groupAssignments";
		
	/**
	 * Database Constructor creates database and tables if they don't
	 * already exist and sets the Connection object. It is private to
	 * prevent instantiation from outside of this class. 
	 */
	private Database() {						
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:classroom.db");			
			createDatabaseTables();
		} catch (Exception e) {
			e.printStackTrace();
		} 		
	}

	/**
	 * This method returns the single instance of the database.
	 * The method is synchronized to prevent multiple instances
	 * from being created in different threads.
	 * 
	 * @return The Database instance.
	 */
	public synchronized static Database getInstance() {
		if(instance == null) {
			instance = new Database();
		}
		return instance;
	}
	
	/**
	 * Provides access to the database connection.
	 * 
	 * @return The database Connection.
	 */
	public Connection getConnection() {
		return connection;
	}
	
	/**
	 * Declares statements to create the database tables if they do
	 * not already exist.
	 * 
	 * @throws SQLException Thrown if there is a problem executing the
	 * 						create statements.
	 */
	private void createDatabaseTables() throws SQLException {
		String createClassrooms = "CREATE TABLE IF NOT EXISTS " + CLASSROOM_TABLE + " (" +
				  CLASSROOM_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
				  CLASSROOM_TITLE + " TEXT, " +
				  MAXIMUM_FRONT_GROUPS + " INTEGER);";

		String createStudents = "CREATE TABLE IF NOT EXISTS " + STUDENT_TABLE + " (" +
						STUDENT_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
						NAME + " TEXT NOT NULL, " +
						GENDER + " TEXT, " +
						ABILITY_LEVEL + " TEXT, " +
						FRONT_SEAT_NEEDED + " INTEGER, " +
						PREFERRED_GROUP_OF_FIVE + " INTEGER);";
				
		String createGroups = "CREATE TABLE IF NOT EXISTS " + GROUP_TABLE + " (" +
					  GROUP_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
					  CLASSROOM_ID + " INTEGER, " +
					  GROUP_NUMBER + " INTEGER, " +
					  IS_FRONT_GROUP + " INTEGER, " +
					  DATE_CREATED + " TEXT);";
		
		String createEnrollment = "CREATE TABLE IF NOT EXISTS " + ENROLLMENT_TABLE + " (" +
						  CLASSROOM_ID + " INTEGER NOT NULL, " +
						  STUDENT_ID + " INTEGER NOT NULL, " +
						  "PRIMARY KEY(" + CLASSROOM_ID + ", " + STUDENT_ID + "));";
		
		String createIncompatibleStudents = "CREATE TABLE IF NOT EXISTS " + INCOMPATIBLE_STUDENT_TABLE + " (" +
			   						STUDENT_1_ID + " INTEGER NOT NULL, " +
			   						STUDENT_2_ID + " INTEGER NOT NULL, " +
			   						"PRIMARY KEY(" + STUDENT_1_ID + ", " + STUDENT_2_ID + "));";
		
		String createGroupAssignments = "CREATE TABLE IF NOT EXISTS groupAssignments (" +
		 						GROUP_ID + " INTEGER NOT NULL, " +
		 						STUDENT_ID + " INTEGER NOT NULL, " +
		 						"PRIMARY KEY(" + GROUP_ID + ", " + STUDENT_ID + "));";
		
		List<String> createStrings = new ArrayList<>();
		createStrings.add(createClassrooms);
		createStrings.add(createStudents);
		createStrings.add(createGroups);
		createStrings.add(createEnrollment);
		createStrings.add(createIncompatibleStudents);
		createStrings.add(createGroupAssignments);
		
		Statement statement = connection.createStatement();
		for(String s: createStrings) 
			statement.executeUpdate(s);
	}
}
