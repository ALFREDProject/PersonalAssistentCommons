package eu.alfred.api.market.responses.app_rate;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import eu.alfred.api.market.responses.login.User;

public class Rate {

    @Expose
    public String versionString;
    @Expose
    public Integer score;
    @Expose
    public String subject;
    @Expose
    public Long date;
    @Expose
    public Integer id;
    @Expose
    public String comment;
    @Expose
    public User users;

    public static Rate fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                return new Gson().fromJson(json, Rate.class);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "versionString='" + versionString + '\'' +
                ", score=" + score +
                ", subject='" + subject + '\'' +
                ", date=" + date +
                ", id=" + id +
                ", comment='" + comment + '\'' +
                ", users=" + users +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}

