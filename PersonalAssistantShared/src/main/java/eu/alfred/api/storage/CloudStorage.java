package eu.alfred.api.storage;

import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import eu.alfred.api.storage.responses.BucketResponse;

/**
 * Created by Daniel on 16.04.2015.
 */
public class CloudStorage {


    private Messenger messenger;

    public CloudStorage(Messenger messenger){
        this.messenger = messenger;
    }

    private class CloudStorageHttpResponse extends Handler {
        private BucketResponse bucketResponse;

        public CloudStorageHttpResponse(BucketResponse bucketResponse){
            this.bucketResponse = bucketResponse;
        }

        @Override
        public void handleMessage(Message msg){
            int respCode = msg.what;

            switch (respCode) {
                //Client asked for a list of contacts. Service delivers them with this response Id
                case StorageConstants.WRITE_STRUCTURED_DATA_RESPONSE:
                case StorageConstants.WRITE_BINARY_DATA_RESPONSE:
                case StorageConstants.UPDATE_STRUCTURED_DATA_RESPONSE:
                case StorageConstants.UPDATE_BINARY_DATA_RESPONSE:
                case StorageConstants.DELETE_STRUCTURED_DATA_RESPONSE:
                case StorageConstants.DELETE_BINARY_DATA_RESPONSE:
                case StorageConstants.CREATE_BUCKET_RESPONSE:
                case StorageConstants.DELETE_BUCKET_RESPONSE:


                {
                    try {
                        String httpCode = Integer.toString(msg.getData().getInt("HttpCode"));
                        JSONObject jsonResponse = new JSONObject();
                        jsonResponse.put("response",httpCode);
                        bucketResponse.OnSuccess(jsonResponse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        bucketResponse.OnError(e);
                    }
                    break;
                }
            }
        }
    }

    private class ReadDataResponse extends Handler {
        private BucketResponse bucketResponse;

        public ReadDataResponse(BucketResponse bucketResponse){
            this.bucketResponse = bucketResponse;
        }

