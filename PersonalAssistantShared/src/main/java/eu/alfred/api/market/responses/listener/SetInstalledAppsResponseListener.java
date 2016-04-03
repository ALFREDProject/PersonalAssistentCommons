package eu.alfred.api.market.responses.listener;

import eu.alfred.api.market.responses.set_installed_apps.Datum;

public interface SetInstalledAppsResponseListener {
    public void onSuccess(Datum response);

    public void onError(Exception exception);
}
