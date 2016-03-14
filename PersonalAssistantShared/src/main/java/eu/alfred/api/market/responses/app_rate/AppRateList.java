package eu.alfred.api.market.responses.app_rate;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class AppRateList {

    @Expose
    @SerializedName("appRateResponse")
    public List<AppRate> appRates;

    public static AppRateList fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                String jsonObj = "";
                Gson gson = new Gson();
                AppRateList appRateList = new AppRateList();
                appRateList.appRates = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = jsonArray.getString(i);
                    AppRate appRate = gson.fromJson(jsonObj, AppRate.class);
                    appRateList.appRates.add(appRate);
                }
                return appRateList;
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String objs = "[";
        if (appRates != null) {
            for (int i = 0; i < appRates.size(); i++) {
                if (i != 0) {
                    objs += ", " + appRates.get(i);
                } else {
                    objs += appRates.get(i);
                }
            }
        }
        objs += "]";

        return "AppRateList{" +
                "appRates=" + objs +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}

