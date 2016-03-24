package eu.alfred.api.storage.responses;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Daniel on 16.04.2015.
 */
public interface BucketResponse {
    public void OnSuccess(JSONObject response);
    public void OnSuccess(JSONArray response);
    public void OnSuccess(byte[] response);
    public void OnError(Exception exception);
}