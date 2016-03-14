package eu.alfred.api.market.responses.apps;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class Os {

    @Expose
    public String name;
    @Expose
    public Integer id;
    @Expose
    public String extension;

    public static Os fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                return new Gson().fromJson(json, Os.class);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Os{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", extension='" + extension + '\'' +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}
