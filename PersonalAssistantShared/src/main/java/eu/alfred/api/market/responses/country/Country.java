package eu.alfred.api.market.responses.country;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class Country {

    @Expose
    public String name;
    @Expose
    public Integer id;

    public static Country fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                return new Gson().fromJson(json, Country.class);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}
