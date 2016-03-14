package eu.alfred.api.market.responses.language;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import eu.alfred.api.market.responses.country.Country;

public class LanguageList {

    @Expose
    @SerializedName("languageResponse")
    public List<Language> languages;

    public static LanguageList fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                String jsonObj = "";
                Gson gson = new Gson();
                LanguageList list = new LanguageList();
                list.languages = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = jsonArray.getString(i);
                    Language item = gson.fromJson(jsonObj, Language.class);
                    list.languages.add(item);
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
        if (languages != null) {
            for (int i = 0; i < languages.size(); i++) {
                if (i != 0) {
                    objs += ", " + languages.get(i);
                } else {
                    objs += languages.get(i);
                }
            }
        }
        objs += "]";

        return "LanguageList{" +
                "languages=" + objs +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}
