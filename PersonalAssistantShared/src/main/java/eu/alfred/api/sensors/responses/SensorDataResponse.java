package eu.alfred.api.sensors.responses;

import android.os.Bundle;

/**
 * Created by gilbe on 22.09.2015.
 */
public interface SensorDataResponse {
    void OnSuccess(Bundle data);
    void OnError(Exception exception);
}