package eu.alfred.api.speech;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import eu.alfred.api.speech.responses.CadeResponse;

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

    public void resultAction(String resultWHQuery) {
        result("RESULT_ACTION", resultWHQuery, CadeConstants.RESULT_ACTION);
    }

    public void resultWHQuery(String resultWHQuery) {
        result("RESULT_WH_QUERY", resultWHQuery, CadeConstants.RESULT_WH_QUERY);
    }

    public void resultValidity(String resultWHQuery) {
        result("RESULT_VALIDITY", resultWHQuery, CadeConstants.RESULT_VALIDITY);
    }

    public void resultEntityRecognizer(String resultWHQuery) {
        result("RESULT_ENTITY_RECOGNIZER", resultWHQuery, CadeConstants.RESULT_ENTITY_RECOGNIZER);
    }

    private void result(String key, String resultWHQuery, int cadeConstant) {
        Bundle bundle = new Bundle();
        bundle.putString(key, resultWHQuery);
        if (messenger != null) {
            Message msg = Message.obtain(null, cadeConstant);
            msg.setData(bundle);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}