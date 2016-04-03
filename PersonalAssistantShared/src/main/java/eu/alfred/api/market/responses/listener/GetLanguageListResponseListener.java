package eu.alfred.api.market.responses.listener;

import eu.alfred.api.market.responses.language.LanguageList;

public interface GetLanguageListResponseListener {
    public void onSuccess(LanguageList item);

    public void onError(Exception exception);
}
