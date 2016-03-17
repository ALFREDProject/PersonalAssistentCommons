package eu.alfred.api.market.responses.listener;

public interface SetInstalledAppsResponseListener {
    public void onSuccess(String item);

    public void onError(Exception exception);
}
