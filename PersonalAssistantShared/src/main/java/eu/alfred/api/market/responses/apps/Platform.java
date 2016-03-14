package eu.alfred.api.market.responses.apps;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class Platform {

    @Expose
    public Os os;
    @Expose
    public String name;
    @Expose
    public Integer id;

    public static Platform fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                return new Gson().fromJson(json, Platform.class);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "os=" + os +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}
