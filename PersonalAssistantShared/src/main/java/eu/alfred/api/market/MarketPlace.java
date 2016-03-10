package eu.alfred.api.market;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import eu.alfred.api.market.responses.MarketplaceResponse;

/**
 * Created by Gary on 03.03.2016.
 */
public class MarketPlace {

    private Messenger messenger;

    private class MarketPlaceDataResponse extends Handler {
        private MarketplaceResponse marketplaceDataResponse;

        public MarketPlaceDataResponse(MarketplaceResponse marketplaceDataResponse) {
            this.marketplaceDataResponse = marketplaceDataResponse;
        }

        @Override
        public void handleMessage(Message msg) {
            int respCode = msg.what;

            switch (respCode) {
                case MarketPlaceConstants.GET_APP_DETAIL_RESPONSE:
                case MarketPlaceConstants.GET_APP_LIST_RESPONSE:
                case MarketPlaceConstants.GET_APP_RATE_LIST_RESPONSE:
                case MarketPlaceConstants.GET_CATEGORY_LIST_RESPONSE:
                case MarketPlaceConstants.GET_COUNTRY_LIST_RESPONSE:
                case MarketPlaceConstants.GET_LANGUAGE_LIST_RESPONSE:
                case MarketPlaceConstants.LOGIN_RESPONSE:
                case MarketPlaceConstants.AUTO_LOGIN_RESPONSE:
                case MarketPlaceConstants.SET_APP_RATE_RESPONSE:
                case MarketPlaceConstants.SET_INSTALLED_APPS_RESPONSE:
                case MarketPlaceConstants.SET_TOKEN_RESPONSE:

                    String response = null;

                    try {
                        response = msg.getData().getString(MarketPlaceConstants.EXTRAS_JSON);
                        marketplaceDataResponse.OnSuccess(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                        marketplaceDataResponse.OnError(e);
                    }
                    break;
            }
        }
    }


    public MarketPlace(Messenger messenger) {
        this.messenger = messenger;
    }

    public void getAppDetails(String userID, MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_APP_DETAIL);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getAppList(String userID, MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_APP_LIST);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getAppRateList(String userID, MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_APP_RATE_LIST);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getCategoryList(String userID, MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_CATEGORY_LIST);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getCountryList(String userID, MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_COUNTRY_LIST);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getLanguageList(String userID, MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_LANGUAGE_LIST);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void login(String userID, MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.LOGIN);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void autoLogin(String userID, MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.AUTO_LOGIN);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAppRate(String userID, MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.SET_APP_RATE);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setInstalledApps(String userID, MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.SET_INSTALLED_APPS);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setToken(String userID, MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.SET_TOKEN);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
