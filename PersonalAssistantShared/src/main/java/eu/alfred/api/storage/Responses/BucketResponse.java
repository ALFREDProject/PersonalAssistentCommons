package eu.alfred.api.storage.responses;

import org.json.JSONObject;

/**
 * Created by Daniel on 16.04.2015.
 */
public interface BucketResponse {
    public void OnSuccess(JSONObject response);
    public void OnError(Exception exception);
}