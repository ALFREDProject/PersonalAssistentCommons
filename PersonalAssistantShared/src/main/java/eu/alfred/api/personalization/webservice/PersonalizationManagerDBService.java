package eu.alfred.api.personalization.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import eu.alfred.api.personalization.model.Contact;
import eu.alfred.api.personalization.model.HistoricalEntry;
import eu.alfred.api.personalization.model.Requesters;
import eu.alfred.api.personalization.model.UserProfile;

//@WebService(serviceName = "databaseServices", targetNamespace = "http://80.86.83.34:8080/personalization-manager/services")
//@WebService(serviceName = "databaseServices", targetNamespace = "http://80.86.83.34:8080/personalization-manager/services")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public interface PersonalizationManagerDBService {

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/new")
	public Response createUserProfile(UserProfile newAlfredUser);
	
	@GET
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/{id}")
	public List<UserProfile> retrieveUserProfile(@PathParam("id") String userID);
	
	/*@GET
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/{id}/accessToken/{token}")
	public List<UserProfile> retrieveUserProfileSensored(@PathParam("id") String userID, @PathParam("token") String token);*/
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/")
	public List<UserProfile> retrieveUserProfileSensored(String searchUsersCriteria);
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/retrieve")
	public List<UserProfile> retrieveUserProfiles(String searchUsersCriteria);
	
	@PUT
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/{id}")
	public Response updateUserProfile(@PathParam("id") String userID, String valuesToUpdate);
			
	@DELETE
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/{id}")
	public Response deleteUserProfile(@PathParam("id") String userID);
	
	@GET
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/username/{username}")
	public Boolean checkUsernameAvailability(@PathParam("username") String username);
	
	@GET
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/sync/{lastSyncDate}")
	public List<UserProfile> getUnsynchronizedUserProfiles(@PathParam("lastSyncDate") String lastSyncDate);
	
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/{id}/contacts/new")
	public Response createUserContact(@PathParam("id") String userID, Contact newContact);
	
	@GET
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/{id}/contacts/all")
	public List<Contact> retrieveAllUserContacts(@PathParam("id") String userID);
	
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/{id}/contacts")
	public List<Contact> retrieveUserContacts(@PathParam("id") String userID, String searchContactsCriteria);
	
	@PUT
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/contacts/{id}")
	public Response updateUserContact(@PathParam("id") String contactID, String valuesToUpdate);
	
	@DELETE
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/contacts/{id}")
	public Response deleteUserContact(@PathParam("id") String contactID);
	
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/{id}/historicalentries/new")
	public Response createUserHistoricalEntry(@PathParam("id") String userID, HistoricalEntry newHistoricalEntry);
	
	@GET
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/{id}/historicalentries/all")
	public List<HistoricalEntry> retrieveAllUserHistoricalEntries(@PathParam("id") String userID);
	
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/{id}/historicalentries")
	public List<HistoricalEntry> retrieveUserHistoricalEntries(@PathParam("id") String userID, String searchHistoricalEntriesCriteria);
	
	@PUT
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/historicalentries/{id}")
	public Response updateUserHistoricalEntries(@PathParam("id") String historicalEntryID, String valuesToUpdate);
	
	@DELETE
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/users/historicalentries/{id}")
	public Response deleteUserHistoricalEntries(@PathParam("id") String historicalEntryID);
	
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/requesters/new")
	public Response createRequester(Requesters newServicesRequester);
	
	@GET
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/requesters/{id}")
	public List<Requesters> retrieveRequester(@PathParam("id") String requesterId);
	
	@GET
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("users/{targetAlfredId}/requesters/{requesterAlfredId}")
	public List<Requesters> retrieveRequester(
			@PathParam("requesterAlfredId") String requesterAlfredId, @PathParam("targetAlfredId") String targetAlfredId);
	
	@PUT
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/requesters/{id}")
	public Response updateRequester(@PathParam("id") String requesterId, String valuesToUpdate);
			
	@DELETE
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/requesters/{id}")
	public Response deleteRequester(@PathParam("id") String requesterId);
 	
}