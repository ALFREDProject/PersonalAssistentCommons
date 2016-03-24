package eu.alfred.api.personalization.webservice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import eu.alfred.api.personalization.model.*;
import eu.alfred.api.personalization.responses.PersonalizationResponse;
import eu.alfred.api.personalization.client.*;


public class PersonalizationManager {

    private Messenger messenger;

	private final static String TAG = "PMgr";

    private class PersonalizationSuccessResponse extends Handler {
        private PersonalizationResponse personalizationSuccessResponse;

        public PersonalizationSuccessResponse(PersonalizationResponse personalizationSuccessResponse) {
            this.personalizationSuccessResponse = personalizationSuccessResponse;
        }

        @Override
        public void handleMessage(Message msg) {
            int respCode = msg.what;

            switch (respCode) {
                case PersonalizationConstants.CREATE_USER_PROFILE_RESPONSE:
                case PersonalizationConstants.UPDATE_USER_PROFILE_RESPONSE:
                case PersonalizationConstants.DELETE_USER_PROFILE_RESPONSE:
                case PersonalizationConstants.CREATE_USER_CONTACT_RESPONSE:
                case PersonalizationConstants.UPDATE_USER_CONTACT_RESPONSE:
                case PersonalizationConstants.DELETE_USER_CONTACT_RESPONSE:
                case PersonalizationConstants.CREATE_REQUESTER_RESPONSE:
                case PersonalizationConstants.UPDATE_REQUESTER_RESPONSE:
                case PersonalizationConstants.DELETE_REQUESTER_RESPONSE:
                case PersonalizationConstants.ADD_MEMBER_TO_GROUP_RESPONSE:
                case PersonalizationConstants.CREATE_GROUP_RESPONSE:
                case PersonalizationConstants.DELETE_GROUP_RESPONSE:
                case PersonalizationConstants.DELETE_USER_HEALTH_PROFILE_RESPONSE:
                case PersonalizationConstants.UPDATE_GROUP_RESPONSE:
                case PersonalizationConstants.UPDATE_USER_HEALTH_PROFILE_RESPONSE:
                case PersonalizationConstants.CREATE_USER_HEALTH_PROFILE_RESPONSE:
                case PersonalizationConstants.REMOVE_MEMBER_FROM_GROUP_RESPONSE:


                    JSONObject jsonResponse = null;

                    try {
	                    String json = msg.getData().getString(PersonalizationConstants.EXTRAS_JSON, "{}");
	                    Log.d(TAG, "data{" + PersonalizationConstants.EXTRAS_JSON + "}=" + json);
                        jsonResponse = new JSONObject(json);
                        personalizationSuccessResponse.OnSuccess(jsonResponse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        personalizationSuccessResponse.OnError(e);
                    }
                    break;
            }
        }
    }

    private class PersonalizationDataResponse extends Handler {
        private PersonalizationResponse personalizationDataResponse;

        public PersonalizationDataResponse(PersonalizationResponse personalizationDataResponse) {
            this.personalizationDataResponse = personalizationDataResponse;
        }

        @Override
        public void handleMessage(Message msg) {
            int respCode = msg.what;

            switch (respCode) {
                case PersonalizationConstants.CREATE_USER_PROFILE_FILTER_RESPONSE:
                case PersonalizationConstants.CREATE_USER_PROFILE_RESPONSE:
                case PersonalizationConstants.RETRIEVE_USER_PROFILE_RESPONSE:
                case PersonalizationConstants.UPDATE_REQUESTER_RESPONSE:
                case PersonalizationConstants.UPDATE_USER_CONTACT_RESPONSE:
                case PersonalizationConstants.RETRIEVE_REQUESTER_RESPONSE:
                case PersonalizationConstants.RETRIEVE_USER_REQUESTER_RESPONSE:
                case PersonalizationConstants.RETRIEVE_GROUP_RESPONSE:
                case PersonalizationConstants.RETRIEVE_USER_CONTACT_RESPONSE:
                case PersonalizationConstants.RETRIEVE_USER_HEALTH_PROFILE_RESPONSE:
                case PersonalizationConstants.UPDATE_GROUP_RESPONSE:

                    JSONObject jsonResponse = null;

                    try {
	                    String json = msg.getData().getString(PersonalizationConstants.EXTRAS_JSON, "{}");
	                    Log.d(TAG, "data{" + PersonalizationConstants.EXTRAS_JSON + "}=" + json);
                        jsonResponse = new JSONObject(json);
                        personalizationDataResponse.OnSuccess(jsonResponse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        personalizationDataResponse.OnError(e);
                    }
                    break;
            }
        }
    }

	private class PersonalizationDataListResponse extends Handler {
		private PersonalizationResponse personalizationDataResponse;

		public PersonalizationDataListResponse(PersonalizationResponse personalizationDataResponse) {
			this.personalizationDataResponse = personalizationDataResponse;
		}

