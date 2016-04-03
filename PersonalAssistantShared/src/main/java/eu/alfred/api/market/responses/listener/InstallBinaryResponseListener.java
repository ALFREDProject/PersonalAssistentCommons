package eu.alfred.api.market.responses.listener;

public interface InstallBinaryResponseListener {
    public void onSuccess(String binaryString);

    public void onError(Exception exception);
}
