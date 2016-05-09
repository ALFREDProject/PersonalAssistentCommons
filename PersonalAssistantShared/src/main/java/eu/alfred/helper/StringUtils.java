package eu.alfred.helper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
}
