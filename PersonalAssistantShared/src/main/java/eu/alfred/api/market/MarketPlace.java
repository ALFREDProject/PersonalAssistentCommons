package eu.alfred.api.market;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import org.json.JSONArray;
import org.json.JSONObject;

import eu.alfred.api.market.device.DeviceUuidFactory;
import eu.alfred.api.market.responses.MarketplaceResponse;
import eu.alfred.api.market.responses.app_rate.AppRateList;
import eu.alfred.api.market.responses.apps.AppDetail;
import eu.alfred.api.market.responses.apps.AppList;
import eu.alfred.api.market.responses.category.CategoryList;
import eu.alfred.api.market.responses.country.CountryList;
import eu.alfred.api.market.responses.language.LanguageList;
import eu.alfred.api.market.responses.listener.GetAppDetailsResponseListener;
import eu.alfred.api.market.responses.listener.GetAppListResponseListener;
import eu.alfred.api.market.responses.listener.GetAppRateListResponseListener;
import eu.alfred.api.market.responses.listener.GetCategoryListResponseListener;
import eu.alfred.api.market.responses.listener.GetCountryListResponseListener;
import eu.alfred.api.market.responses.listener.GetLanguageListResponseListener;
import eu.alfred.api.market.responses.listener.InstallBinaryResponseListener;
import eu.alfred.api.market.responses.listener.LoginResponseListener;
import eu.alfred.api.market.responses.listener.SetAppRateResponseListener;
import eu.alfred.api.market.responses.listener.SetTokenResponseListener;
import eu.alfred.api.market.responses.login.User;
import eu.alfred.api.market.responses.push.SetTokenResponse;
import eu.alfred.api.market.responses.set_app_rate.SetAppRateResponse;

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
                case MarketPlaceConstants.INSTALL_BINARY_RESPONSE:
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


    public void login(String user, String password, final LoginResponseListener listener) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.LOGIN);

            MarketplaceResponse marketplaceResponse = new MarketplaceResponse() {
                @Override
                public void OnSuccess(JSONObject response) {
                }

                @Override
                public void OnSuccess(JSONArray response) {
                }

                @Override
                public void OnSuccess(String response) {
                    User item = User.fromJson(response);
                    if (listener != null) {
                        listener.onSuccess(item);
                    }
                }

                @Override
                public void OnError(Exception exception) {
                    if (listener != null) {
                        listener.onError(exception);
                    }
                }
            };
            msg.replyTo = new Messenger(new MarketPlaceDataResponse(marketplaceResponse));

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

    public void getCategoryList(final GetCategoryListResponseListener listener) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_CATEGORY_LIST);

            MarketplaceResponse marketplaceResponse = new MarketplaceResponse() {
                @Override
                public void OnSuccess(JSONObject response) {
                }

                @Override
                public void OnSuccess(JSONArray response) {
                }

                @Override
                public void OnSuccess(String response) {
                    CategoryList item = CategoryList.fromJson(response);
                    if (listener != null) {
                        listener.onSuccess(item);
                    }
                }

                @Override
                public void OnError(Exception exception) {
                    if (listener != null) {
                        listener.onError(exception);
                    }
                }
            };
            msg.replyTo = new Messenger(new MarketPlaceDataResponse(marketplaceResponse));

            Bundle data = new Bundle();
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getCountryList(final GetCountryListResponseListener listener) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_COUNTRY_LIST);

            MarketplaceResponse marketplaceResponse = new MarketplaceResponse() {
                @Override
                public void OnSuccess(JSONObject response) {
                }

                @Override
                public void OnSuccess(JSONArray response) {
                }

                @Override
                public void OnSuccess(String response) {
                    CountryList item = CountryList.fromJson(response);
                    if (listener != null) {
                        listener.onSuccess(item);
                    }
                }

                @Override
                public void OnError(Exception exception) {
                    if (listener != null) {
                        listener.onError(exception);
                    }
                }
            };
            msg.replyTo = new Messenger(new MarketPlaceDataResponse(marketplaceResponse));

            Bundle data = new Bundle();
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getLanguageList(final GetLanguageListResponseListener listener) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_LANGUAGE_LIST);

            MarketplaceResponse marketplaceResponse = new MarketplaceResponse() {
                @Override
                public void OnSuccess(JSONObject response) {
                }

                @Override
                public void OnSuccess(JSONArray response) {
                }

                @Override
                public void OnSuccess(String response) {
                    LanguageList item = LanguageList.fromJson(response);
                    if (listener != null) {
                        listener.onSuccess(item);
                    }
                }

                @Override
                public void OnError(Exception exception) {
                    if (listener != null) {
                        listener.onError(exception);
                    }
                }
            };
            msg.replyTo = new Messenger(new MarketPlaceDataResponse(marketplaceResponse));

            Bundle data = new Bundle();
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    public void getAppDetails(String id, final GetAppDetailsResponseListener listener) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_APP_DETAIL);

            MarketplaceResponse marketplaceResponse = new MarketplaceResponse() {
                @Override
                public void OnSuccess(JSONObject response) {
                }

                @Override
                public void OnSuccess(JSONArray response) {
                }

                @Override
                public void OnSuccess(String response) {
                    AppDetail item = AppDetail.fromJson(response);
                    if (listener != null) {
                        listener.onSuccess(item);
                    }
                }

                @Override
                public void OnError(Exception exception) {
                    if (listener != null) {
                        listener.onError(exception);
                    }
                }
            };
            msg.replyTo = new Messenger(new MarketPlaceDataResponse(marketplaceResponse));

            Bundle data = new Bundle();
            try {
                data.putLong("p_id", Long.parseLong(id));
            } catch (Exception ignored) {
                data.putLong("p_id", -1);
            }
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
                           final GetAppListResponseListener listener) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_APP_LIST);

            MarketplaceResponse marketplaceResponse = new MarketplaceResponse() {
                @Override
                public void OnSuccess(JSONObject response) {
                }

                @Override
                public void OnSuccess(JSONArray response) {
                }

                @Override
                public void OnSuccess(String response) {
                    AppList item = AppList.fromJson(response);
                    if (listener != null) {
                        listener.onSuccess(item);
                    }
                }

                @Override
                public void OnError(Exception exception) {
                    if (listener != null) {
                        listener.onError(exception);
                    }
                }
            };
            msg.replyTo = new Messenger(new MarketPlaceDataResponse(marketplaceResponse));

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

    public void getAppRateList(String id, final GetAppRateListResponseListener listener) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.GET_APP_RATE_LIST);

            MarketplaceResponse marketplaceResponse = new MarketplaceResponse() {
                @Override
                public void OnSuccess(JSONObject response) {
                }

                @Override
                public void OnSuccess(JSONArray response) {
                }

                @Override
                public void OnSuccess(String response) {
                    AppRateList item = AppRateList.fromJson(response);
                    if (listener != null) {
                        listener.onSuccess(item);
                    }
                }

                @Override
                public void OnError(Exception exception) {
                    if (listener != null) {
                        listener.onError(exception);
                    }
                }
            };
            msg.replyTo = new Messenger(new MarketPlaceDataResponse(marketplaceResponse));

            Bundle data = new Bundle();

            try {
                data.putLong("p_id", Long.parseLong(id));
            } catch (Exception var7) {
                data.putLong("p_id", -1L);
            }

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
                           final SetAppRateResponseListener listener) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.SET_APP_RATE);

            MarketplaceResponse marketplaceResponse = new MarketplaceResponse() {
                @Override
                public void OnSuccess(JSONObject response) {
                }

                @Override
                public void OnSuccess(JSONArray response) {
                }

                @Override
                public void OnSuccess(String response) {
                    SetAppRateResponse item = SetAppRateResponse.fromJson(response);
                    if (listener != null) {
                        listener.onSuccess(item);
                    }
                }

                @Override
                public void OnError(Exception exception) {
                    if (listener != null) {
                        listener.onError(exception);
                    }
                }
            };
            msg.replyTo = new Messenger(new MarketPlaceDataResponse(marketplaceResponse));

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

    public void setToken(String token, final SetTokenResponseListener listener) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.SET_TOKEN);

            MarketplaceResponse marketplaceResponse = new MarketplaceResponse() {
                @Override
                public void OnSuccess(JSONObject response) {
                }

                @Override
                public void OnSuccess(JSONArray response) {
                }

                @Override
                public void OnSuccess(String response) {
                    SetTokenResponse item = SetTokenResponse.fromJson(response);
                    if (listener != null) {
                        listener.onSuccess(item);
                    }
                }

                @Override
                public void OnError(Exception exception) {
                    if (listener != null) {
                        listener.onError(exception);
                    }
                }
            };
            msg.replyTo = new Messenger(new MarketPlaceDataResponse(marketplaceResponse));

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

    public void installBinary(long appId, String versionId, final InstallBinaryResponseListener listener) {
        if (messenger != null) {
            Message msg = Message.obtain(null, MarketPlaceConstants.SET_TOKEN);

            MarketplaceResponse marketplaceResponse = new MarketplaceResponse() {
                @Override
                public void OnSuccess(JSONObject response) {
                }

                @Override
                public void OnSuccess(JSONArray response) {
                }

                @Override
                public void OnSuccess(String binaryString) {
                    if (listener != null) {
                        listener.onSuccess();
                    }
                }

                @Override
                public void OnError(Exception exception) {
                    if (listener != null) {
                        listener.onError(exception);
                    }
                }
            };
            msg.replyTo = new Messenger(new MarketPlaceDataResponse(marketplaceResponse));

            Bundle data = new Bundle();
            data.putLong("p_appId", appId);
            data.putString("p_versionId", versionId);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
