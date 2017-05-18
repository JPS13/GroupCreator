package model;

/**
 * The enum AbilityLevel represents an elementary school student's
 * academic ability level. It is used to enforce standardized 
 * representation in the display and data source. 
 * 
 * @author Joseph Stewart
 */
public enum AbilityLevel {
	LOW, AVERAGE, HIGH;
	
	/**
	 * The String representation of a Student's ability level.
	 * 
	 * @return The String representation.
	 */
	@Override
	public String toString() {
		String ability = null;
		
		switch(this) {
		case LOW: ability = "Low";
			break;
		case AVERAGE: ability = "Average";
			break;
		case HIGH: ability = "High";
			break;
		}
		return ability;
	}
}
