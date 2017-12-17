package data;

import java.sql.*;
import java.util.*;

import model.*;

/**
 * The StudentDAO class is a data access class for
 * persisting Student objects to an SQLit database.
 * 
 * @author Joseph Stewart
 */
public class StudentDAO implements BaseDAO {
	
	/** Provides database connection. */
	private Connection connection;
	
	/**
	 * Constructor sets the connection object.
	 */
	public StudentDAO() {
		connection = Database.getInstance().getConnection();
	}

	/**
	 * This method adds a Student object to the database and
	 * returns the same object with the id assigned.
	 * 
	 * @param model The Student to be added.
	 * @return The Student with the id assigned.
	 */
	@Override
	public Model add(Model model) {
		Student student = (Student) model;
		
		String query = "INSERT INTO " + Database.STUDENT_TABLE + " (" + Database.NAME + 
				", " + Database.GENDER + ", " + Database.ABILITY_LEVEL +  ")" + " VALUES (?, ?, ?)";
		
		String idQuery = "SELECT last_insert_rowid()";
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {
						
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getGender().toString());
			preparedStatement.setString(3, student.getAbilityLevel().toString());
			preparedStatement.execute();
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(idQuery);
			
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				student.setId(id); 
			}
			
			statement.close();	
		} catch(SQLException e) {
			e.printStackTrace();
		}			
		return student;
	}

	/**
	 * Adds an accommodation to the table.
	 * 
	 * @param accommodation The accommodation to be added.
	 */
	public void addAccommodation(String accommodation) {
		String query = "INSERT INTO " + Database.ACCOMMODATIONS_TABLE + 
				" (" + Database.NAME + ")" + " VALUES (?)";
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {
						
			preparedStatement.setString(1, accommodation);
			preparedStatement.execute();	
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * This method updates a Student record already stored in
	 * the database. 
	 * 
	 * @param model The Student to be updated.
	 * @return True if the update was successful; otherwise false.
	 */
	@Override
	public boolean update(Model model) {
		Student student = (Student) model;	
		
		String query = "UPDATE " + Database.STUDENT_TABLE + " SET " + 
				Database.NAME + " = ?, " + Database.GENDER + " = ?, " + 
				Database.ABILITY_LEVEL + " = ? WHERE " + 
				Database.STUDENT_ID + " = ?";
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {
					
			preparedStatement.setString(1, student.getName());	
			preparedStatement.setString(2, student.getGender().toString());
			preparedStatement.setString(3, student.getAbilityLevel().toString());
			preparedStatement.setInt(4, student.getId());		
			preparedStatement.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Updates the value of the accommodation.
	 * 
	 * @param oldAccommodation The old value.
	 * @param newAccommodation The new value.
	 * @return True if updated successfully; false otherwise.
	 */
	public boolean updateAccommodation(String oldAccommodation, String newAccommodation) {
		String query = "UPDATE " + Database.ACCOMMODATIONS_TABLE + " SET " + 
				Database.ACCOMMODATION + " = ? WHERE " + 
				Database.ACCOMMODATION + " = ?";
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {
					
			preparedStatement.setString(1, newAccommodation);	
			preparedStatement.setString(2, oldAccommodation);	
			preparedStatement.executeUpdate();			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}

	/**
	 * This method deletes a Student record from the database.
	 * 
	 * @param model The Student to be deleted.
	 * @return True if the delete was successful; otherwise false.
	 */
	@Override
	public boolean delete(Model model) {
		Student student = (Student) model;
		
		String query = "DELETE FROM " + Database.STUDENT_TABLE + 
				" WHERE " + Database.STUDENT_ID + " = ?";
			
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {	
						
			preparedStatement.setInt(1, student.getId());
			preparedStatement.execute();			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	/**
	 * Deletes an accommodation entry from the table.
	 * 
	 * @param accommodation The value to be deleted.
	 * @return True if deleted successfully; false otherwise.
	 */
	public boolean deleteAccommodation(String accommodation) {
		String query = "DELETE FROM " + Database.ACCOMMODATIONS_TABLE + 
				" WHERE " + Database.ACCOMMODATION + " = ?";
			
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {	
						
			preparedStatement.setString(1, accommodation);
			preparedStatement.execute();			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}

	/**
	 * Obtains the relevant accommodations for this student.
	 * 
	 * @param student The student whose accommodations are needed.
	 * @return The map of accommodations.
	 */
	public Map<String, Boolean> getAccommodations(Student student) {
		Map<String, Boolean> accommodations = new HashMap<>();
		
		String accommodationQuery = "SELECT * FROM " + Database.ACCOMMODATIONS_TABLE;		
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(accommodationQuery)) {
						
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {						
				accommodations.put(resultSet.getString(Database.ACCOMMODATION), false);		
			}	 
		} catch(SQLException e) {
			e.printStackTrace();
		}		
 		
 		String studentQuery = "SELECT " + Database.ACCOMMODATION + " FROM " + 
 				Database.STUDENT_ACCOMMODATIONS_TABLE +
 				" WHERE " + Database.STUDENT_ID + " = ?";
 		
 		try(PreparedStatement preparedStatement = 
					connection.prepareStatement(studentQuery)) {
 			 			
 			preparedStatement.setInt(1, student.getId()); 			
 			ResultSet resultSet = preparedStatement.executeQuery();
 			
 			while(resultSet.next()) {  	 				
 				accommodations.put(resultSet.getString(Database.ACCOMMODATION), true);				
 			} 			
 		} catch(SQLException e) {
 			e.printStackTrace();
 		} 				
		return accommodations;
	}
	
	/**
	 * This method retrieves all of the Students stored in the Database.
	 * 
	 *@return The collection of Students. 
	 */
	@Override
	public Collection<Model> getAll() {
		Collection<Model> students = new ArrayList<>();
		
 		String query = "SELECT * FROM " + Database.STUDENT_TABLE;
 		 				
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {
						
			ResultSet resultSet = preparedStatement.executeQuery();
					
			while(resultSet.next()) {				
				Student student = new Student();				
				student.setId(resultSet.getInt(Database.STUDENT_ID));
				student.setName(resultSet.getString(Database.NAME)); 
				student.setGender(Gender.valueOf(resultSet.getString(Database.GENDER).toUpperCase())); 
				student.setAbilityLevel(AbilityLevel.valueOf(resultSet.getString(Database.ABILITY_LEVEL).toUpperCase())); 	
				students.add(student);			
			}	 
		} catch(SQLException e) {
			e.printStackTrace();
		}
 		
 		return students;
	}

	/**
	 * Assigns an accommodation to the student.
	 * 
	 * @param student The student requiring an accommodation.
	 * @param accommodation The accommodation required.
	 */
	public void assignAccommodation(Student student, String accommodation) {
		String query = "INSERT OR IGNORE INTO " + Database.STUDENT_ACCOMMODATIONS_TABLE +
				" (" + Database.STUDENT_ID + ", " + Database.ACCOMMODATION + 
				") VALUES (?, ?)";
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {
						
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, accommodation);
			preparedStatement.execute();	
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Remove an accommodation for a student.
	 * 
	 * @param student The student whose accommodation is removed.
	 * @param accommodation The accommodation to be removed.
	 */
	public void unassignAccommodation(Student student, String accommodation) {
		String query = "DELETE FROM " + Database.STUDENT_ACCOMMODATIONS_TABLE + " WHERE " +
				Database.STUDENT_ID + " = ? AND " + Database.ACCOMMODATION + " = ?";
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {	
						
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, accommodation);
			preparedStatement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * This method assigns incompatible students to each other.
	 * 
	 * @param model The first Student.
	 * @param student The second Student.
	 */
	@Override
	public void assignStudent(Model model, Student student) {
		Student student1 = (Student) model;
		
		String query = "INSERT OR IGNORE INTO " + Database.INCOMPATIBLE_STUDENT_TABLE +
				" (" + Database.STUDENT_1_ID + ", " + Database.STUDENT_2_ID + 
				") VALUES (?, ?)";
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {
						
			preparedStatement.setInt(1, student1.getId());
			preparedStatement.setInt(2, student.getId());
			preparedStatement.execute();
			
			// Add second entry with id's swapped
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setInt(2, student1.getId());
			preparedStatement.execute();	
		} catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * This method removes assigned students from each other.
	 * 
	 * @param model The first Student.
	 * @param student The second Student.
	 */
	@Override
	public void unassignStudent(Model model, Student student) {
		Student student1 = (Student) model;
		
		String query = "DELETE FROM " + Database.INCOMPATIBLE_STUDENT_TABLE + " WHERE " +
				Database.STUDENT_1_ID + " = ? AND " + Database.STUDENT_2_ID + " = ?";
		
		try(PreparedStatement preparedStatement = 
				connection.prepareStatement(query)) {	
						
			preparedStatement.setInt(1, student1.getId());
			preparedStatement.setInt(2, student.getId());
			preparedStatement.execute();
			
			// Delete second entry with id's swapped 
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setInt(2, student1.getId());			
			preparedStatement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * This method retrieves all of the student assigned to the passed
	 * in Student and returns them as a collection.
	 * 
	 * @param model The Student with the assigned Students.
	 * @return The Collection of assigned Student.
	 */
	@Override
	public Collection<Student> getStudents(Model model) {
		Student student = (Student) model;
		Collection<Student> incompatibleStudents = new ArrayList<>();
 		
 		String query = "SELECT * FROM " + Database.STUDENT_TABLE +
 				" INNER JOIN " + Database.INCOMPATIBLE_STUDENT_TABLE +
 				" WHERE " + Database.STUDENT_TABLE + "." + Database.STUDENT_ID + 
 				" = " + Database.INCOMPATIBLE_STUDENT_TABLE + "." + Database.STUDENT_2_ID +
 				" AND " + Database.INCOMPATIBLE_STUDENT_TABLE + "." + Database.STUDENT_1_ID + " = ?";
 		
 		try(PreparedStatement preparedStatement = 
					connection.prepareStatement(query)) {
 			 			
 			preparedStatement.setInt(1, student.getId());
 			
 			ResultSet resultSet = preparedStatement.executeQuery();
 			
 			while(resultSet.next()) {  				
 				Student s = new Student();				
				s.setId(resultSet.getInt(Database.STUDENT_ID));
				s.setName(resultSet.getString(Database.NAME)); 			
				s.setGender(Gender.valueOf(resultSet.getString(Database.GENDER).toUpperCase())); 
				s.setAbilityLevel(AbilityLevel.valueOf(resultSet.getString(Database.ABILITY_LEVEL).toUpperCase())); 
				incompatibleStudents.add(s);				
 			} 			
 		} catch(SQLException e) {
 			e.printStackTrace();
 		} 		
 		
 		return incompatibleStudents;
	}

	public Collection<Student> completeStudents(Collection<Student> students) {
		Collection<Student> completeStudents = new ArrayList<>();
		
		//may not need different collection
		
		for(Student s: students) {			
			s.setAccommodations(getAccommodations(s));
			
			Collection<Student> incompatibles = getStudents(s);
			
		
			completeStudents.add(s);
		}		
		return completeStudents;
	}
}
