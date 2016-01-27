package eu.alfred.api.personalization.webservice;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import eu.alfred.api.personalization.model.Contact;
import eu.alfred.api.personalization.model.HistoricalEntry;
import eu.alfred.api.personalization.model.Requesters;
import eu.alfred.api.personalization.model.UserProfile;

/**
 * Created by Tobias on 27/01/2016.
 */
public class PersonalizationManager implements PersonalizationManagerDBService {
    @Override
    public Response createUserProfile(UserProfile newAlfredUser) {
        return null;
    }

    @Override
    public List<UserProfile> retrieveUserProfile(@PathParam("id") String userID) {
        return null;
    }

    @Override
    public List<UserProfile> retrieveUserProfileSensored(String searchUsersCriteria) {
        return null;
    }

    @Override
    public List<UserProfile> retrieveUserProfiles(String searchUsersCriteria) {
        return null;
    }

    @Override
    public Response updateUserProfile(@PathParam("id") String userID, String valuesToUpdate) {
        return null;
    }

    @Override
    public Response deleteUserProfile(@PathParam("id") String userID) {
        return null;
    }

    @Override
    public Boolean checkUsernameAvailability(@PathParam("username") String username) {
        return null;
    }

    @Override
    public List<UserProfile> getUnsynchronizedUserProfiles(@PathParam("lastSyncDate") String lastSyncDate) {
        return null;
    }

    @Override
    public Response createUserContact(@PathParam("id") String userID, Contact newContact) {
        return null;
    }

    @Override
    public List<Contact> retrieveAllUserContacts(@PathParam("id") String userID) {
        return null;
    }

    @Override
    public List<Contact> retrieveUserContacts(@PathParam("id") String userID, String searchContactsCriteria) {
        return null;
    }

    @Override
    public Response updateUserContact(@PathParam("id") String contactID, String valuesToUpdate) {
        return null;
    }

    @Override
    public Response deleteUserContact(@PathParam("id") String contactID) {
        return null;
    }

    @Override
    public Response createUserHistoricalEntry(@PathParam("id") String userID, HistoricalEntry newHistoricalEntry) {
        return null;
    }

    @Override
    public List<HistoricalEntry> retrieveAllUserHistoricalEntries(@PathParam("id") String userID) {
        return null;
    }

    @Override
    public List<HistoricalEntry> retrieveUserHistoricalEntries(@PathParam("id") String userID, String searchHistoricalEntriesCriteria) {
        return null;
    }

    @Override
    public Response updateUserHistoricalEntries(@PathParam("id") String historicalEntryID, String valuesToUpdate) {
        return null;
    }

    @Override
    public Response deleteUserHistoricalEntries(@PathParam("id") String historicalEntryID) {
        return null;
    }

    @Override
    public Response createRequester(Requesters newServicesRequester) {
        return null;
    }

    @Override
    public List<Requesters> retrieveRequester(@PathParam("id") String requesterId) {
        return null;
    }

    @Override
    public List<Requesters> retrieveRequester(@PathParam("requesterAlfredId") String requesterAlfredId, @PathParam("targetAlfredId") String targetAlfredId) {
        return null;
    }

    @Override
    public Response updateRequester(@PathParam("id") String requesterId, String valuesToUpdate) {
        return null;
    }

    @Override
    public Response deleteRequester(@PathParam("id") String requesterId) {
        return null;
    }
}
