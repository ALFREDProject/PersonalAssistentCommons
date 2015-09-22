package eu.alfred.api.sensors.responses;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by gilbe on 22.09.2015.
 */
public interface SensorDataResponse {
    void OnSuccess(JSONObject response);
    void OnError(Exception exception);
}