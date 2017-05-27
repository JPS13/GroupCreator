package data;

import java.sql.*;
import java.util.*;
import model.*;

/**
 * The GroupDAO class is a data access class to persist
 * Group objects to an SQLite database.
 * 
 * @author Joseph Stewart
 */
public class GroupDAO implements BaseDAO {
	
	/** Provides database connection. */
	private Connection connection;
	
	/**
	 * Constructor sets the connection object.
	 */
	public GroupDAO() {
		connection = Database.getInstance().getConnection();
	}

	/**
	 * This method adds a Group object to the database and returns
	 * the same object with the id set.
	 * 
	 * @param model The Group to be added.
	 * @return The same Group with the assigned id.
	 */
	@Override
	public Model add(Model model) {
		Group group = (Group) model;
		
		String query = "INSERT INTO " + Database.GROUP_TABLE + " (" + 
				Database.CLASSROOM_ID + ", " + Database.GROUP_NUMBER + 
				", " + Database.IS_FRONT_GROUP + ", "
				+ Database.DATE_CREATED + ") VALUES (?, ?, ?, ?)";
		
		String idQuery = "SELECT last_insert_rowid()";
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, group.getClassroomId());
			preparedStatement.setInt(2, group.getGroupNumber());
			preparedStatement.setBoolean(3, group.getIsFrontGroup());
			preparedStatement.setString(4, group.getDateCreated());
			preparedStatement.execute();
									
			Statement statement = connection.createStatement();			
			ResultSet resultSet = statement.executeQuery(idQuery);
			
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				group.setId(id);
			}
			
			statement.close();	
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return group;
	}

	/**
	 * This method updates a Group record already saved in the database.
	 * 
	 * @param model The Group to be updated.
	 * @return True if the update is successful; otherwise false.
	 */
	@Override
	public boolean update(Model model) {
		Group group = (Group) model;
		boolean updateSuccessful = false;
		
		String query = "UPDATE " + Database.GROUP_TABLE + " SET " + 				
				Database.CLASSROOM_ID + " = ?, " + Database.GROUP_NUMBER + 
				" = ?, " + Database.IS_FRONT_GROUP + " = ?, " + 
				Database.DATE_CREATED + " = ? WHERE " + Database.GROUP_ID + " = ?";
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {
								
			preparedStatement.setInt(1, group.getClassroomId());
			preparedStatement.setInt(2, group.getGroupNumber());
			preparedStatement.setBoolean(3, group.getIsFrontGroup());
			preparedStatement.setString(4, group.getDateCreated());
			preparedStatement.setInt(5, group.getId());		
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			updateSuccessful = true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return updateSuccessful;
	}

	/**
	 * This method deletes a Group record from the database.
	 * 
	 * @param model The Group to be deleted.
	 * @return True if the record is deleted; otherwise false.
	 */
	@Override
	public boolean delete(Model model) {
		Group group = (Group) model;
		boolean deleted = false;
		
		String query = "DELETE FROM " + Database.GROUP_TABLE + " WHERE " + 
				Database.GROUP_ID + " = ?";
			
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {	
						
			preparedStatement.setInt(1, group.getId());
			preparedStatement.execute();
			
			deleted = true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return deleted;
	}

	/**
	 * This method retrieves all Groups from the database and
	 * returns the Collection.
	 * 
	 * @return The Collection of Groups.
	 */
	@Override
	public Collection<Model> getAll() {
		Collection<Model> groups = new ArrayList<>();
		
		String query = "SELECT * FROM " + Database.GROUP_TABLE; 
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {			
					
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

	/**
	 * This method assigns a Student to a Group.
	 * 
	 * @param model The Group.
	 * @param student The Student to be assigned.
	 */
	@Override
	public void assignStudent(Model model, Student student) {
		Group group = (Group) model;
		
		String query = "INSERT OR IGNORE INTO " + Database.GROUP_ASSIGNMENT_TABLE +
				" (" + Database.GROUP_ID + ", " + Database.STUDENT_ID + 
				") VALUES (?, ?)";
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {			
			
			preparedStatement.setInt(1, group.getId());
			preparedStatement.setInt(2, student.getId());
			preparedStatement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * This method removes a Student from a Group.
	 * 
	 * @param model The Group.
	 * @param student The student to be removed.
	 */
	@Override
	public void unassignStudent(Model model, Student student) {
		Group group = (Group) model;
		
		String query = "DELETE FROM " + Database.GROUP_ASSIGNMENT_TABLE + 
				" WHERE " + Database.GROUP_ID + " = ? AND " + 
				Database.STUDENT_ID + " = ?";
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {		
						
			preparedStatement.setInt(1, group.getId());
			preparedStatement.setInt(2, student.getId());
			preparedStatement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * This method retrieves all Students assigned to the passed
	 * in Group and returns them in a Collection.
	 * 
	 * @param model The Group with assigned Students.
	 * @return The Collection of Students.
	 */
	@Override
	public Collection<Student> getStudents(Model model) {
		Group group = (Group) model;
		Collection<Student> students = new ArrayList<>();
 		
 		String query = "SELECT * FROM " + Database.STUDENT_TABLE +
 				" INNER JOIN " + Database.GROUP_ASSIGNMENT_TABLE +
 				" WHERE " + Database.STUDENT_TABLE + "." + Database.STUDENT_ID + 
 				" = " + Database.GROUP_ASSIGNMENT_TABLE + "." + Database.STUDENT_ID +
 				" AND " + Database.GROUP_ASSIGNMENT_TABLE + "." + Database.GROUP_ID + " = ?";
 		
 		try(PreparedStatement preparedStatement = 
					connection.prepareStatement(query)) { 			
 			
 			preparedStatement.setInt(1, group.getId());
 			
 			ResultSet resultSet = preparedStatement.executeQuery();  
 			
 			while(resultSet.next()) {  				
 				Student student = new Student();				
				student.setId(resultSet.getInt(Database.STUDENT_ID));
				student.setName(resultSet.getString(Database.NAME));
				student.setGender(Gender.valueOf(resultSet.getString(Database.GENDER).toUpperCase())); 
				student.setAbilityLevel(AbilityLevel.valueOf(resultSet.getString(Database.ABILITY_LEVEL).toUpperCase())); 
				student.setFrontSeatNeeded(resultSet.getBoolean(Database.FRONT_SEAT_NEEDED));
				student.setPreferredGroupOfFive(resultSet.getBoolean(Database.PREFERRED_GROUP_OF_FIVE));				
				students.add(student);				
 			} 			 	
 		} catch(SQLException e) {
 			e.printStackTrace();
 		} 		
 		
 		return students;
	}

}
