package eu.alfred.api.sensors;

/**
 * Created by gilbe on 21.09.2015.
 */
public class SAFFacadeOld {/*
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

                    try {
                        sensorDataResponse.OnSuccess(toObjects(Base64.decode(jsonResponse.getString("value"), Base64.DEFAULT)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        sensorDataResponse.OnError(e);
                    }
                    break;
                }
            }
        }
    }

    Byte[] toObjects(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];

        int i = 0;
        for (byte b : bytesPrim) bytes[i++] = b; // Autoboxing

        return bytes;
    }

    public void GetLiveData(String sensorUri, SensorDataResponse sensorDataResponse) throws IllegalArgumentException {
        if (sensorUri == null || "".equals(sensorUri.trim())) throw new IllegalArgumentException("Empty or null Uri is not allowed.");

        if (sensorDataResponse == null) throw new IllegalArgumentException("Response must not be null.");

        sensorDataResponseHandler = new SensorDataResponseHandler(sensorDataResponse);

       // Message msg = Message.obtain(null, SAFCacheFacadeConstants.READ_LIVE_DATA);
        msg.replyTo = new Messenger(sensorDataResponseHandler);
        Bundle msgBundle = new Bundle();
        msgBundle.putString("Uri",sensorUri);
        msg.setData(msgBundle);

        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public SAFFacadeOld(Messenger messenger) throws IllegalArgumentException {
        this.messenger = messenger;

        if (messenger == null) throw new IllegalArgumentException("messenger should not be null!");

    }*/
}