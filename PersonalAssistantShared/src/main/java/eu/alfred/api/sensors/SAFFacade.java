package eu.alfred.api.sensors;

import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import org.json.JSONException;
import org.json.JSONObject;

import eu.alfred.api.sensors.responses.SensorDataResponse;
import eu.alfred.api.storage.StorageConstants;
import eu.alfred.api.storage.responses.BucketResponse;

/**
 * Created by gilbe on 21.09.2015.
 */
public class SAFFacade {
    private Messenger messenger;
    private SensorDataResponseHandler sensorDataResponseHandler;

    private class SensorDataResponseHandler extends Handler {
        private SensorDataResponse sensorDataResponse;

        public SensorDataResponseHandler(SensorDataResponse sensorDataResponse){
            this.sensorDataResponse = sensorDataResponse;
        }

        @Override
        public void handleMessage(Message msg){
            int respCode = msg.what;

            switch (respCode) {
                //Client asked for a list of contacts. Service delivers them with this response Id
                case SAFFacadeConstants.READ_LIVE_DATA_RESPONSE: {
                    JSONObject jsonResponse = null;

                    try {
                        String msgData = msg.getData().getString("data", "{}");
                        jsonResponse = new JSONObject(msgData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        sensorDataResponse.OnError(e);
                    }

                    sensorDataResponse.OnSuccess(jsonResponse);
                    break;
                }
            }
        }
    }

    public void GetLiveData(String sensorUri, SensorDataResponse sensorDataResponse) throws IllegalArgumentException {
        if (sensorUri == null || "".equals(sensorUri.trim())) throw new IllegalArgumentException("Empty or null Uri is not allowed.");

        if (sensorDataResponse == null) throw new IllegalArgumentException("Response must not be null.");

        sensorDataResponseHandler = new SensorDataResponseHandler(sensorDataResponse);

        Message msg = Message.obtain(null, SAFFacadeConstants.READ_LIVE_DATA);
        msg.replyTo = new Messenger(sensorDataResponseHandler);

        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public SAFFacade(Messenger messenger) throws IllegalArgumentException {
        this.messenger = messenger;

        if (messenger == null) throw new IllegalArgumentException("messenger should not be null!");

    }
}