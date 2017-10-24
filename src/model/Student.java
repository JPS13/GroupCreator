package model;

import java.util.*;

/**
 * The Student class represents a student in an elementary school
 * classroom. 
 * 
 * @author Joseph Stewart
 */
public class Student extends Model implements Comparable<Student> {
		
	/** The Student's name. */
	private String name;
	
	/** The Student's gender. */
	private Gender gender;
	
	/** The Student's ability level. */
	private AbilityLevel abilityLevel;	
	
	/** Accommodations */	
	Map<String, Boolean> accommodations = new HashMap<>();
	
	/** Default Constructor. */
	public Student() {
		for(Accommodations a: Accommodations.values()) {
			accommodations.put(a.toString(), false);
		}
	}
	
	/**
	 * Constructor which sets values for name, gender, and ability level.
	 * 
	 * @param name The Student's name.
	 * @param gender The Student's gender.
	 * @param abilityLevel The Student's ability level.
	 */
	public Student(String name, Gender gender, AbilityLevel abilityLevel) {					
		this.name = name;
		this.gender = gender;
		this.abilityLevel = abilityLevel;	
		
		for(Accommodations a: Accommodations.values()) {
			accommodations.put(a.toString(), false);
		}
	}
		
	/**
	 * Returns the Student's ability level.
	 * 
	 * @return The Student's ability level.
	 */
	public AbilityLevel getAbilityLevel() {
		return abilityLevel;
	}
	
	/**
	 * Returns the map of the student's accommodations.
	 * 
	 * @return The Student's accommodations map.
	 */
	public Map<String, Boolean> getAccommodations() {
		return accommodations;
	}
	
	/**
	 * Returns the Student's name.
	 * 
	 * @return The Student's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the Student's gender.
	 * 
	 * @return The Student's gender.
	 */
	public Gender getGender() {
		return gender;
	}
		
	/**
	 * Sets the Student's ability level.
	 * 
	 * @param abilityLevel The Student's ability level.
	 */
	public void setAbilityLevel(AbilityLevel abilityLevel) {
		this.abilityLevel = abilityLevel;
	}
	
	/**
	 * Sets the accommodations for the student.
	 * 	  
	 * @param accommodations The map of the students accommodations.
	 */
	public void getAccommodations(Map<String, Boolean> accommodations) {
		this.accommodations = accommodations;
	}
	
	/**
	 * Sets the Student's gender.
	 * 
	 * @param gender The Student's gender.
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	/**
	 * Sets the name of the Student.
	 * 
	 * @param name The Student's name.
	 */
	public void setName(String name) {
		this.name = name.trim();
	}
	
	/**
	 * This method compares this Student with another one.
	 * 
	 * @param otherStudent The other Student to be compared.
	 * @return The integer value representing the comparison. 
	 */
	@Override
	public int compareTo(Student otherStudent) {
		return this.name.compareTo(otherStudent.name);
	}
	
	/**
	 * Computes a hash code for this Student.
	 * 
	 * @param result The integer hash code for this Student.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abilityLevel == null) ? 0 : abilityLevel.hashCode());
		result = prime * result + ((accommodations == null) ? 0 : accommodations.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	/**
	 * Tests this Student for equality with obj.
	 * 
	 * @param obj The other student.
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (abilityLevel != other.abilityLevel)
			return false;
		if (accommodations == null) {
			if (other.accommodations != null)
				return false;
		} else if (!accommodations.equals(other.accommodations))
			return false;
		if (gender != other.gender)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	

	/**
	 * A String representation of this Student.
	 * 
	 * @return The String representation.
	 */
	@Override
	public String toString() {		
		StringBuilder stringValue = new StringBuilder("Name: ").append(name)
				.append(", Gender: ").append(gender)				
				.append(", Ability Level: ").append(abilityLevel);
		
		for(String accommodation: accommodations.keySet()) {
			if(accommodations.get(accommodation))
				stringValue.append(", ").append(accommodation);
		}		
		return stringValue.toString();
	}
}
