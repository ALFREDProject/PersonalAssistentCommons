package eu.alfred.api.speech;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.google.gson.Gson;

import eu.alfred.api.speech.responses.CadeResponse;
import eu.alfred.api.speech.util.RequestResultEntityRecognizer;
import eu.alfred.api.speech.util.RequestResultWHQuery;

/**
 * Created by gilbe on 23.09.2015.
 */
public class Cade {
    private Messenger messenger;

    private class ReadCadeResponse extends Handler {
        private CadeResponse cadeResponse;

        public ReadCadeResponse(CadeResponse cadeResponse) {
            this.cadeResponse = cadeResponse;
        }

        @Override
        public void handleMessage(Message msg) {
            int respCode = msg.what;

            switch (respCode) {
                //Client asked for a list of contacts. Service delivers them with this response Id
                case CadeConstants.GET_CADE_BACKEND_URL_RESPONSE: {
                    Bundle data = msg.getData();
                    Log.i("mssssg", msg.toString());
                    cadeResponse.OnSuccess(data.getString("CADE_BACKEND_URL", ""));
                    break;
                }
            }
        }
    }

    public Cade(Messenger messenger) {
        this.messenger = messenger;
    }

    public void StartListening(String callerName) {
        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.START_LISTENING);
            Bundle data = new Bundle();
            data.putString("callerName", callerName);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void StopListening(String callerName) {
        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.STOP_LISTENING);
            Bundle data = new Bundle();
            data.putString("callerName", callerName);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void SetCadeBackendUrl(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("CADE_BACKEND_URL", url);

        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.SET_CADE_BACKEND_URL);
            msg.setData(bundle);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void GetCadeBackendUrl(CadeResponse cadeResponse) {
        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.GET_CADE_BACKEND_URL);
            if (cadeResponse != null)
                msg.replyTo = new Messenger(new ReadCadeResponse(cadeResponse));
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void TellBatteryStatus(String batteryStatus) {
        Bundle bundle = new Bundle();
        bundle.putString("BATTERY_STATUS", batteryStatus);

        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.TELL_BATTERY_STATUS);
            msg.setData(bundle);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Used to return a value to the server related to an Action
     * Returns true if the message has been sent to the PA
     *
     * @param requestResultWHQuery
     * @return
     */
    public boolean resultAction(boolean requestResultWHQuery) {
        return resultString("RESULT_ACTION", "" + requestResultWHQuery, CadeConstants.RESULT_ACTION);
    }

    /**
     * Used to return a value to the server related to a Validity
     * Returns true if the message has been sent to the PA
     *
     * @param requestResultWHQuery
     * @return
     */
    public boolean resultValidity(boolean requestResultWHQuery) {
        return resultString("RESULT_VALIDITY", "" + requestResultWHQuery, CadeConstants.RESULT_VALIDITY);
    }

    private boolean resultString(String key, String requestResultWHQuery, int cadeConstant) {
        Bundle bundle = new Bundle();
        bundle.putString(key, requestResultWHQuery);
        if (messenger != null) {
            Message msg = Message.obtain(null, cadeConstant);
            msg.setData(bundle);
            try {
                messenger.send(msg);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Used to return a value to the server related to a WHQuery
     * Returns true if the message has been sent to the PA
     *
     * @param requestResultWHQuery
     * @return
     */
    public boolean resultWHQuery(RequestResultWHQuery requestResultWHQuery) {
        String jsonData = "";
        try {
            Gson gson = new Gson();
            jsonData = gson.toJson(requestResultWHQuery);
        } catch (Exception ignored) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("RESULT_WH_QUERY", jsonData);
        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.RESULT_WH_QUERY);
            msg.setData(bundle);
            try {
                messenger.send(msg);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Used to return a value to the server related to an Entity Recognizer
     * Returns true if the message has been sent to the PA
     *
     * @param requestResultEntityRecognizer
     * @return
     */
    public boolean resultEntityRecognizer(RequestResultEntityRecognizer requestResultEntityRecognizer) {
        String jsonData = "";
        try {
            Gson gson = new Gson();
            jsonData = gson.toJson(requestResultEntityRecognizer);
        } catch (Exception ignored) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("RESULT_ENTITY_RECOGNIZER", jsonData);
        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.RESULT_ENTITY_RECOGNIZER);
            msg.setData(bundle);
            try {
                messenger.send(msg);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}