package eu.alfred.api.speech.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestResultEntityRecognizer {

    public List<Map<String, String>> values;

    public RequestResultEntityRecognizer() {
        this.values = new ArrayList<Map<String, String>>();
    }

    public boolean addValue(Map<String, String> map) {
        return map != null && this.values.add(map);
    }
}
