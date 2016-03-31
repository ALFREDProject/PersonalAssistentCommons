package eu.alfred.api.market.responses.apps;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AppDetail {

    @Expose
    public String description;
    @Expose
    public List<String> screenshots = new ArrayList<String>();
    @Expose
    public String versionString;
    @Expose
    public String iconUrl;
    @Expose
    public Float rating;
    @Expose
    public String promoUrl;
    @Expose
    public Integer versionNumber;
    @Expose
    public String date;
    @Expose
    public String name;
    @Expose
    public Integer id;
    @Expose
    public Double size;
    @Expose
    public List<Platform> platform = new ArrayList<Platform>();
    @Expose
    public String notificationEmails;
    @Expose
    public Boolean allowed;
    @Expose
    public String supportEmails;
    @Expose
    public String author;
    @Expose
    public String externalUrl;
    @Expose
    public Integer versionId;
    @Expose
    public Boolean externalBinary;
    @Expose
    public String packageName;

    public static AppDetail fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                return new Gson().fromJson(json, AppDetail.class);
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "AppDetail{" +
                "description='" + description + '\'' +
                ", screenshots=" + screenshots +
                ", versionString='" + versionString + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", rating=" + rating +
                ", promoUrl='" + promoUrl + '\'' +
                ", versionNumber=" + versionNumber +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", size=" + size +
                ", platform=" + platform +
                ", notificationEmails='" + notificationEmails + '\'' +
                ", allowed=" + allowed +
                ", supportEmails='" + supportEmails + '\'' +
                ", author='" + author + '\'' +
                ", externalUrl='" + externalUrl + '\'' +
                ", versionId=" + versionId +
                ", externalBinary=" + externalBinary +
                ", packageName='" + packageName + '\'' +
                '}';
    }

    public String toGson() {
        return new Gson().toJson(this);
    }
}
