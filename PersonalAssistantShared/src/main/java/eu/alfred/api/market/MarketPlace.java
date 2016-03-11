package eu.alfred.api.market;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import eu.alfred.api.market.device.DeviceUuidFactory;
import eu.alfred.api.market.responses.MarketplaceResponse;

/**
 * Created by Gary on 03.03.2016.
 */
public class MarketPlace {

    private Messenger messenger;
    private Context context;

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
                    handleResponse(msg, marketplaceDataResponse);
                    break;
                case MarketPlaceConstants.GET_APP_LIST_RESPONSE:
                    handleResponse(msg, marketplaceDataResponse);
                    break;
                case MarketPlaceConstants.GET_APP_RATE_LIST_RESPONSE:
                    handleResponse(msg, marketplaceDataResponse);
                    break;
                case MarketPlaceConstants.GET_CATEGORY_LIST_RESPONSE:
                    handleResponse(msg, marketplaceDataResponse);
                    break;
                case MarketPlaceConstants.GET_COUNTRY_LIST_RESPONSE:
                    handleResponse(msg, marketplaceDataResponse);
                    break;
                case MarketPlaceConstants.GET_LANGUAGE_LIST_RESPONSE:
                    handleResponse(msg, marketplaceDataResponse);
                    break;
                case MarketPlaceConstants.LOGIN_RESPONSE:
                    handleResponse(msg, marketplaceDataResponse);
                    break;
                case MarketPlaceConstants.AUTO_LOGIN_RESPONSE:
                    handleResponse(msg, marketplaceDataResponse);
                    break;
                case MarketPlaceConstants.SET_APP_RATE_RESPONSE:
                    handleResponse(msg, marketplaceDataResponse);
                    break;
                case MarketPlaceConstants.SET_INSTALLED_APPS_RESPONSE:
                    handleResponse(msg, marketplaceDataResponse);
                    break;
                case MarketPlaceConstants.SET_TOKEN_RESPONSE:
                    handleResponse(msg, marketplaceDataResponse);
                    break;
            }
        }
    }

    private void handleResponse(Message msg, MarketplaceResponse marketplaceDataResponse) {
        String response = null;

        try {
            response = msg.getData().getString(MarketPlaceConstants.EXTRAS_JSON);
            marketplaceDataResponse.OnSuccess(response);
        } catch (Exception e) {
            e.printStackTrace();
            marketplaceDataResponse.OnError(e);
        }
    }


    public MarketPlace(Messenger messenger, Context context) {
        this.messenger = messenger;
        this.context = context;
    }


    public void login(String user, String password, MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.LOGIN);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            String deviceId = "unkownDeviceId";
            try {
                deviceId = "" + new DeviceUuidFactory(context).getDeviceUuid().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String versionOS = "" + Build.VERSION.SDK_INT;
            String platform = "Android";

            Bundle data = new Bundle();
            data.putString("p_user", user);
            data.putString("p_password", password);
            data.putString("p_deviceId", deviceId);
            data.putString("p_versionOS", versionOS);
            data.putString("p_platform", platform);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    public void getAppDetails(String id, MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_APP_DETAIL);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            data.putString("p_id", id);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getAppList(AppListType appListType,
                           String words,
                           String name,
                           int start,
                           int elements,
                           String sorting,
                           int countryId,
                           int categoryId,
                           int languageId,
                           boolean hasPromoImage,
                           MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_APP_LIST);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            /*
                DEFAULT(0, "default"),
                INSTALLED(1, "installed"),
                UPDATABLE(2, "updatable"),
                MOST_POPULAR(3, "most popular"),
                LATEST(4, "latest"),
                SEARCH(5, "search");
             */
            data.putString("p_appListType", appListType != null ? "" + appListType : "" + AppListType.DEFAULT);
            data.putString("p_words", words);
            data.putString("p_name", name);
            data.putInt("p_start", start);
            data.putInt("p_elements", elements);
            data.putString("p_sorting", sorting);
            data.putInt("p_countryId", countryId);
            data.putInt("p_categoryId", categoryId);
            data.putInt("p_languageId", languageId);
            data.putBoolean("p_hasPromoImage", hasPromoImage);

            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getAppRateList(String id, MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_APP_RATE_LIST);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            data.putString("p_id", id);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getCategoryList(MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_CATEGORY_LIST);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getCountryList(MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_COUNTRY_LIST);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getLanguageList(MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_LANGUAGE_LIST);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void autoLogin(MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.AUTO_LOGIN);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAppRate(Integer id,
                           String title,
                           int rate,
                           int author,
                           String body,
                           int versionNumber,
                           MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.SET_APP_RATE);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            data.putInt("p_id", id);
            data.putString("p_title", title);
            data.putInt("p_rate", rate);
            data.putInt("p_author", author);
            data.putString("p_body", body);
            data.putInt("p_versionNumber", versionNumber);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setInstalledApps(String jsonArray, MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.SET_INSTALLED_APPS);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            data.putString("p_jsonArray", jsonArray);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setToken(String token, MarketplaceResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.SET_TOKEN);

            if (response != null)
                msg.replyTo = new Messenger(new MarketPlaceDataResponse(response));

            Bundle data = new Bundle();
            data.putString("p_token", token);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
