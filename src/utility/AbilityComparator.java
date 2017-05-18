/*
 * AbilityComparator - This class compares students based on 
 * their ability levels. There are three ability levels: low,
 * average, and high. The result is in ascending order with 
 * low being the lowest and high being the highest.
 * 
 * This class has been modified to accommodate cases in which
 * either of the two Student objects has a null value for the
 * abilityLevel.
 * 
 * Joseph Stewart, 2016
 */

package utility;

import java.util.Comparator;
import model.*;

/**
 * AbilityComparator - This class compares students based on 
 * their ability levels. There are three ability levels: low,
 * average, and high. The result is in ascending order with 
 * low being the lowest and high being the highest.
 * 
 * This class has been modified to accommodate cases in which
 * either of the two Student objects has a null value for the
 * abilityLevel.
 * 
 * @author Joseph Stewart
 * @version 1.2
 */
public class AbilityComparator implements Comparator<Student> {

	/**
	 * This method takes in two students as parameters and compares
	 * their ability levels.
	 * 
	 * @return The integer result of the comparison is returned.
	 */	
	@Override
	public int compare(Student s1, Student s2) {
		
		AbilityLevel s1Ability = s1.getAbilityLevel();
		AbilityLevel s2Ability = s2.getAbilityLevel();	
		
		int s1Value;
		int s2Value;		
		
		// Assign value to s1
		if(s1Ability == null) {
			s1Value = 0;
		} else if(s1Ability.equals(AbilityLevel.HIGH)) {
			s1Value = 3;
		} else if(s1Ability.equals(AbilityLevel.AVERAGE)) {
			s1Value = 2;
		} else {
			s1Value = 1;
		}	
				
		// Assign value to s2
		if(s2Ability == null) {
			s2Value = 0;
		} else if(s2Ability.equals(AbilityLevel.HIGH)) {
			s2Value = 3;
		} else if(s2Ability.equals(AbilityLevel.AVERAGE)) {
			s2Value = 2;
		} else {
			s2Value = 1;
		}
		
		return s1Value - s2Value;		
	}
}
