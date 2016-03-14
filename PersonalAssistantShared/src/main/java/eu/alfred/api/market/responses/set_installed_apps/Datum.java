package eu.alfred.api.market.responses.set_installed_apps;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class Datum {

    @Expose
    public Integer id;
    @Expose
    public String result;

    public static Datum fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                return new Gson().fromJson(json, Datum.class);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Datum{" +
                "id=" + id +
                ", result='" + result + '\'' +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}
