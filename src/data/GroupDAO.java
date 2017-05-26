package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import model.Group;
import model.Model;
import model.Student;

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
		
		String idQuery = "SELECT LAST_INSERT_ID()";
		
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

	@Override
	public Collection<Model> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignStudent(Model model, Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unassignStudent(Model model, Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Student> getStudents(Model model) {
		// TODO Auto-generated method stub
		return null;
	}

}
