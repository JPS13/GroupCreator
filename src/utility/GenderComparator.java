/*
 * GenderComparator - This class compares two students by their gender.
 * The genders are compared alphabetically so f comes before m meaning
 * that the students will be ordered with females first followed by males.
 * 
 * This class has been modified to accommodate for the situation in which
 * either of the two Students' genders are null.
 * 
 * Copyright 2016, Joseph Stewart
 */

package utility;

import java.util.Comparator;

import model.Gender;
import model.Student;

/**
 * GenderComparator - This class compares two students by their gender.
 * The genders are compared alphabetically so f comes before m meaning
 * that the students will be ordered with females first followed by males.
 * 
 * This class has been modified to accommodate for the situation in which
 * either of the two Students' genders are null.
 * 
 * @author Joseph Stewart
 * @version 1.2
 *
 */
public class GenderComparator implements Comparator<Student> {

	/**
	 * This method takes in two students as parameters and compares 
	 * their genders.
	 * 
	 * @return The integer result of the comparison is returned. 
	 */	
	@Override
	public int compare(Student s1, Student s2) {
		
		Gender s1Gender = s1.getGender();
		Gender s2Gender = s2.getGender();
		
		int s1Value = 1;
		int s2Value = 1;
		
		if(s1Gender == null) {
			s1Value = 0;
		}			
		
		if(s2Gender == null) {
			s2Value = 0;
		}
		
		if(s1Gender == null || s2Gender == null) {
			return s1Value - s2Value;
		} else {
			return s1Gender.compareTo(s2Gender);
		}		
	}
}
