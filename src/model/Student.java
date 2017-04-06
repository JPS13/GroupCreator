package model;

public class Student extends Model {
	
	private String firstName;
	private String lastName;
	private Gender gender;	
	private AbilityLevel abilityLevel;	
	private boolean frontSeatNeeded;
	private boolean preferredGroupOfFive;
						
	public Student() { }
	
	public Student(String firstName, String lastName, Gender gender, AbilityLevel abilityLevel) {					
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.abilityLevel = abilityLevel;	
	}
		
	public boolean equals(Object object) {
		Student otherStudent = (Student) object;
		
		return firstName.equals(otherStudent.getFirstName())
				&& lastName.equals(otherStudent.getLastName())
				&& gender == otherStudent.getGender()
				&& abilityLevel == otherStudent.getAbilityLevel()
				&& frontSeatNeeded == otherStudent.getFrontSeatNeeded()
				&& preferredGroupOfFive == otherStudent.getPreferredGroupOfFive();
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
	
	public void setAbilityLevel(AbilityLevel ability) {
		abilityLevel = ability;
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
	
	public void setPreferredGroupOfFive(boolean Five) {
		preferredGroupOfFive = Five;
	}
	
	@Override
	public String toString() {
		
		String string = "Name: " + firstName + " " + lastName + 
						", Gender: " + gender + 
						", Ability Level: " + abilityLevel; 
		
		if(frontSeatNeeded) {
			string += ", Front Seat";
		}
		
		if(preferredGroupOfFive) {
			string += ", Group of Five";
		}
		
		return string;
	}

}
