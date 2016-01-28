package eu.alfred.api.event.webservice;

import android.app.usage.UsageEvents;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.alfred.api.event.model.Event;
import eu.alfred.api.personalization.model.UserProfile;
import eu.alfred.api.event.recommendation.Eventrating;

/**
 * Created by Tobias on 27/01/2016.
 */
public interface RecommendationService  {
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/recommendations/get")
    public List<Event> getEventRecommendationForUSer(UserProfile user);



    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/recommendationsRating/put")
    public List<Event> createEventRecommendationAnswer(Eventrating rating, UserProfile user);


    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/recommendationsRating/put")
    public List<Event> getEventRating(Event event);


}
