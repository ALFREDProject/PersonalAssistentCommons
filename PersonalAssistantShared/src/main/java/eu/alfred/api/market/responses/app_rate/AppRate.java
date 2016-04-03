package eu.alfred.api.market.responses.app_rate;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class AppRate {

    @Expose
    public Integer userId;
    @Expose
    public String versionString;
    @Expose
    public String text;
    @Expose
    public String dateCreation;
    @Expose
    public String userFullName;
    @Expose
    public String title;
    @Expose
    public Integer rate;
    @Expose
    public String userName;
    @Expose
    public Integer id;

    public static AppRate fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                return new Gson().fromJson(json, AppRate.class);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "AppRate{" +
                "userId=" + userId +
                ", versionString='" + versionString + '\'' +
                ", text='" + text + '\'' +
                ", dateCreation='" + dateCreation + '\'' +
                ", userFullName='" + userFullName + '\'' +
                ", title='" + title + '\'' +
                ", rate=" + rate +
                ", userName='" + userName + '\'' +
                ", id=" + id +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}

