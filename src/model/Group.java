package model;

import java.text.SimpleDateFormat;
import java.util.*;

public class Group extends Model {
	
	private int groupNumber;
	private boolean isFrontGroup;
	private String dateCreated;
	
	public Group() { 		
		setDate();	 
	}
	
	public Group(List<Student> studentList) {			
		setDate();		
		this.studentList = studentList;		
	}
	
	/**
	 * This method compares data fields to test equality.
	 * 
	 * @param group The other group for comparison.
	 * @return True if the Groups are equal; otherwise false.
	 */
	
	public boolean equals(Group group) {
		return (this.dateCreated == group.getDateCreated() &&
				this.groupNumber == group.getGroupNumber() &&
				this.isFrontGroup == group.getIsFrontGroup() &&
				this.studentList.equals(group.getStudentList()));
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
	 * Sets the date this group was created. This method will 
	 * overwrite the dateCreated field to accommodate retrieving
	 * a previously created Group from a data source. 
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
	 * This method returns whether or not this group should be
	 * located at the front of the classroom.
	 * 
	 * @param isFront The boolean value for isFrontGroup.
	 */
	
	public void setIsFrontGroup(boolean isFront) {
		isFrontGroup = isFront;
	}
	
	/**
	 * The String representation of this group is returned.
	 * 
	 * @return The String representation of this group is returned. 
	 */
	
	public String toString() {
		
		String string = "Group # " + groupNumber + ", " + dateCreated;
		
		if(isFrontGroup) {
			string += ", Front Group";
		}
				
		return string;
	}

}
