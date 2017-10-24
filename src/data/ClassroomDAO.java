package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import model.*;

/**
 * The ClassroomDAO class is a data access class for persisting
 * Classroom objects to an SQLite database. 
 * 
 * @author Joseph Stewart
 */
public class ClassroomDAO implements BaseDAO {
	
	/** Provides database connection. */
	private Connection connection;
	
	/**
	 * Constructor sets the connection object.
	 */
	public ClassroomDAO() {
		connection = Database.getInstance().getConnection();
	}

	/**
	 * This method adds a Classroom object to the database and 
	 * returns the object with the auto generated id assigned.
	 * 
	 * @param The Classroom object to be added.
	 * @return The same Classroom with the assigned id.
	 */
	@Override
	public Model add(Model model) {
		Classroom classroom = (Classroom) model;
		
		String query = "INSERT INTO " + Database.CLASSROOM_TABLE + 
				" (" + Database.CLASSROOM_TITLE + ", " +
				Database.MAXIMUM_FRONT_GROUPS + ")" + 
				"VALUES (?, ?)";
		
		String idQuery = "SELECT last_insert_rowid()";    
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {	
			
			preparedStatement.setString(1, classroom.getTitle());			
			preparedStatement.setInt(2, classroom.getMaximumFrontGroups());		
			preparedStatement.execute();
								
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(idQuery);
			
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				classroom.setId(id);								
			}
			
			statement.close();			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return classroom;
	}

