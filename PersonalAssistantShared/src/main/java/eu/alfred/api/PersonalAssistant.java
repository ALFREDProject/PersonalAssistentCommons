package eu.alfred.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;

import java.util.ArrayList;

/**
 * Created by Daniel on 21.04.2015.
 */
public class PersonalAssistant {

    ArrayList<PersonalAssistantConnection> listeners = new ArrayList<> ();

    private Context context;
    private ServiceConnection serviceConnection;
    private Messenger messenger;

    public Context getContext(){
        return this.context;
    }

    public ServiceConnection getServiceConnection(){
        return this.serviceConnection;
    }

    public Messenger getMessenger(){
        return this.messenger;
    }

    public PersonalAssistant(Context context){
        this.context = context;
    }

    public void Init(){
        serviceConnection = new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {
                messenger = null;
                for (PersonalAssistantConnection listener : listeners)
                {
                    listener.OnDisconnected();
                }
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // We are connected to the service
                messenger = new Messenger(service);
                for (PersonalAssistantConnection listener : listeners)
                {
                    listener.OnConnected();
                }
            }
        };

        Intent intent = new Intent();
        intent.setPackage("eu.alfred.personalassistant");
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }



    public void setOnPersonalAssistantConnectionListener (PersonalAssistantConnection listener) {
        // Store the listener object
        this.listeners.add(listener);
    }
}