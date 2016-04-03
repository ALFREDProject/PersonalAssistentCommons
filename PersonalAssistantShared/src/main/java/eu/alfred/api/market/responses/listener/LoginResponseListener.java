package eu.alfred.api.market.responses.listener;

import eu.alfred.api.market.responses.login.User;

public interface LoginResponseListener {
    public void onSuccess(User user);

    public void onError(Exception exception);
}
