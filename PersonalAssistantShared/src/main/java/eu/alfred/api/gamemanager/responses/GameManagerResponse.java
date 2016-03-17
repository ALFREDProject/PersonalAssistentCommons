package eu.alfred.api.gamemanager.responses;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Gary on 10.03.2016.
 */
public interface GameManagerResponse {
    public void OnSuccess(JSONObject response);
    public void OnSuccess(JSONArray response);
    public void OnSuccess(String response);
    public void OnError(Exception exception);
}
