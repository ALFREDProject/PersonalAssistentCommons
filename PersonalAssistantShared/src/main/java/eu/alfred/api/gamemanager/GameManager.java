package eu.alfred.api.gamemanager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import eu.alfred.api.gamemanager.responses.GameManagerResponse;

/**
 * Created by Gary on 03.03.2016.
 */
public class GameManager {

    private Messenger messenger;

    private class GameManagerDataResponse extends Handler {
        private GameManagerResponse gameManagerResponse;

        public GameManagerDataResponse(GameManagerResponse gameManagerResponse) {
            this.gameManagerResponse = gameManagerResponse;
        }

        @Override
        public void handleMessage(Message msg) {
            int respCode = msg.what;

            switch (respCode) {
                case GameManagerConstants.START_GAME:
                case GameManagerConstants.STOP_GAME:
                case GameManagerConstants.RESUME_GAME:
                case GameManagerConstants.PAUSE_GAME:


                    String response = null;

                    try {
                        response = msg.getData().getString("response");
                        gameManagerResponse.OnSuccess(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                        gameManagerResponse.OnError(e);
                    }
                    break;
            }
        }
    }

    public GameManager (Messenger messenger) {
        this.messenger = messenger;
    }

    public void stopGame(String userID, GameManagerResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, GameManagerConstants.STOP_GAME);

            if (response != null)
                msg.replyTo = new Messenger(new GameManagerDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void pauseGame(String userID, GameManagerResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, GameManagerConstants.PAUSE_GAME);

            if (response != null)
                msg.replyTo = new Messenger(new GameManagerDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void resumeGame(String userID, GameManagerResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, GameManagerConstants.RESUME_GAME);

            if (response != null)
                msg.replyTo = new Messenger(new GameManagerDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
