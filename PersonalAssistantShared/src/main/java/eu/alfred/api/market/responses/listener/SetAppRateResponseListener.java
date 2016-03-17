package eu.alfred.api.market.responses.listener;

import eu.alfred.api.market.responses.set_app_rate.SetAppRateResponse;

public interface SetAppRateResponseListener {
    public void onSuccess(SetAppRateResponse item);

    public void onError(Exception exception);
}
