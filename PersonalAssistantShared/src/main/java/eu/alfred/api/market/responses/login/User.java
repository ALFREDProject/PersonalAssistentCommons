package eu.alfred.api.market.responses.login;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @Expose
    public String dasUser;
    @Expose
    @SerializedName("isTester")
    public Boolean tester;
    @Expose
    @SerializedName("isApprover")
    public Boolean approver;
    @Expose
    public String authToken;
    @Expose
    public String name;
    @Expose
    public Integer id;

    public static User fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                return new Gson().fromJson(json, User.class);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "User{" +
                "dasUser='" + dasUser + '\'' +
                ", tester=" + tester +
                ", approver=" + approver +
                ", authToken='" + authToken + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}
