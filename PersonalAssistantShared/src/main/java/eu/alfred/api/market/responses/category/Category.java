package eu.alfred.api.market.responses.category;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class Category {

    @Expose
    public String name;
    @Expose
    public Integer id;
    @Expose
    public String image;

    public static Category fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                return new Gson().fromJson(json, Category.class);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", image='" + image + '\'' +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}
