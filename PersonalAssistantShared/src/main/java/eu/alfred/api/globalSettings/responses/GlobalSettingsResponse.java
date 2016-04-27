package eu.alfred.api.globalSettings.responses;

import java.util.HashMap;

/**
 * Created by Gary on 14.04.2016.
 */
public interface GlobalSettingsResponse {
    public void OnSuccess(HashMap<String, Object> response);
    public void OnError(Exception exception);
}