        @Override
        public void handleMessage(Message msg){
            int respCode = msg.what;

            switch (respCode) {
                //Client asked for a list of contacts. Service delivers them with this response Id
                case StorageConstants.READ_STRUCTURED_DATA_RESPONSE:
                    JSONArray jsonResponse = null;

                    try {
                        jsonResponse = new JSONArray(msg.getData().getString("response"));
                        bucketResponse.OnSuccess(jsonResponse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        bucketResponse.OnError(e);
                    }
                    break;
                case StorageConstants.READ_BINARY_DATA_RESPONSE:{
                    String binaryResponse = null;

                    try {
                        binaryResponse = msg.getData().getString("response", "");
                        bucketResponse.OnSuccess(binaryResponse.getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                        bucketResponse.OnError(e);
                    }
                    break;
                }
            }
        }
    }


    public void writeJsonObject(String bucketId, JSONObject jsonObject, BucketResponse bucketResponse){
        if (messenger != null) {
            Message msg = Message.obtain(null, StorageConstants.WRITE_STRUCTURED_DATA);

            if (bucketResponse != null)
                msg.replyTo = new Messenger(new CloudStorageHttpResponse(bucketResponse));

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

    public void writeBinaryData(String bucketId, String binaryKey, byte[] data, BucketResponse bucketResponse){
        if (messenger != null) {
            Message msg = Message.obtain(null, StorageConstants.WRITE_BINARY_DATA);

            if (bucketResponse != null)
                msg.replyTo = new Messenger(new CloudStorageHttpResponse(bucketResponse));

            //The PersonalAssistantService should respond to this Handler
            msg.getData().putString("bucketId", bucketId);
            msg.getData().putString("binaryKey", binaryKey);
            msg.getData().putString("binaryBase64Data", Base64.encodeToString(data, Base64.DEFAULT));
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void readJsonArray(String bucketId, JSONObject jsonQuery, BucketResponse bucketResponse){
        if (messenger != null) {
            Message msg = Message.obtain(null, StorageConstants.READ_STRUCTURED_DATA);

            if (bucketResponse != null)
                msg.replyTo = new Messenger(new ReadDataResponse(bucketResponse));

            //The PersonalAssistantService should respond to this Handler
            msg.getData().putString("bucketId", bucketId);
            msg.getData().putString("jsonQueryData", jsonQuery.toString());
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void readBinaryArray(String bucketId, String binaryKey, byte[] data, BucketResponse bucketResponse){
        if (messenger != null) {
            Message msg = Message.obtain(null, StorageConstants.READ_BINARY_DATA);

            if (bucketResponse != null)
                msg.replyTo = new Messenger(new ReadDataResponse(bucketResponse));

            //The PersonalAssistantService should respond to this Handler
            msg.getData().putString("bucketId", bucketId);
            msg.getData().putString("binaryKey", binaryKey);
            msg.getData().putString("binaryBase64Data", Base64.encodeToString(data, Base64.DEFAULT));
            msg.getData().putByteArray("binaryData", data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateJsonObject(String bucketId, JSONObject jsonObject, JSONObject queryJsonObject, BucketResponse bucketResponse) {
        if (messenger != null) {
            Message msg = Message.obtain(null, StorageConstants.UPDATE_STRUCTURED_DATA);

            if (bucketResponse != null)
                msg.replyTo = new Messenger(new CloudStorageHttpResponse(bucketResponse));

            //The PersonalAssistantService should respond to this Handler
            msg.getData().putString("bucketId", bucketId);
            msg.getData().putString("JsonData", jsonObject.toString());
            msg.getData().putString("JsonQueryData", queryJsonObject.toString());
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateBinaryData(String bucketId, String binaryKey, byte[] data, BucketResponse bucketResponse) {
        if (messenger != null) {
            Message msg = Message.obtain(null, StorageConstants.UPDATE_BINARY_DATA);

            if (bucketResponse != null)
                msg.replyTo = new Messenger(new CloudStorageHttpResponse(bucketResponse));

            //The PersonalAssistantService should respond to this Handler
            msg.getData().putString("bucketId", bucketId);
            msg.getData().putString("binaryKey", binaryKey);
            msg.getData().putString("binaryBase64Data", Base64.encodeToString(data, Base64.DEFAULT));
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteJsonObject(String bucketId, JSONObject queryJsonObject, BucketResponse bucketResponse){
        if (messenger != null) {
            Message msg = Message.obtain(null, StorageConstants.DELETE_STRUCTURED_DATA);

            if (bucketResponse != null)
                msg.replyTo = new Messenger(new CloudStorageHttpResponse(bucketResponse));

            //The PersonalAssistantService should respond to this Handler
            msg.getData().putString("bucketId", bucketId);
            msg.getData().putString("jsonQueryData", queryJsonObject.toString());
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteBinaryData(String bucketId, String binaryKey, BucketResponse bucketResponse){
        if (messenger != null) {
            Message msg = Message.obtain(null, StorageConstants.DELETE_BINARY_DATA);

            if (bucketResponse != null)
                msg.replyTo = new Messenger(new CloudStorageHttpResponse(bucketResponse));

            //The PersonalAssistantService should respond to this Handler
            msg.getData().putString("bucketId", bucketId);
            msg.getData().putString("binaryKey", binaryKey);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void createStructuredBucket(String bucketId, BucketResponse bucketResponse) {
        if (messenger != null) {
            Message msg = Message.obtain(null, StorageConstants.CREATE_STRUCTURED_BUCKET);

            if (bucketResponse != null)
                msg.replyTo = new Messenger(new CloudStorageHttpResponse(bucketResponse));

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

    public void createBinaryBucket(String bucketId, BucketResponse bucketResponse) {
        if (messenger != null) {
            Message msg = Message.obtain(null, StorageConstants.CREATE_BINARY_BUCKET);

            if (bucketResponse != null)
                msg.replyTo = new Messenger(new CloudStorageHttpResponse(bucketResponse));

            //The PersonalAssistantService should respond to this Handler
            msg.getData().putString("bucketId", bucketId);
            //Will be changed. Someday.
            msg.getData().putString("bucketType", "Binary");
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
                bucketResponse.OnError(e);
            }
        }
    }

    public void deleteBucket(String bucketId, BucketResponse bucketResponse) {
        if (messenger != null) {
            Message msg = Message.obtain(null, StorageConstants.DELETE_BUCKET);

            if (bucketResponse != null)
                msg.replyTo = new Messenger(new CloudStorageHttpResponse(bucketResponse));

            //The PersonalAssistantService should respond to this Handler
            msg.getData().putString("bucketId", bucketId);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
                bucketResponse.OnError(e);
            }
        }
    }
}