package eu.alfred.api.market.responses.listener;

import eu.alfred.api.market.responses.apps.AppList;

public interface GetAppListResponseListener {
    public void onSuccess(AppList item);

    public void onError(Exception exception);
}
