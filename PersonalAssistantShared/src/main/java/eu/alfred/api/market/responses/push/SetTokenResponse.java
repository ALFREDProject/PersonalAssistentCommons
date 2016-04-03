package eu.alfred.api.market.responses.push;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import eu.alfred.api.market.responses.apps.Platform;
import eu.alfred.api.market.responses.login.User;

public class SetTokenResponse {

    @Expose
    public long id;
    @Expose
    public Platform platforms;
    @Expose
    public User users;
    @Expose
    public String uuid;
    @Expose
    public long version;

    public static SetTokenResponse fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                return new Gson().fromJson(json, SetTokenResponse.class);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "SetTokenResponse{" +
                "platforms=" + platforms +
                ", users=" + users +
                ", uuid='" + uuid + '\'' +
                ", version=" + version +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}

