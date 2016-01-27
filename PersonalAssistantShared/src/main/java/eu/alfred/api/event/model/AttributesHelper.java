package eu.alfred.api.event.model;

import java.lang.reflect.Field; 
import java.util.HashSet;

public class AttributesHelper {
	
	public HashSet<String> getUserProfileFields() {
		
		UserProfile userProfile = new UserProfile();
		Class<?> c = userProfile.getClass();
		Field[] fields = c.getDeclaredFields();
		HashSet<String> userProfileFields = new HashSet<String>();
		
		for(Field field : fields) 			
			userProfileFields.add(field.getName());
		
		
		return userProfileFields;
	}
	
		
	
}
