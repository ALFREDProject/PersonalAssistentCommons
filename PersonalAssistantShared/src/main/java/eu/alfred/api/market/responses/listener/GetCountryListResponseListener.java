package eu.alfred.api.market.responses.listener;

import eu.alfred.api.market.responses.country.CountryList;

public interface GetCountryListResponseListener {
    public void onSuccess(CountryList item);

    public void onError(Exception exception);
}