		@Override
		public void handleMessage(Message msg) {
			int respCode = msg.what;

			switch (respCode) {
				case PersonalizationConstants.RETRIEVE_USER_CONTACTS_BY_CRITERIA_RESPONSE:
				case PersonalizationConstants.RETRIEVE_USER_PROFILES_LAST_SYNC_RESPONSE:
				case PersonalizationConstants.RETRIEVE_USER_PROFILES_BY_CRITERIA_RESPONSE:
				case PersonalizationConstants.RETRIEVE_USER_CONTACTS_RESPONSE:
				case PersonalizationConstants.GET_UNSYNCHRONIZED_USER_PROFILES_RESPONSE:
				case PersonalizationConstants.RETRIEVE_ALL_USER_CONTACTS_RESPONSE:
				case PersonalizationConstants.RETRIEVE_GROUPS_WITH_MEMBER_RESPONSE:
				case PersonalizationConstants.RETRIEVE_FILTERED_GROUPS_RESPONSE:
				case PersonalizationConstants.RETRIEVE_USER_PROFILE_SENSORED_RESPONSE:
				case PersonalizationConstants.RETRIEVE_USER_PROFILES_RESPONSE:
				case PersonalizationConstants.RETRIEVE_USERS_GROUPS_RESPONSE:
				case PersonalizationConstants.RETRIEVE_ALL_GROUPS_RESPONSE:

					JSONArray jsonResponse = null;

					try {
						String json = msg.getData().getString(PersonalizationConstants.EXTRAS_JSON, "{}");
						Log.d(TAG, "data{" + PersonalizationConstants.EXTRAS_JSON + "}=" + json);
						jsonResponse = new JSONArray(json);
						personalizationDataResponse.OnSuccess(jsonResponse);
					} catch (JSONException e) {
						e.printStackTrace();
						personalizationDataResponse.OnError(e);
					}
					break;
			}
		}
	}

    public PersonalizationManager(Messenger messenger) {
        this.messenger = messenger;
    }


	// Creates a new User Profile that will be persisted in the database
    public void createUserProfile(UserProfile newAlfredUser, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.CREATE_USER_PROFILE);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			Gson gson = new Gson();
			UserProfileDto newAlfredUserDto = UserProfileMapper.toDto(newAlfredUser);
			String newAlfredUserJson = gson.toJson(newAlfredUserDto);
			data.putString("newAlfredUser", newAlfredUserJson);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Retrieves the User Profile with the specified Id persisted in the database
    public void retrieveUserProfile(String userID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_USER_PROFILE);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataResponse(response));

            Bundle data = new Bundle();

			data.putString("userID", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Retrieves the User Profile with the specified Id in behalf of a specified requester
    public void retrieveUserProfileSensored(String searchUsersCriteria, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_USER_PROFILE_SENSORED);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataListResponse(response));

            Bundle data = new Bundle();

			data.putString("searchUsersCriteria", searchUsersCriteria);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Retrieves all User Profiles which match specified criteria
    public void retrieveUserProfiles(String searchUsersCriteria, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_USER_PROFILES);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataListResponse(response));

            Bundle data = new Bundle();

			data.putString("searchUsersCriteria", searchUsersCriteria);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Updates the User Profile with the specified Id for the provided values
    public void updateUserProfile(String userID, String valuesToUpdate, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.UPDATE_USER_PROFILE);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			data.putString("userID", userID);
			data.putString("valuesToUpdate", valuesToUpdate);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Deletes the User Profile with the specified Id from the repository
    public void deleteUserProfile(String userID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.DELETE_USER_PROFILE);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			data.putString("userID", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Returns all User Profiles that were updated or created after the specified sync date
    public void getUnsynchronizedUserProfiles(String lastSyncDate, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.GET_UNSYNCHRONIZED_USER_PROFILES);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataListResponse(response));

            Bundle data = new Bundle();

