package eu.alfred.api.market.responses.language;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class Language {

    @Expose
    public String name;
    @Expose
    public Integer id;

    public static Language fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                return new Gson().fromJson(json, Language.class);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Language{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}
