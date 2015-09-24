package eu.alfred.api.speech;

import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import eu.alfred.api.storage.StorageConstants;

/**
 * Created by gilbe on 23.09.2015.
 */
public class Cade {
    private Messenger messenger;

    public Cade(Messenger messenger){
        this.messenger = messenger;
    }

    public void InitiateSpeechRecognition(){
        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.INITIATE_SPEECH_RECOGNITION);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}