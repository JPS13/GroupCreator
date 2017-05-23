package data;

import java.util.Collection;
import model.*;

/**
 * This BaseDAO interface defines the expected behavior
 * of the implementing data access objects. It provides
 * method declarations for adding, updating, deleting,
 * and retrieving model objects as well as for assigning
 * and unassigning students to the model objects. 
 * 
 * @author Joseph Stewart
 */
public interface BaseDAO {
		
	/**
	 * Adds an object to the database.
	 * 
	 * @param model The object to be added.
	 * @return Returns the added object.
	 */
	public Model add(Model model);

	/**
	 * Updates an object in the database.
	 * 
	 * @param model The object to be updated.
	 * @return True if the update was successful; otherwise false.
	 */
	public boolean update(Model model);
		
	/**
	 * Deletes an object from the database.
	 * 
	 * @param model The object to be deleted.
	 * @return True if the deletion is successful; otherwise false.
	 */
	public boolean delete(Model model);
		
	/**
	 * Returns a collection of model objects from
	 * the database.
	 * 
	 * @return The collection of objects.
	 */
	public Collection<Model> getAll(); 	
 	
	/**
	 * Assigns a student to a model object.
	 * 
	 * @param model The object to have the student assigned.
	 * @param student The student to be assigned.
	 */
 	public void assignStudent(Model model, Student student); 	
 	
 	/**
 	 * Unassigns a student from the model object.
 	 * 
 	 * @param moodel The object to have the student unassigned.
 	 * @param student The student to be unassigned.
 	 */
 	public void unassignStudent(Model model, Student student); 
 	
 	/**
 	 * Returns a collection of students assigned to the object.
 	 * 
 	 * @param model The object with students assigned.
 	 * @return The collection of students.
 	 */
 	public Collection<Student> getStudents(Model model);
}
