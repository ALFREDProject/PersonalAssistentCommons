package eu.alfred.api.market.responses.listener;

import eu.alfred.api.market.responses.apps.AppList;
import eu.alfred.api.market.responses.set_installed_apps.Datum;

public interface GetInstalledAppsResponseListener {
    public void onSuccess(AppList response);

    public void onError(Exception exception);
}
