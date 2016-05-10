package eu.alfred.helper;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtils {
	public static String getReadableString(Map<String, String> map) {
		String readableString = "{";
		if (map != null) {
			boolean first = true;
			for (String key : map.keySet()) {
				if (!first) {
					readableString += ", " + key + ":" + map.get(key);
				} else {
					first = false;
					readableString += key;
				}
			}
		}
		readableString += "}";
		return readableString;
	}

	public static void compactListToString(JsonObject updateObject, String member) {

		JsonArray ja = updateObject.getAsJsonArray(member);
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i = 0; i < ja.size(); i++) {
			if (i > 0) sb.append(',');
			sb.append('"').append(ja.get(i).getAsString()).append('"');
		}
		sb.append(']');
		updateObject.remove(member);
		updateObject.addProperty(member, sb.toString());

	}

	public static void compactListsToString(JsonObject updateObject) {

		List<String> removeEntries = new ArrayList<String>();
		Map<String, String> addEntries = new HashMap<String, String>();

		for (Map.Entry<String, JsonElement> entry : updateObject.entrySet()) {

			if (entry.getValue().isJsonArray()) {

				JsonArray ja = entry.getValue().getAsJsonArray();
				StringBuilder sb = new StringBuilder();
				sb.append('[');
				for (int i = 0; i < ja.size(); i++) {
					if (i > 0) sb.append(',');
					sb.append('"').append(ja.get(i).getAsString()).append('"');
				}
				sb.append(']');
				removeEntries.add(entry.getKey());
				addEntries.put(entry.getKey(), sb.toString());

			}
		}
		for (String key : removeEntries)
			updateObject.remove(key);
		for (Map.Entry<String, String> entry : addEntries.entrySet())
			updateObject.add(entry.getKey(), new JsonPrimitive(entry.getValue()));

	}

	public static void flattenJsonSubStructs(JsonObject updateObject) {

		List<String> removeEntries = new ArrayList<String>();
		Map<String, String> addEntries = new HashMap<String, String>();

		for (Map.Entry<String, JsonElement> entry : updateObject.entrySet()) {

			if (entry.getValue().isJsonObject()) {
				JsonObject subObject = entry.getValue().getAsJsonObject();

				flattenJsonSubStructs(subObject);

				for (Map.Entry<String, JsonElement> subEntry : subObject.entrySet()) {
					addEntries.put(entry.getKey() + "." + subEntry.getKey(), subEntry.getValue().getAsString());
				}

				removeEntries.add(entry.getKey());

			}
		}
		for (String key : removeEntries)
			updateObject.remove(key);
		for (Map.Entry<String, String> entry : addEntries.entrySet())
			updateObject.add(entry.getKey(), new JsonPrimitive(entry.getValue()));
	}

	public static void fixFullJson(JsonObject updateObject) {

		List<String> removeEntries = new ArrayList<String>();
		Map<String, String> addEntries = new HashMap<String, String>();

		for (Map.Entry<String, JsonElement> entry : updateObject.entrySet()) {

			if (entry.getValue().isJsonArray()) {

				JsonArray ja = entry.getValue().getAsJsonArray();
				StringBuilder sb = new StringBuilder();
				sb.append('[');
				for (int i = 0; i < ja.size(); i++) {
					if (i > 0) sb.append(',');
					sb.append('"').append(ja.get(i).getAsString()).append('"');
				}
				sb.append(']');
				removeEntries.add(entry.getKey());
				addEntries.put(entry.getKey(), sb.toString());

			}

			if (entry.getValue().isJsonObject()) {
				JsonObject subObject = entry.getValue().getAsJsonObject();

				fixFullJson(subObject);

				for (Map.Entry<String, JsonElement> subEntry : subObject.entrySet()) {
					addEntries.put(entry.getKey() + "." + subEntry.getKey(), subEntry.getValue().getAsString());
				}

				removeEntries.add(entry.getKey());

			}
		}
		for (String key : removeEntries)
			updateObject.remove(key);
		for (Map.Entry<String, String> entry : addEntries.entrySet())
			updateObject.add(entry.getKey(), new JsonPrimitive(entry.getValue()));
	}
}
