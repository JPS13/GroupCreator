package model;

public enum Gender {
	MALE, FEMALE;
	
	public String toString() {		
		String gender = null;
		
		switch (this) {
		case MALE: gender = "Male";
			break;
		case FEMALE: gender = "Female";
			break;		
		}		
		return gender;
	}
}
