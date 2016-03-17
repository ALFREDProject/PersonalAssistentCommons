package eu.alfred.api.market.responses.listener;

import eu.alfred.api.market.responses.apps.AppDetail;

public interface InstallBinaryResponseListener {
    public void onSuccess();

    public void onError(Exception exception);
}
