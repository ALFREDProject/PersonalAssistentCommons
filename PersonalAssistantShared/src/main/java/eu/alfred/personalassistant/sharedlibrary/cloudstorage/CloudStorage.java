package eu.alfred.personalassistant.sharedlibrary.cloudstorage;

import android.content.Context;

import org.json.JSONObject;

import eu.alfred.personalassistant.sharedlibrary.cloudstorage.Responses.UpdateBucketResponse;

/**
 * Created by Daniel on 16.04.2015.
 */
public class CloudStorage {

    private Context mContext;

    public CloudStorage(Context context){
        mContext = context;
    }

    public void updateStructuredBucket(String bucketId, JSONObject obj, JSONObject queryObject, UpdateBucketResponse updateBucketResponse){

    }
}
