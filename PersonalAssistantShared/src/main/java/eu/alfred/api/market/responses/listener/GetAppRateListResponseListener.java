package eu.alfred.api.market.responses.listener;

import eu.alfred.api.market.responses.app_rate.AppRateList;

public interface GetAppRateListResponseListener {
    public void onSuccess(AppRateList item);

    public void onError(Exception exception);
}
