package eu.alfred.api.personalization.model;

public enum BMICategory {

	VERY_SEVERELY_UNDERWEIGHT("Very severely underweight",  15.0),
	SEVERELY_UNDERWEIGHT("Severely underweight",  16.0),
	UNDERWEIGHT("Underweight",  18.5),
	NORMAL("Normal (healthy weight)", 25.0),
	OVERWEIGHT("Overweight", 30.0),
	OBESE_I("Obese Class I (Moderately obese)", 35.0),
	OBESE_II("Obese Class II (Severely obese)", 40.0),
	OBESE_III("Obese Class III (Very severely obese)", 999.0);
	
	private String label;
	private double upperLimit;
	
	BMICategory(String l, double ul) {
		label = l;
		upperLimit = ul;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static BMICategory getBMICategoryForValue(double bmi) {
		for (BMICategory c : values()) {
			if (bmi < c.upperLimit) return c;
		}
		return null;
	}
	
}
