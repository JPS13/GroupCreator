package model;

import java.util.*;

/**
 * The Model class serves as the super class for Classroom, Student, 
 * and Group objects. Each of the objects has a set of subordinate
 * students and an id, both of which are encapsulated here to reduce 
 * code in each child class.
 * 
 * The subordinate students have the following meanings:
 *  - For a Classroom object, the students are those enrolled in the class.
 *  - For a Group object, the students are those assigned to the group.
 *  - For a Student object, the students are those not able to sit together.
 * 
 * @author Joseph Stewart
 */
public abstract class Model {
	
	/** The unique id assigned to the extending object. */
	protected int id;
	
	/** The set of subordinate students for the extending object. */
	protected Collection<Student> students = new ArrayList<>();
	
	/**
	 * Returns the unique id assigned to the object.
	 * 
	 * @return The unique id.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Sets the unique id of the object.
	 *  
	 * @param id The unique id.
	 */
	public void setId(int id) {
		this.id = id; 
	}
	
	/**
	 * Returns the collection of students subordinate to the extending object.
	 * 
	 * @return The subordinate students.
	 */
	public Collection<Student> getStudents() {
		return students;
	}
	
	/**
	 * Sets the students subordinate to the extending object.
	 * 
	 * @param students The collection of students to be enrolled.
	 */
	public void setStudents(Collection<Student> students) {
		this.students = students;
	}
}
