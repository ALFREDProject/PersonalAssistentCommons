package eu.alfred.api.personalization.responses;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Gary on 03.03.2016.
 */
public interface PersonalizationResponse {
    public void OnSuccess(JSONObject response);
    public void OnSuccess(JSONArray response);
    public void OnSuccess(Object response);
    public void OnSuccess(String response);
    public void OnError(Exception exception);
}
