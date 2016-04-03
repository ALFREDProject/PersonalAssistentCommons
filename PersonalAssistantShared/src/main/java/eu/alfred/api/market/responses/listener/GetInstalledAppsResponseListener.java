package eu.alfred.api.market.responses.listener;

import eu.alfred.api.market.responses.apps.AppList;

public interface GetInstalledAppsResponseListener {
    public void onSuccess(AppList response);

    public void onError(Exception exception);
}
