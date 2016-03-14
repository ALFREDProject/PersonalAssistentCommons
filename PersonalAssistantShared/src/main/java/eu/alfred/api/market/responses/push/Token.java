package eu.alfred.api.market.responses.push;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import eu.alfred.api.market.responses.apps.Platform;
import eu.alfred.api.market.responses.login.User;

public class Token {

    @Expose
    public String uuid;
    @Expose
    public Platform platforms;
    @Expose
    public User users;
    @Expose
    public Integer version;
    @Expose
    public Integer id;

    public static Token fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                return new Gson().fromJson(json, Token.class);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Token{" +
                "uuid='" + uuid + '\'' +
                ", platforms=" + platforms +
                ", users=" + users +
                ", version=" + version +
                ", id=" + id +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}

