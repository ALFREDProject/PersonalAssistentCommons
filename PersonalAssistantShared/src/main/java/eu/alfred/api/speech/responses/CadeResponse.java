package eu.alfred.api.speech.responses;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by gilbe on 12.10.2015.
 */
public interface CadeResponse {
    public void OnSuccess(JSONObject response);
    public void OnSuccess(JSONArray response);
    public void OnSuccess(String resposen);
    public void OnError(Exception exception);
}
