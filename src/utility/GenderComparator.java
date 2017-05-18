package utility;

import java.util.Comparator;

import model.*;

/**
 * The GenderComparator enum allows for students to be compared
 * based on their genders instead of their names (natural ordering). 
 * 
 * @author Joseph Stewart
 */
public class GenderComparator implements Comparator<Student> {

	/**
	 * This method takes in two students as parameters and compares 
	 * them based on their genders.
	 * 
	 * @return The integer result of the comparison is returned. 
	 */	
	@Override
	public int compare(Student s1, Student s2) {
		int s1Value = 1;
		int s2Value = 1;
		
		if(s1 == null || s1.getGender() == null)
			s1Value = 0;		
		
		if(s2 == null || s2.getGender() == null)
			s2Value = 0;				
		
		if(s1Value == 0 || s2Value == 0) {
			return s1Value - s2Value;
		} else {
			return s1.getGender().compareTo(s2.getGender());
		}			
	}
}
