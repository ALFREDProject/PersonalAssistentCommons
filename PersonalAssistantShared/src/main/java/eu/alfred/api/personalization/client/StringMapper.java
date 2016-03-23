package eu.alfred.api.personalization.client;

public class StringMapper {

	public static String toModel(StringDto s) {
		return s.string;
	}

	public static StringDto toDto(String s) {
		StringDto sd = new StringDto();
		sd.string = s;
		return sd;
	}

}