			data.putString("lastSyncDate", lastSyncDate);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Creates a new User Contact associated with an ALFRED user
    public void createUserContact(String userID, Contact newContact, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.CREATE_USER_CONTACT);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			Gson gson = new Gson();
			data.putString("userID", userID);
			ContactDto newContactDto = ContactMapper.toDto(newContact);
			String newContactJson = gson.toJson(newContactDto);
			data.putString("newContact", newContactJson);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Retrieves all Contacts of a specified ALFRED user
    public void retrieveAllUserContacts(String userID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_ALL_USER_CONTACTS);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataListResponse(response));

            Bundle data = new Bundle();

			data.putString("userID", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Retrieves all Contacts of a specified ALFRED user which match specified criteria
    public void retrieveUserContacts(String userID, String searchContactsCriteria, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_USER_CONTACTS);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataListResponse(response));

            Bundle data = new Bundle();

			data.putString("userID", userID);
			data.putString("searchContactsCriteria", searchContactsCriteria);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Updates the specified Contact of the specified ALFRED user with the specified Id for the provided values
    public void updateUserContact(String contactID, String valuesToUpdate, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.UPDATE_USER_CONTACT);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			data.putString("contactID", contactID);
			data.putString("valuesToUpdate", valuesToUpdate);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Retrieves the specified Contact with the specified Id
    public void retrieveUserContact(String contactID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_USER_CONTACT);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataResponse(response));

            Bundle data = new Bundle();

			data.putString("contactID", contactID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Deleted the Contact with the specified Id from the repository
    public void deleteUserContact(String contactID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.DELETE_USER_CONTACT);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			data.putString("contactID", contactID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Creates a new Requester object which defines the Id of an ALFRED user and all the information of their profile that are allowing access to another ALFRED user (their Id also specified)
    public void createRequester(Requester newServicesRequester, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.CREATE_REQUESTER);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			Gson gson = new Gson();
			RequesterDto newServicesRequesterDto = RequesterMapper.toDto(newServicesRequester);
			String newServicesRequesterJson = gson.toJson(newServicesRequesterDto);
			data.putString("newServicesRequester", newServicesRequesterJson);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Creates a new Requester object which defines the Id of an ALFRED user and all the information of their profile that are allowing access to another ALFRED user (their Id also specified)
    public void retrieveRequester(String requesterId, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_REQUESTER);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataResponse(response));

            Bundle data = new Bundle();

			data.putString("requesterId", requesterId);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Retrieves the Requester with the specified Id persisted in the database
    public void retrieveRequester(String requesterAlfredId, String targetAlfredId, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_REQUESTER);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataResponse(response));

            Bundle data = new Bundle();

			data.putString("requesterAlfredId", requesterAlfredId);
			data.putString("targetAlfredId", targetAlfredId);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Updates the Requester with the specified Id for the provided values
    public void updateRequester(String requesterId, String valuesToUpdate, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.UPDATE_REQUESTER);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			data.putString("requesterId", requesterId);
			data.putString("valuesToUpdate", valuesToUpdate);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Deletes the Requester with the specified Id from the repository
    public void deleteRequester(String requesterId, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.DELETE_REQUESTER);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			data.putString("requesterId", requesterId);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Creates a HealthProfile for a specified ALFRED user
    public void createUserHealthProfile(String userID, HealthProfile profile, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.CREATE_USER_HEALTH_PROFILE);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			Gson gson = new Gson();
			data.putString("userID", userID);
			HealthProfileDto profileDto = HealthProfileMapper.toDto(profile);
			String profileJson = gson.toJson(profileDto);
			data.putString("profile", profileJson);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Updates the HealthProfile of a specified ALFRED user
    public void updateUserHealthProfile(String userID, String valuesToUpdate, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.UPDATE_USER_HEALTH_PROFILE);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			data.putString("userID", userID);
			data.putString("valuesToUpdate", valuesToUpdate);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Retrieves the HealthProfile of a specified ALFRED user
    public void retrieveUserHealthProfile(String userID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_USER_HEALTH_PROFILE);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataResponse(response));

            Bundle data = new Bundle();

			data.putString("userID", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Deletes the HealthProfile of the specified user from the repository
    public void deleteUserHealthProfile(String userID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.DELETE_USER_HEALTH_PROFILE);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			data.putString("userID", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Creates a Group
    public void createGroup(Group group, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.CREATE_GROUP);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			Gson gson = new Gson();
			GroupDto groupDto = GroupMapper.toDto(group);
			String groupJson = gson.toJson(groupDto);
			data.putString("group", groupJson);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Updates the group with the specified ID
    public void updateGroup(String groupID, String valuesToUpdate, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.UPDATE_GROUP);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			data.putString("groupID", groupID);
			data.putString("valuesToUpdate", valuesToUpdate);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Retrieves a group by id
    public void retrieveGroup(String groupID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_GROUP);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataResponse(response));

            Bundle data = new Bundle();

			data.putString("groupID", groupID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Adds a member to the group with the specified ID
    public void addMemberToGroup(String groupID, String memberID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.ADD_MEMBER_TO_GROUP);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			data.putString("groupID", groupID);
			data.putString("memberID", memberID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Removes a member from the group with the specified ID
    public void removeMemberFromGroup(String groupID, String memberID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.REMOVE_MEMBER_FROM_GROUP);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			data.putString("groupID", groupID);
			data.putString("memberID", memberID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Retrieves all groups owned by the specified user
    public void retrieveUsersGroups(String userID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_USERS_GROUPS);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataListResponse(response));

            Bundle data = new Bundle();

			data.putString("userID", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Retrieves all groups where the specified user is a member
    public void retrieveGroupsWithMember(String userID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_GROUPS_WITH_MEMBER);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataListResponse(response));

            Bundle data = new Bundle();

			data.putString("userID", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Retrieves all groups which match specified criteria
    public void retrieveFilteredGroups(String searchUsersCriteria, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_FILTERED_GROUPS);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataListResponse(response));

            Bundle data = new Bundle();

			data.putString("searchUsersCriteria", searchUsersCriteria);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Retrieves all groups
    public void retrieveAllGroups(PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_ALL_GROUPS);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataListResponse(response));

            Bundle data = new Bundle();
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

	// Deletes a group from the repository
    public void deleteGroup(String groupID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.DELETE_GROUP);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();

			data.putString("groupID", groupID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
