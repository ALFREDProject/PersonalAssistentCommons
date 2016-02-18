package eu.alfred.api.speech.util;

import java.util.ArrayList;

public class RequestResultWHQuery {

    public ArrayList<String> values;

    public RequestResultWHQuery() {
        this.values = new ArrayList<>();
    }

    public boolean addValue(String value){
        return value != null && this.values.add(value);
    }
}
