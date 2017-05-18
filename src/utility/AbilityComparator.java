package utility;

import java.util.Comparator;
import model.*;

/**
 * The AbilityComparator allows Students to be compared based on
 *  their ability levels instead of their names (natural ordering).
 * 
 * @author Joseph Stewart
 */
public class AbilityComparator implements Comparator<Student> {

	/**
	 * This method takes in two students as parameters and compares
	 * them based on their ability levels.
	 * 
	 * @return The integer result of the comparison is returned.
	 */	
	@Override
	public int compare(Student s1, Student s2) {
		int s1Value;
		int s2Value;		
		
		// Assign a value to s1Value
		if(s1 == null || s1.getAbilityLevel() == null) {
			s1Value = 0;
		} else {			
			if(s1.getAbilityLevel().equals(AbilityLevel.HIGH)) {
				s1Value = 3;
			} else if(s1.getAbilityLevel().equals(AbilityLevel.AVERAGE)) {
				s1Value = 2;
			} else {
				s1Value = 1;
			}			
		}
				
		// Assign a value to s2Value
		if(s2 == null || s2.getAbilityLevel() == null) {
			s2Value = 0;
		} else {
			if(s2.getAbilityLevel().equals(AbilityLevel.HIGH)) {
				s2Value = 3;
			} else if(s2.getAbilityLevel().equals(AbilityLevel.AVERAGE)) {
				s2Value = 2;
			} else {
				s2Value = 1;
			}		
		}		
		return s1Value - s2Value;		
	}
}
