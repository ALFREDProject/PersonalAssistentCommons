package eu.alfred.personalassistant.sharedlibrary;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;

import java.util.ArrayList;

import eu.alfred.personalassistant.sharedlibrary.cloudstorage.CloudStorage;
import eu.alfred.personalassistant.sharedlibrary.interfaces.IPersonalAssistantConnection;

/**
 * Created by Daniel on 16.04.2015.
 */

public class PersonalAssistant {

    ArrayList<IPersonalAssistantConnection> listeners = new ArrayList<> ();

    private Context mContext;
    private ServiceConnection mConnection;
    private Messenger mMessenger;
    private CloudStorage mCloudStorage;

    public PersonalAssistant(Context context){
        mContext = context;
    }

    public void Init(){
        mConnection = new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mMessenger = null;
                for (IPersonalAssistantConnection listener : listeners)
                {
                    listener.OnDisconnected();
                }
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // We are connected to the service
                mMessenger = new Messenger(service);
                for (IPersonalAssistantConnection listener : listeners)
                {
                    listener.OnConnected();
                }
            }
        };

        Intent intent = new Intent();
        intent.setPackage("eu.alfred.personalassistant");
        mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public CloudStorage getCloudStorageInstance(){
        if (mCloudStorage == null)
            mCloudStorage = new CloudStorage(mContext);

        return mCloudStorage;
    }

    public void setOnPersonalAssistantConnectionListener (IPersonalAssistantConnection listener)
    {
        // Store the listener object
        this.listeners.add(listener);
    }
}
