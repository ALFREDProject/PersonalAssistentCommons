package eu.alfred.api.sensors;

import android.os.Messenger;
import eu.alfred.api.sensors.exceptions.SAFException;

import android.util.Log;

/**
 * Created by gilbe on 21.09.2015.
 */
public class SAFFacade {
    private Messenger messenger;
    

    public SAFFacade(Messenger messenger) throws SAFException {
        this.messenger = messenger;
    }

    public void registerDriver(SensorDriver driver) throws SAFException {

    }

    public void unregisterDriver(SensorDriver driver) throws SAFException {

    }

    public void registerListener(String url, SensorListener listener) throws SAFException {

    }

    public void unRegisterListener(String url, SensorListener listener) throws SAFException {

    }
}