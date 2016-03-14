package eu.alfred.api.market.responses.category;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class CategoryList {

    @Expose
    @SerializedName("categoryResponse")
    public List<Category> categories;

    public static CategoryList fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                String jsonObj = "";
                Gson gson = new Gson();
                CategoryList list = new CategoryList();
                list.categories = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = jsonArray.getString(i);
                    Category item = gson.fromJson(jsonObj, Category.class);
                    list.categories.add(item);
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
        if (categories != null) {
            for (int i = 0; i < categories.size(); i++) {
                if (i != 0) {
                    objs += ", " + categories.get(i);
                } else {
                    objs += categories.get(i);
                }
            }
        }
        objs += "]";

        return "CategoryList{" +
                "categories=" + objs +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}
