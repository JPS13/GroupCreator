package model;

import java.util.*;

/**
 * The Classroom Class represents an elementary school classroom
 * which seats students in groups. 
 * 
 * @author Joseph Stewart
 */
public class Classroom extends Model {
	
	/** The title of the classroom. */
	private String title;
	
	/** The maximum number of groups located at the front of the classroom. */
	private int maximumFrontGroups;		
	
	/** The groups of students. */
	private Collection<Group> groups = new ArrayList<>();
	
	/**
	 * Default constructor which sets the max front groups to 10
	 * since it was not specified by the user.
	 */
	public Classroom() {
		maximumFrontGroups = 10;		
	}

	/**
	 * Constructor for Classroom that sets the title.
	 * 
	 * @param title The title for the Classroom.
	 */
	public Classroom(String title) {
		this.title = title;
		maximumFrontGroups = 10;
	}
	
	/**
	 * Constructor for Classroom that sets the title and
	 * the maximumFrontGroups.
	 * 
	 * @param title The title for the Classroom.
	 * @param maxFrontGroups The maximum number of groups
	 *  	  that fit at the front of the classroom. 
	 */
	public Classroom(String title, int maxFrontGroups) {
		this.title = title;
		maximumFrontGroups = maxFrontGroups;
	}
		
	/**
	 * This method returns the groups of students.
	 * 
	 * @return The collection of groups is returned.
	 */	
 	public Collection<Group> getGroups() {
		return groups;
	}
		
	/**
	 * This method returns the maximum number of groups that
	 * can be located in the front of the classroom. 
	 * 
	 * @return The maximum number of front groups. 
	 */	
	public int getMaximumFrontGroups() {
		return maximumFrontGroups;
	}	
		
	/**
	 * The classroom title is returned.
	 * 
	 * @return The title is returned. 
	 */	
	public String getTitle() {
		return title;
	}
			
	/**
	 * This method sets the groups for this classroom.
	 * 
	 * @param groupList The collection of groups.
	 */	
	public void setGroups(Collection<Group> groups) {
		this.groups = groups;
	}
	
	/**
	 * This method sets the maximum number of groups that can be
	 * located at the front of the classroom.
	 * 
	 * @param maxFrontGroups The maximum number of groups up front.
	 */	
	public void setMaximumFrontGroups(int maxFrontGroups) {
		if(maxFrontGroups > 0 && maxFrontGroups < 10)
			maximumFrontGroups = maxFrontGroups;
	}
	
	/**
	 * This method sets the title for the classroom. 
	 * 
	 * @param title The classroom's title.
	 */	
	public void setTitle(String title) {
		if(title != null)
			this.title = title.trim();
	}	
		
	/**
	 * This method compares this classroom to another
	 * classroom. 
	 * 
	 * @param object The other classroom to be compared.
	 * @return True if fields match, otherwise false.
	 */	
	@Override
	public boolean equals(Object object) {
		Classroom otherClassroom = (Classroom) object;
		
		return( this.title.equals(otherClassroom.getTitle()) &&
				this.maximumFrontGroups == otherClassroom.getMaximumFrontGroups() &&
				this.students.equals(otherClassroom.getStudents()) &&
				this.groups.equals(otherClassroom.getGroups()));
	}
	
	/**
	 * This method returns a String representation of the classroom.
	 * 
	 * @return The String representation of this classroom. 
	 */	
	@Override
	public String toString() {		
		return new StringBuilder(title).append(", Max Front Groups: ")
				.append(maximumFrontGroups).toString();		
	}
}