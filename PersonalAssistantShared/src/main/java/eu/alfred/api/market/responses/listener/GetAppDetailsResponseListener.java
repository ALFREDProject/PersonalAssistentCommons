package eu.alfred.api.market.responses.listener;

import eu.alfred.api.market.responses.apps.AppDetail;

public interface GetAppDetailsResponseListener {
    public void onSuccess(AppDetail item);

    public void onError(Exception exception);
}
