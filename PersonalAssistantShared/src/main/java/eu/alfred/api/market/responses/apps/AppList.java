package eu.alfred.api.market.responses.apps;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import eu.alfred.api.market.responses.app_rate.AppRate;

public class AppList {

    @Expose
    @SerializedName("app")
    public List<App> apps;

    public static AppList fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                String jsonObj = "";
                Gson gson = new Gson();
                AppList list = new AppList();
                list.apps = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = jsonArray.getString(i);
                    App item = gson.fromJson(jsonObj, App.class);
                    list.apps.add(item);
                }
                return list;
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String objs = "[";
        if (apps != null) {
            for (int i = 0; i < apps.size(); i++) {
                if (i != 0) {
                    objs += ", " + apps.get(i);
                } else {
                    objs += apps.get(i);
                }
            }
        }
        objs += "]";

        return "AppList{" +
                "apps=" + objs +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}
