package eu.alfred.api.event.util;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.type.TypeReference;

/**
 * This class is a utility class which focuses mainly in data transformations between Objects, collections and Json Strings.
 * 
 * @author tasos
 *
 */
public class DataTransformationsHandler {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Transforms hashmap of keys and values to a Json String.
	 * 
	 * @param keysValues A HashMap with key and value pairs which will be transformed to a json String.
	 * @return A String formed as Json containing the keys and values. 
	 */
	public String KeyValuesToJson(HashMap<String,String> keysValues) {
		String jsonResult="{";
		
		for(Map.Entry<String, String> keyValueEntry : keysValues.entrySet()) {
			jsonResult += "\"" + keyValueEntry.getKey() + "\":\"" + keyValueEntry.getValue() + "\",";
		}
		// An ugly way to remove last ','. Find better solution
		jsonResult=jsonResult.substring(0,jsonResult.length()-1);
		jsonResult +="}"; 
		return jsonResult;
	}
	
	/**
	 * Breaks a Json String to a hashmap.
	 * 
	 * @param rawJson The String with the Json content to be transformed in a hashmap.
	 * @return A HashMap with the keys and values extracted by the json.
	 */
	public HashMap<String,String> breakJsonToHashMap(String rawJson){		
		HashMap<String,String> jsonAsMap = new HashMap<String,String>();
		ObjectMapper mapper = new ObjectMapper();
	 
		try {
			//convert JSON string to Map
			jsonAsMap = mapper.readValue(rawJson, new TypeReference<HashMap<String,String>>(){});
	 	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonAsMap;
	}
	
	/**
	 * Maps an object to a Json String
	 * 
	 * @param objectToMap The object which will be transformed into a Json.
	 * @return A String representing the Json representation of the object.
	 */
	public String mapObjectToJson(Object objectToMap) {
		String mappedJson=null;		
		
		try{
			ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
			mappedJson = objectWriter.writeValueAsString(objectToMap);
		}catch(Exception ex) {
			ex.printStackTrace();
		} 
		
		return mappedJson;
	}
	
	public static ObjectMapper getMapperInstance() {
		return mapper;
	}
		
}
