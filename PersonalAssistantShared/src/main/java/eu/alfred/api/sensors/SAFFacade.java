package eu.alfred.api.sensors;

import android.os.Messenger;

import org.alfred.saf.SAFException;
import org.alfred.saf.SensorDriver;
import org.alfred.saf.SensorListener;

/**
 * Created by gilbe on 21.09.2015.
 */
public class SAFFacade {
    private Messenger messenger;
    private org.alfred.saf.SAFFacade safFacade;

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