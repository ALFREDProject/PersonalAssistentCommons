package eu.alfred.api.market.responses.set_app_rate;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import eu.alfred.api.market.responses.login.User;

public class SetAppRateResponse {

    @Expose
    public String comment;
    @Expose
    public long date;
    @Expose
    public long id;
    @Expose
    public int score;
    @Expose
    public String subject;
    @Expose
    public User users;
    @Expose
    public String versionString;

    public static SetAppRateResponse fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                return new Gson().fromJson(json, SetAppRateResponse.class);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "SetAppRateResponse{" +
                "comment='" + comment + '\'' +
                ", date=" + date +
                ", id=" + id +
                ", score=" + score +
                ", subject='" + subject + '\'' +
                ", users=" + users +
                ", versionString='" + versionString + '\'' +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}

