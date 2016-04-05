package eu.alfred.api.market.responses.apps;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class App {

    @Expose
    public Integer versionNumber;
    @Expose
    public Boolean allowed;
    @Expose
    public String supportEmails;
    @Expose
    public String versionString;
    @Expose
    public String iconUrl;
    @Expose
    public Float rating;
    @Expose
    public String promoUrl;
    @Expose
    public String author;
    @Expose
    public String externalUrl;
    @Expose
    public Integer versionId;
    @Expose
    public Boolean externalBinary;
    @Expose
    public List<Platform> platform = new ArrayList<Platform>();
    @Expose
    public String notificationEmails;
    @Expose
    public String packageName;
    @Expose
    public String date;
    @Expose
    public String name;
    @Expose
    public Integer id;

    public static App fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                return new Gson().fromJson(json, App.class);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "App{" +
                "versionNumber=" + versionNumber +
                ", allowed=" + allowed +
                ", supportEmails='" + supportEmails + '\'' +
                ", versionString='" + versionString + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", rating=" + rating +
                ", promoUrl='" + promoUrl + '\'' +
                ", author='" + author + '\'' +
                ", externalUrl='" + externalUrl + '\'' +
                ", versionId=" + versionId +
                ", externalBinary=" + externalBinary +
                ", platform=" + platform +
                ", notificationEmails='" + notificationEmails + '\'' +
                ", packageName='" + packageName + '\'' +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}
