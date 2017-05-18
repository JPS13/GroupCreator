package model;

public class Student extends Model implements Comparable<Student> {
	
	private String firstName;
	private String lastName;
	private Gender gender;	
	private AbilityLevel abilityLevel;	
	
	// Accommodations 
	private boolean frontSeatNeeded;
	private boolean preferredGroupOfFive;
						
	public Student() { }
	
	public Student(String firstName, String lastName, Gender gender, AbilityLevel abilityLevel) {					
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.abilityLevel = abilityLevel;	
	}
		
	public AbilityLevel getAbilityLevel() {
		return abilityLevel;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public boolean getFrontSeatNeeded() {		
		return frontSeatNeeded;		
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public boolean getPreferredGroupOfFive() {
		return preferredGroupOfFive;
	}
	
	public void setAbilityLevel(AbilityLevel abilityLevel) {
		this.abilityLevel = abilityLevel;
	}
	
	public void setFirstName(String firstName) {
			this.firstName = firstName.trim();
	}
	
	public void setFrontSeatNeeded(boolean frontSeatNeeded) {
		this.frontSeatNeeded = frontSeatNeeded;
	}
		
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public void setLastName(String lastName) {
			this.lastName = lastName.trim();
	}
	
	public void setPreferredGroupOfFive(boolean preferredGroupOfFive) {
		this.preferredGroupOfFive = preferredGroupOfFive;
	}
	
	
	@Override
	public int compareTo(Student otherStudent) {
		if(this.lastName.compareTo(otherStudent.lastName) == 0) {
			return this.firstName.compareTo(otherStudent.firstName);
		} else {
			return this.lastName.compareTo(otherStudent.lastName);
		}
	}
	
	@Override
	public boolean equals(Object object) {
		Student otherStudent = (Student) object;
		
		return firstName.equals(otherStudent.getFirstName())
				&& lastName.equals(otherStudent.getLastName())
				&& gender == otherStudent.getGender()
				&& abilityLevel == otherStudent.getAbilityLevel()
				&& frontSeatNeeded == otherStudent.getFrontSeatNeeded()
				&& preferredGroupOfFive == otherStudent.getPreferredGroupOfFive();
	}
	
	@Override
	public String toString() {
		
		StringBuilder stringValue = new StringBuilder("Name: ").append(firstName)
				.append(" ").append(lastName)
				.append(", Gender: ").append(gender)				
				.append(", Ability Level: ").append(abilityLevel);
		
		if(frontSeatNeeded) {
			stringValue.append(", Front Seat");
		}
		
		if(preferredGroupOfFive) {
			stringValue.append(", Group of Five");
		}
		
		return stringValue.toString();
	}
}
