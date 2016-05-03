package eu.alfred.api.personalization.helper.eventrecommendation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import eu.alfred.api.personalization.model.eventrecommendation.EventRecommendationResponse;

/**
 * Created by thardes on 02/05/2016.
 */
public class EventHelper {
    private static Gson g;

    private static void prepareGsonWeb()
    {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
                Date d = new Date(json.getAsLong());
                return d;
            }
        });
        g = builder.create();
    }


    private static void prepareGsonLocal()
    {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        g = builder.create();
    }

    public static ArrayList<EventRecommendationResponse> jsonToEventList(String json)
    {
        prepareGsonWeb();
        if(json.equals(""))
            return new ArrayList<>();

        EventRecommendationResponse[] r =g.fromJson(json,EventRecommendationResponse[].class);
        return new ArrayList<>(Arrays.asList(r));
    }
    public static String eventListToJson(ArrayList<EventRecommendationResponse> list)
    {
        prepareGsonWeb();
        return g.toJson(list);
    }




    public static ArrayList<EventRatingTransfer> jsonToEventTransferList(String json)
    {
        prepareGsonLocal();
        if(json.equals(""))
            return new ArrayList<>();
        EventRatingTransfer[] r =g.fromJson(json,EventRatingTransfer[].class);
        return new ArrayList<>(Arrays.asList(r));
    }
    public static String eventTransferListToJson(ArrayList<EventRatingTransfer> list)
    {
        prepareGsonLocal();
        return g.toJson(list);
    }
}
