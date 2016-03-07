package eu.alfred.api.sensors;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import eu.alfred.api.sensors.responses.SensorDataResponse;

/**
 * Created by gilbe on 21.09.2015.
 */
public class SAFDataFacade {
    private Messenger messenger;

    private class SensorLiveDataResponse extends Handler {
        private SensorDataResponse sensorDataResponse;

        public SensorLiveDataResponse(SensorDataResponse sensorDataResponse){
            this.sensorDataResponse = sensorDataResponse;
        }

        @Override
        public void handleMessage(Message msg){
            int respCode = msg.what;

            switch (respCode) {
                case SAFFacadeConstants.READ_TEMP_DATA_RESPONSE:
                case SAFFacadeConstants.READ_ACC_DATA_RESPONSE:
                case SAFFacadeConstants.READ_HR_DATA_RESPONSE:
                case SAFFacadeConstants.READ_RR_DATA_RESPONSE:
                    Bundle response = new Bundle();

                    response.putByteArray("sensorData", msg.getData().getByteArray("sensorData"));
                    response.putLong("ts", msg.getData().getLong("ts"));
                    sensorDataResponse.OnSuccess(response);
                    break;

            }
        }
    }

    Byte[] toObjects(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];

        int i = 0;
        for (byte b : bytesPrim) bytes[i++] = b; // Autoboxing

        return bytes;
    }

    public void GetTempData(String sensorName, SensorDataResponse response) throws IllegalArgumentException {
        if (messenger != null) {
            Message msg = Message.obtain(null, SAFFacadeConstants.READ_TEMP_DATA);

            if (response != null)
                msg.replyTo = new Messenger(new SensorLiveDataResponse(response));

            Bundle data = new Bundle();
            data.putString("sensor", sensorName);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void GetAccData(String sensorName, SensorDataResponse response) throws IllegalArgumentException {
        if (messenger != null) {
            Message msg = Message.obtain(null, SAFFacadeConstants.READ_ACC_DATA);

            if (response != null)
                msg.replyTo = new Messenger(new SensorLiveDataResponse(response));

            Bundle data = new Bundle();
            data.putString("sensor", sensorName);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void GetHrData(String sensorName, SensorDataResponse response) throws IllegalArgumentException {
        if (messenger != null) {
            Message msg = Message.obtain(null, SAFFacadeConstants.READ_HR_DATA);

            if (response != null)
                msg.replyTo = new Messenger(new SensorLiveDataResponse(response));

            Bundle data = new Bundle();
            data.putString("sensor", sensorName);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void GetRrData(String sensorName, SensorDataResponse response) throws IllegalArgumentException {
        if (messenger != null) {
            Message msg = Message.obtain(null, SAFFacadeConstants.READ_RR_DATA);

            if (response != null)
                msg.replyTo = new Messenger(new SensorLiveDataResponse(response));

            Bundle data = new Bundle();
            data.putString("sensor", sensorName);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setHRSelected(boolean selected) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(SAFFacadeConstants.SELECTED_HR, selected);

        if (messenger != null) {
            Message msg = Message.obtain(null, SAFFacadeConstants.SET_SELECTED_HR);
            msg.setData(bundle);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public SAFDataFacade(Messenger messenger) throws IllegalArgumentException {
        this.messenger = messenger;

        if (messenger == null) throw new IllegalArgumentException("messenger should not be null!");

    }
}