	/**
	 * This method updates a Classroom object in the database.
	 * 
	 * @param model The Classroom object to be updated.
	 * @return True if the Classroom is successfully updated; otherwise false.
	 */
	@Override
	public boolean update(Model model) {
		Classroom classroom = (Classroom) model;
		boolean updateSuccessful = false;
		
		String query = "UPDATE " + Database.CLASSROOM_TABLE + 
				" SET " + Database.CLASSROOM_TITLE + " = ?, " + 
				Database.MAXIMUM_FRONT_GROUPS + " = ? " + 
				"WHERE " + Database.CLASSROOM_ID + " = ?";
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {	
			
			preparedStatement.setString(1, classroom.getTitle());		
			preparedStatement.setInt(2, classroom.getMaximumFrontGroups());
			preparedStatement.setInt(3, classroom.getId());		
			preparedStatement.executeUpdate();
						
			updateSuccessful = true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return updateSuccessful;
	}

	/**
	 * This method deletes a Classroom object from the database.
	 * 
	 * @param model The Classroom to be deleted.
	 * @return True if successfully deleted; otherwise false.
	 */
	@Override
	public boolean delete(Model model) {
		Classroom classroom = (Classroom) model;
		
		boolean deleted = false;
		
		String query = "DELETE FROM " + Database.CLASSROOM_TABLE + 
					   " WHERE " + Database.CLASSROOM_ID + " = ?";
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {	
						
			preparedStatement.setInt(1, classroom.getId());
			preparedStatement.execute();
							
			deleted = true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return deleted;
	}

	/**
	 * This method retrieves all of the Classrooms stored in the 
	 * database and returns them in a Collection. The aggregated
	 * collections stored in a Classroom are not retrieved at
	 * this time; they are loaded when the desired Classroom is 
	 * selected by the user. This prevents resources being wasted
	 * on loading data for a Classroom that may never be selected.
	 * 
	 * @return The Collection of Classrooms.
	 */
	@Override
	public Collection<Model> getAll() {
		Collection<Model> classrooms = new ArrayList<>();
		
		String query = "SELECT * FROM " + Database.CLASSROOM_TABLE;		
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {
									
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {				
				Classroom classroom = new Classroom();				
				classroom.setId(resultSet.getInt(Database.CLASSROOM_ID));
				classroom.setTitle(resultSet.getString(Database.CLASSROOM_TITLE)); 
				classroom.setMaximumFrontGroups(resultSet.getInt(Database.MAXIMUM_FRONT_GROUPS));				
				classrooms.add(classroom);			
			}			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return classrooms;
	}

	/**
	 * This method enrolls a student in the classroom. 
	 * 
	 * @param model The classroom.
	 * @param student The student to be enrolled.
	 */
	@Override
	public void assignStudent(Model model, Student student) {
		Classroom classroom = (Classroom) model;
		
		String query = "INSERT INTO enrollment (" + Database.CLASSROOM_ID + 
				", " + Database.STUDENT_ID + ")" + "VALUES (?, ?)";
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {
						
			preparedStatement.setInt(1, classroom.getId());
			preparedStatement.setInt(2, student.getId());
			preparedStatement.execute();		
		} catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * This method removes a student from the classroom. This 
	 * does not delete the student record; it may be enrolled
	 * again or used in a different classroom.
	 * 
	 * @param model The classroom.
	 * @param student The student to be removes.
	 */
	@Override
	public void unassignStudent(Model model, Student student) {
		Classroom classroom = (Classroom) model;
		
		String query = "DELETE FROM " + Database.ENROLLMENT_TABLE + 
				" WHERE " + Database.CLASSROOM_ID + " = ? " +
				" AND " + Database.STUDENT_ID + " = ?";
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {	
			
			preparedStatement.setInt(1, classroom.getId());
			preparedStatement.setInt(2, student.getId());
			preparedStatement.execute();		
		} catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * This method retrieves all of the students enrolled in the 
	 * Classroom and returns them as a collection.
	 * 
	 * @param model The Classroom with enrolled Students.
	 * @return The Collection of Students.
	 */
	@Override
	public Collection<Student> getStudents(Model model) {
		Classroom classroom = (Classroom) model;		
		Collection<Student> students = new ArrayList<>();
		
 		String query = "SELECT * FROM " + Database.STUDENT_TABLE + 
 				" INNER JOIN " + Database.ENROLLMENT_TABLE + " WHERE " + 
 				Database.STUDENT_TABLE + "." + Database.STUDENT_ID + 
 				" = " + Database.ENROLLMENT_TABLE + "." + Database.STUDENT_ID
 				+ " AND " + Database.ENROLLMENT_TABLE + "." + Database.CLASSROOM_ID + " = ?";
 				
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, classroom.getId());
			
			ResultSet resultSet = preparedStatement.executeQuery();
					
			while(resultSet.next()) {				
				Student student = new Student();				
				student.setId(resultSet.getInt(Database.STUDENT_ID));
				student.setName(resultSet.getString(Database.NAME)); 
				student.setGender(Gender.valueOf(resultSet.getString(Database.GENDER).toUpperCase())); 
				student.setAbilityLevel(AbilityLevel.valueOf(resultSet.getString(Database.ABILITY_LEVEL).toUpperCase())); 
				
				StudentDAO sdao = new StudentDAO();				
				student.setAccommodations(sdao.getAccommodations(student));
				student.setStudents(sdao.getStudents(student));
				students.add(student);			
			}			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return students;
	}

	/**
	 * This method retrieves all of the Groups created for the
	 * specified Classroom and returns them as a Collection.
	 * 
	 * @param classroom The Classroom with the Groups.
	 * @return The Collection of Groups.
	 */
	public Collection<Group> getGroups(Classroom classroom) {		
		Collection<Group> groups = new ArrayList<>();
		
 		String query = "SELECT * FROM " + Database.GROUP_TABLE + 
 				" WHERE " + Database.CLASSROOM_ID + " = ?";
 				
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, classroom.getId());
			
			ResultSet resultSet = preparedStatement.executeQuery();
					
			while(resultSet.next()) {	
				Group group = new Group();
				group.setId(resultSet.getInt(Database.GROUP_ID));
				group.setClassroomId(resultSet.getInt(Database.CLASSROOM_ID));
				group.setGroupNumber(resultSet.getInt(Database.GROUP_NUMBER));
				group.setIsFrontGroup(resultSet.getBoolean(Database.IS_FRONT_GROUP));
				group.setDateCreated(resultSet.getString(Database.DATE_CREATED));				
				groups.add(group);			
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return groups;		
	}
}
