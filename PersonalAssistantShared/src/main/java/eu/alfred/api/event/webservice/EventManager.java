package eu.alfred.api.event.webservice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.alfred.api.event.model.AnalyticsParameters;
import eu.alfred.api.event.model.Event;
import eu.alfred.api.event.model.EventConstants;

/**
 * Created by Tobias on 28/01/2016.
 */
public class EventManager  extends Handler {
    private Messenger messenger;
    public EventManager(Messenger messenger){
        this.messenger = messenger;
    }

    /**
     * Retrieves an event entry from the EM, with the specified ID.
     *
     * @param eventId A String with the unique identification of the event.
     * @return The event with the specified ID, if successfully retrieved.
     */
    public Event getEvent(String eventId) {
        if (messenger != null) {
            Message msg = Message.obtain(null, EventConstants.GET_EVENT);
            Bundle data = new Bundle();
            data.putString("eventid", eventId);
            msg.setData(data);
            try {
                messenger.send(msg);
                //TODO: Get the result here (Somehow?!)
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * Retrieves a list of events from the Event Manager, based on parameters described in an AnalyticsParameters object.
     * The AnalyticsParameters object makes use of calculations of the OLAP cubes defined in the EM.
     *
     * @param parameters The AnalyticsParameters object with the parameters for filtering the events to be retrieved.
     * @return A List of Event objects which satisfy the defined parameters.
     */
    public List<Event> getEvents(AnalyticsParameters parameters) {
        if (messenger != null) {
            Message msg = Message.obtain(null, EventConstants.GET_EVENT);
            Bundle data = new Bundle();
            data.putString("parameters", new Gson().toJson(parameters));
            msg.setData(data);
            try {
                messenger.send(msg);
                //TODO: Get the result here (Somehow?!)
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Creates a new entry of an event if the event doesn't exist, or updates the event if already present.
     *
     * @param newEvent The Event object for the new event entry.
     * @return The new Event entry created if the operation was successful, or null otherwise.
     */
    public Event addEvent(Event newEvent) {
        if (messenger != null) {
            Message msg = Message.obtain(null, EventConstants.GET_EVENT);
            Bundle data = new Bundle();
            data.putString("event", new Gson().toJson(newEvent));
            msg.setData(data);
            try {
                messenger.send(msg);
                //TODO: Get the result here (Somehow?!)
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Deletes an event entry with the specified ID.
     *
     * @param eventId A String with the unique identification of the event to be deleted.
     * @return The Event that was deleted with the operation.
     */
    public Object deleteEvent(String eventId) {
        if (messenger != null) {
            Message msg = Message.obtain(null, EventConstants.GET_EVENT);
            Bundle data = new Bundle();
            data.putString("eventId",eventId);
            msg.setData(data);
            try {
                messenger.send(msg);
                //TODO: Get the result here (Somehow?!)
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * Deletes a list of event entry with specified IDs.
     *
     * @param eventIds A String with the unique identification of the event to be deleted.
     * @return The Event that was deleted with the operation.
     */
    public Object deleteEvent(List<String> eventIds) {
        if (messenger != null) {
            Message msg = Message.obtain(null, EventConstants.GET_EVENTS);
            Bundle data = new Bundle();
            data.putStringArrayList("eventIds", new ArrayList<>(Arrays.asList((String[]) eventIds.toArray())));
            msg.setData(data);
            try {
                messenger.send(msg);
                //TODO: Get the result here (Somehow?!)
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Adds a user with a specified Id as a participant in an event with a specified Id.
     *
     * @param eventId The unique identification of the event in which the user will participate.
     * @param participantId The unique identification of the user who will participate in the event
     * @return A Boolean indicating the success or failure of the operation
     */
    public Boolean addParticipant(String eventId, String participantId) {
        if (messenger != null) {
            Message msg = Message.obtain(null, EventConstants.ADD_EVENT_PARTICIPANT);
            Bundle data = new Bundle();
            data.putString("eventId", eventId);
            data.putString("participantId", participantId);
            msg.setData(data);
            try {
                messenger.send(msg);
                //TODO: Get the result here (Somehow?!)
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

