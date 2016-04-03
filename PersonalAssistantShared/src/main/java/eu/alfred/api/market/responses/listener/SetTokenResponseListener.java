package eu.alfred.api.market.responses.listener;

import eu.alfred.api.market.responses.push.SetTokenResponse;

public interface SetTokenResponseListener {
    public void onSuccess(SetTokenResponse item);

    public void onError(Exception exception);
}
