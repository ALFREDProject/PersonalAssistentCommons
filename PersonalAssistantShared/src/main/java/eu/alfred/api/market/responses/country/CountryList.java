package eu.alfred.api.market.responses.country;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class CountryList {

    @Expose
    @SerializedName("countryResponse")
    public List<Country> countries;

    public static CountryList fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                String jsonObj = "";
                Gson gson = new Gson();
                CountryList list = new CountryList();
                list.countries = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = jsonArray.getString(i);
                    Country item = gson.fromJson(jsonObj, Country.class);
                    list.countries.add(item);
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
        if (countries != null) {
            for (int i = 0; i < countries.size(); i++) {
                if (i != 0) {
                    objs += ", " + countries.get(i);
                } else {
                    objs += countries.get(i);
                }
            }
        }
        objs += "]";

        return "CountryList{" +
                "countries=" + objs +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}
