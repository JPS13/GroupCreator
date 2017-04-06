package model;

public enum AbilityLevel {
	LOW, AVERAGE, HIGH;
	
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
