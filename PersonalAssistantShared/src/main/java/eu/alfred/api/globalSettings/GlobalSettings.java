package eu.alfred.api.globalSettings;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import java.io.Serializable;
import java.util.HashMap;

import eu.alfred.api.globalSettings.responses.GlobalSettingsResponse;

/**
 * Created by Gary on 14.04.2016.
 */
public class GlobalSettings {
    private Messenger messenger;
    private Activity activity;

    private class ReadSettingsResponse extends Handler {
        private GlobalSettingsResponse globalSettingsResponse;

        public ReadSettingsResponse(GlobalSettingsResponse globalSettingsResponse) {
            this.globalSettingsResponse = globalSettingsResponse;
        }

        @Override
        public void handleMessage(Message msg) {
            int respCode = msg.what;

            switch (respCode) {
                //Client asked for a list of contacts. Service delivers them with this response Id
                case GlobalSettingsConstants.GET_SETTINGS_RESPONSE: {
                    Bundle data = msg.getData();
                    HashMap<String, Object> settings = (HashMap) data.getSerializable("global_settings");
                    globalSettingsResponse.OnSuccess(settings);
                    break;
                }
            }
        }
    }

    public GlobalSettings(Messenger messenger, Activity activity) {
        this.messenger = messenger;
    }

    public void getGlobalSettings(GlobalSettingsResponse globalSettingsResponse) {
        if (messenger != null) {
            Message msg = Message.obtain(null, GlobalSettingsConstants.GET_SETTINGS);
            if (globalSettingsResponse != null)
                msg.replyTo = new Messenger(new ReadSettingsResponse(globalSettingsResponse));
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setGlobalSetting(String key, Object value) {
        if (messenger != null) {
            Message msg = Message.obtain(null, GlobalSettingsConstants.SET_SETTINGS);

            Bundle bundle = new Bundle();
            bundle.putString("key", key);
            bundle.putSerializable("value", (Serializable) value);
            msg.setData(bundle);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
