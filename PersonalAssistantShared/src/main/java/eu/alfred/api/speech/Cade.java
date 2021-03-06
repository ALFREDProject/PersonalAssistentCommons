package eu.alfred.api.speech;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void SetLanguage(String url) {
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

    public void sendActionResult(boolean state) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(CadeConstants.ACTION_STATE, state);

        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.RESULT_ACTION);
            msg.setData(bundle);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendWHQueryResult(List<Map<String, String>> resultList) {
        Bundle bundle = new Bundle();

        ArrayList<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();

        for(Map<String, String> map : resultList) {
            HashMap<String, String> temp = new HashMap<>(map);
            result.add(temp);
        }
        bundle.putSerializable(CadeConstants.WHQUERY_LIST, result);

        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.RESULT_WHQUERY);
            msg.setData(bundle);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendValidityResult(boolean state) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(CadeConstants.VALIDITY_STATE, state);

        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.RESULT_VALIDITY);
            msg.setData(bundle);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendEntityRecognizerResult(List<Map<String,String>> resultList) {
        Bundle bundle = new Bundle();

        ArrayList<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();

        for(Map<String, String> map : resultList) {
            HashMap<String, String> temp = new HashMap<>(map);
            result.add(temp);
        }

        bundle.putSerializable(CadeConstants.ENTITYRECOGNIZER_LIST, result);

        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.RESULT_ENTITYRECOGNIZER);
            msg.setData(bundle);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}