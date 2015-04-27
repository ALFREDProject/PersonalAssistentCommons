package eu.alfred.api.storage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;

import eu.alfred.api.storage.responses.BucketResponse;

/**
 * Created by Daniel on 16.04.2015.
 */
public class CloudStorage {


    private Messenger messenger;

    public CloudStorage(Messenger messenger){
        this.messenger = messenger;
    }

    private class SaveJsonResponse extends Handler {
        private BucketResponse bucketResponse;

        public SaveJsonResponse(BucketResponse bucketResponse){
            this.bucketResponse = bucketResponse;
        }

        @Override
        public void handleMessage(Message msg){
            int respCode = msg.what;

            switch (respCode) {
                //Client asked for a list of contacts. Service delivers them with this response Id
                case StorageConstants.WRITE_STRUCTURED_DATA_RESPONSE: {
                    JSONObject jsonResponse = null;

                    try {
                        jsonResponse = new JSONObject(msg.getData().getString("JsonData", "{}"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        bucketResponse.OnError(e);
                    }

                    bucketResponse.OnSuccess(jsonResponse);
                    break;
                }
            }
        }
    }
    private class CreateBucketResponse extends Handler {

        private BucketResponse bucketResponse;

        public CreateBucketResponse(BucketResponse bucketResponse){
            this.bucketResponse = bucketResponse;
        }

        @Override
        public void handleMessage(Message msg) {
            int respCode = msg.what;
            switch (respCode) {
                //Client asked for a list of contacts. Service delivers them with this response Id
                case StorageConstants.CREATE_BUCKET_RESPONSE: {
                    JSONObject jsonResponse = null;

                    try {
                        jsonResponse = new JSONObject(msg.getData().getString("JsonData", "{}"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        bucketResponse.OnError(e);
                    }

                    bucketResponse.OnSuccess(jsonResponse);
                    break;
                }
            }
        }
    }

    //region Bucket-Methods
    public void createBucket(String bucketId, BucketResponse bucketResponse) {
        if (messenger != null) {
            Message msg = Message.obtain(null, StorageConstants.CREATE_BUCKET);

            if (bucketResponse != null)
                msg.replyTo = new Messenger(new CreateBucketResponse(bucketResponse));

            //The PersonalAssistantService should respond to this Handler
            msg.getData().putString("bucketId", bucketId);
            //Will be changed. Someday.
            msg.getData().putString("bucketType", "SemiStructured");
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
                bucketResponse.OnError(e);
            }
        }
    }
    //endregion

    //region Save-Methods
    public void saveJsonObject(JSONObject jsonObject){
        saveJsonObject("", jsonObject, null);
    }

    public void saveJsonObject(JSONObject jsonObject, BucketResponse bucketResponse){
        saveJsonObject("", jsonObject, bucketResponse);
    }

    public void saveJsonObject(String bucketId, JSONObject jsonObject){
        saveJsonObject(bucketId, jsonObject, null);
    }

    public void saveJsonObject(String bucketId, JSONObject jsonObject, BucketResponse bucketResponse){
        if (messenger != null) {
            Message msg = Message.obtain(null, StorageConstants.WRITE_STRUCTURED_DATA);

            if (bucketResponse != null)
                msg.replyTo = new Messenger(new SaveJsonResponse(bucketResponse));

            //The PersonalAssistantService should respond to this Handler
            msg.getData().putString("bucketId", bucketId);
            msg.getData().putString("JsonData", jsonObject.toString());
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
    //endregion

    //region Update-Methods
    public void updateJsonObject(JSONObject jsonObject, JSONObject queryJsonObject){
        updateJsonObject("", jsonObject, queryJsonObject, null);
    }

    public void updateJsonObject(JSONObject jsonObject, JSONObject queryJsonObject, BucketResponse bucketResponse){
        updateJsonObject("", jsonObject, queryJsonObject, bucketResponse);
    }

    public void updateJsonObject(String bucketId, JSONObject jsonObject, JSONObject queryJsonObject){
        updateJsonObject(bucketId, jsonObject, queryJsonObject, null);
    }

    public void updateJsonObject(String bucketId, JSONObject jsonObject, JSONObject queryJsonObject, BucketResponse bucketResponse){

    }
    //endregion

    //region Delete-Methods
    public void deleteJsonObject(JSONObject jsonObject, JSONObject queryJsonObject){
        deleteJsonObject("", jsonObject, queryJsonObject, null);
    }

    public void deleteJsonObject(JSONObject jsonObject, JSONObject queryJsonObject, BucketResponse bucketResponse){
        deleteJsonObject("", jsonObject, queryJsonObject, bucketResponse);
    }

    public void deleteJsonObject(String bucketId, JSONObject jsonObject, JSONObject queryJsonObject){
        deleteJsonObject(bucketId, jsonObject, queryJsonObject, null);
    }

    public void deleteJsonObject(String bucketId, JSONObject jsonObject, JSONObject queryJsonObject, BucketResponse bucketResponse){

    }
    //endregion
}