package eu.alfred.api.sensors;

import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import org.json.JSONException;
import org.json.JSONObject;

import eu.alfred.api.storage.StorageConstants;
import eu.alfred.api.storage.responses.BucketResponse;

/**
 * Created by gilbe on 21.09.2015.
 */
public class SAFFacade {
    private Messenger messenger;
    private SAFFacadeReceivedResponse safFacadeReceivedResponse;

    private class SAFFacadeReceivedResponse extends Handler {

        public SAFFacadeReceivedResponse(){}

        @Override
        public void handleMessage(Message msg){
            int respCode = msg.what;

            switch (respCode) {
                //Client asked for a list of contacts. Service delivers them with this response Id
                case SAFFacadeConstants.READ_LEGACY_DATA_RESPONSE: {
                    JSONObject jsonResponse = null;

                    try {
                        jsonResponse = new JSONObject(msg.getData().getString("JsonData", "{}"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //bucketResponse.OnSuccess(jsonResponse);
                    break;
                }
            }
        }
    }

    public SAFFacade(Messenger messenger) throws IllegalArgumentException {
        this.messenger = messenger;

        if (messenger == null) throw new IllegalArgumentException("messenger should not be null!");

        safFacadeReceivedResponse = new SAFFacadeReceivedResponse();

        Message msg = Message.obtain(null, SAFFacadeConstants.READ_LEGACY_DATA);
        msg.replyTo = new Messenger(safFacadeReceivedResponse);

        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}