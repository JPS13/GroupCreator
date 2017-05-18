package model;

/**
 * The Gender enum represents the Gender of Student. It is
 * limited to male or female to enforce standardization in
 * the displays and data source.
 * 
 * @author Joseph Stewart
 */
public enum Gender {
	MALE, FEMALE;
	
	/**
	 * A String representation of the Gender attribute.
	 * 
	 * @return The String representation. 
	 */
	@Override
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
