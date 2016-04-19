package eu.alfred.api.personalization.client.eventrecommendation;

import com.google.gson.annotations.Expose;

import java.util.HashMap;

import eu.alfred.api.personalization.model.eventrecommendation.Event;

/**
 * Created by thardes on 19/04/2016.
 */
public class EventrecommendationDto {
    @Expose
    public HashMap<Event,Integer> id;
}
