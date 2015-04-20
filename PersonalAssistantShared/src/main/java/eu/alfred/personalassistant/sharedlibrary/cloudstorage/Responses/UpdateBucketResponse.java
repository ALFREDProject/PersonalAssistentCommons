package eu.alfred.personalassistant.sharedlibrary.cloudstorage.Responses;

import android.os.AsyncTask;

import org.json.JSONObject;

/**
 * Created by Daniel on 16.04.2015.
 */
public interface UpdateBucketResponse {
    public void OnResponse(JSONObject response);
}
