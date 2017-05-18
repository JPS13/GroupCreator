package model;

/**
 * The Student class represents a student in an elementary room
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
	
	/** Boolean flag to indicate whether or not the student needs to sit up front. */
	private boolean frontSeatNeeded;
	
	/** Boolean flag to indicate whether or not the student needs to be in a group of five. */
	private boolean preferredGroupOfFive;
		
	/** Default Constructor. */
	public Student() { }
	
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
	 * Returns the Student's name.
	 * 
	 * @return The Student's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the value of the frontSeatNeeded flag.
	 * 
	 * @return The boolean flag for frontSeatNeeded.
	 */
	public boolean getFrontSeatNeeded() {		
		return frontSeatNeeded;		
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
	 * Returns the value of the preferredGroupOfFive.
	 *  
	 * @return the boolean flag value.
	 */
	public boolean getPreferredGroupOfFive() {
		return preferredGroupOfFive;
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
	 * Sets the value for whether or not the Student needs
	 * to sit at the front of the classroom. 
	 * 
	 * @param frontSeatNeeded The boolean flag for the desired result. 
	 */
	public void setFrontSeatNeeded(boolean frontSeatNeeded) {
		this.frontSeatNeeded = frontSeatNeeded;
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
	 * Sets the value for whether or not this Student should
	 * be in a group of five.
	 * 
	 * @param preferredGroupOfFive The boolean flag for the desired result.
	 */
	public void setPreferredGroupOfFive(boolean preferredGroupOfFive) {
		this.preferredGroupOfFive = preferredGroupOfFive;
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
	 * computes a numeric hash code for this Student.
	 * 
	 * @return The computed hash code.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abilityLevel == null) ? 0 : abilityLevel.hashCode());
		result = prime * result + (frontSeatNeeded ? 1231 : 1237);
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (preferredGroupOfFive ? 1231 : 1237);
		return result;
	}

	/**
	 * This method tests this Student and another Student
	 * for equality.
	 * 
	 * @param object The other Student to compare.
	 * @return True if the two objects are equal; false otherwise.
	 */
	@Override
	public boolean equals(Object object) {		
		Student otherStudent = (Student) object;		
		
		return (object != null &&
				this != otherStudent &&
				this.name.equals(otherStudent.getName()) &&
				this.gender != otherStudent.getGender() &&
				this.abilityLevel != otherStudent.getAbilityLevel() &&
				this.frontSeatNeeded != otherStudent.getFrontSeatNeeded() &&
				this.preferredGroupOfFive != otherStudent.getPreferredGroupOfFive());		
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
		
		if(frontSeatNeeded) {
			stringValue.append(", Needs Front Seat");
		}
		
		if(preferredGroupOfFive) {
			stringValue.append(", Needs Group of Five");
		}
		
		return stringValue.toString();
	}
}
