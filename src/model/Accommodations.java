package model;

public enum Accommodations {

	GROUP_OF_FIVE, FRONT_SEAT;
	
	/**
	 * The String representation of a Student's accommodations.
	 * 
	 * @return The String representation.
	 */
	@Override
	public String toString() {
		String accommodation = null;
		
		switch(this) {
		case GROUP_OF_FIVE: accommodation = "Group of Five";
			break;
		case FRONT_SEAT: accommodation = "Front Seat Needed";
			break;
		}
		return accommodation;
	}
}
