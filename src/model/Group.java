package model;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The Group class represents a group of students in an elementary
 * school classroom. 
 * 
 * @author Joseph Stewart
 */
public class Group extends Model {
	
	/** The group number. */
	private int groupNumber;
	
	/** Boolean flag for whether or not the Group is located at the front of the classroom.*/
	private boolean isFrontGroup;
	
	/** The date this Group was created. */
	private String dateCreated;
	
	/** Default Constructor. */
	public Group() { 		
		setDate();	 
	}
	
	/**
	 * Constructor which sets the collection of students.
	 * 
	 * @param students The students assigned to this Group.
	 */
	public Group(Collection<Student> students) {			
		setDate();		
		this.students = (Set<Student>) students;		
	}
		
	/**
	 *  This method returns the String value for the date 
	 *  this group was created.
	 *  
	 * @return The date this group was created.
	 */	
	public String getDateCreated() {
		return dateCreated;
	}

	/**
	 * This method returns the group number.
	 * 
	 * @return The group number.
	 */	
	public int getGroupNumber() {		
		return groupNumber;		
	}
	
	/**
	 * This method returns whether or not this group is
	 * identified as a group that should be up front.
	 * 
	 * @return The boolean value is returned. 
	 */	
	public boolean getIsFrontGroup() {
		return isFrontGroup;
	}
	
	/**
	 * Sets the date this group was created.
	 * 
	 */	
	private void setDate() {		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");        
 	    dateCreated = dateFormat.format(new Date());
	}
	
	/**
	 * Sets the date this group was created. 
	 * 
	 * @param date The date this group was created.
	 */	
	public void setDateCreated(String date) {
		this.dateCreated = date;
	}

	/**
	 * This method sets the group number for this group.
	 * 
	 * @param number The group number.
	 */	
	public void setGroupNumber(int number) {		
		groupNumber = number;		
	}
	
	/**
	 * This method sets the flag for whether or not this group should be
	 * located at the front of the classroom.
	 * 
	 * @param isFront The boolean flag for isFrontGroup.
	 */	
	public void setIsFrontGroup(boolean isFront) {
		isFrontGroup = isFront;
	}
	
	/**
	 * This method compares data fields to test equality.
	 * 
	 * @param group The other group for comparison.
	 * @return True if the Groups are equal; otherwise false.
	 */
	@Override
	public boolean equals(Object object) {
		Group group = (Group)object;
		
		return (this.dateCreated == group.getDateCreated() &&
				this.groupNumber == group.getGroupNumber() &&
				this.isFrontGroup == group.getIsFrontGroup() &&
				this.students.equals(group.getStudents()));
	}
	
	/**
	 * The String representation of this group is returned.
	 * 
	 * @return The String representation of this group. 
	 */
	@Override
	public String toString() {
		StringBuilder groupString = new StringBuilder("Group # ");
		
		groupString.append(groupNumber).append(", ").append(dateCreated);
		
		if(isFrontGroup) {
			groupString.append(", Front Group");
		}
				
		return groupString.toString();
	}
}
