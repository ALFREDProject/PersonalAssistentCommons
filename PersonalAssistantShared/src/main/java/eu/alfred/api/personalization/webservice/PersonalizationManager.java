package eu.alfred.api.personalization.webservice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import eu.alfred.api.personalization.responses.PersonalizationResponse;

/**
 * Created by Tobias on 27/01/2016.
 */
public class PersonalizationManager {

    private Messenger messenger;

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

                    JSONObject jsonResponse = null;

                    try {
                        jsonResponse = new JSONObject(msg.getData().getString("JsonData", "{}"));
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
                case PersonalizationConstants.RETRIEVE_USER_CONTACTS_BY_CRITERIA_RESPONSE:
                case PersonalizationConstants.RETRIEVE_USER_PROFILES_LAST_SYNC_RESPONSE:
                case PersonalizationConstants.RETRIEVE_USER_PROFILE_RESPONSE:
                case PersonalizationConstants.UPDATE_REQUESTER_RESPONSE:
                case PersonalizationConstants.UPDATE_USER_CONTACT_RESPONSE:
                case PersonalizationConstants.RETRIEVE_USER_PROFILES_BY_CRITERIA_RESPONSE:
                case PersonalizationConstants.RETRIEVE_REQUESTER_RESPONSE:
                case PersonalizationConstants.RETRIEVE_USER_REQUESTER_RESPONSE:
                case PersonalizationConstants.RETRIEVE_USER_CONTACTS_RESPONSE:
                case PersonalizationConstants.RETRIEVE_USER_ID_RESPONSE:
                    JSONArray jsonResponse = null;

                    try {
                        jsonResponse = new JSONArray(msg.getData().getString("response"));
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

    public void createUserProfile(String newAlfredUser, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.CREATE_USER_PROFILE);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();
            data.putString("newAlfredUser", newAlfredUser);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void retrieveUserProfile(String userID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_USER_PROFILE);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void retrieveUserProfileSensored(String userID, String searchUsersCriteria, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_USER_PROFILES_BY_CRITERIA);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            data.putString("searchUsersCriteria", searchUsersCriteria);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void retrieveUserProfilesFilter(String userID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.CREATE_USER_PROFILE_FILTER);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateUserProfile(String userID, String valuesToUpdate, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.UPDATE_USER_PROFILE);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            data.putString("valuesToUpdate", valuesToUpdate);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteUserProfile(String userID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.DELETE_USER_PROFILE);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /*public Boolean checkUsernameAvailability(String username) {
        if (messenger != null) {
            Message msg = Message.obtain(null, S);
            Bundle data = new Bundle();
            data.putString("username", username);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }*/

    public void getUnsynchronizedUserProfiles(String lastSyncDate, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_USER_PROFILES_LAST_SYNC);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataResponse(response));

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

    public void createUserContact(String userID, String newContact, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.CREATE_USER_CONTACT);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            data.putString("newContact", newContact);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void retrieveAllUserContacts(String userID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_USER_CONTACTS);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void retrieveUserContacts(String userID, String searchContactsCriteria, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_USER_CONTACTS_BY_CRITERIA);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", userID);
            data.putString("searchContactsCriteria", searchContactsCriteria);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateUserContact(String contactID, String valuesToUpdate, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.UPDATE_USER_CONTACT);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();
            data.putString("id", contactID);
            data.putString("searchContactsCriteria", valuesToUpdate);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteUserContact(String contactID, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.DELETE_USER_CONTACT);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();
            data.putString("id", contactID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    public Response createUserHistoricalEntry(String userID, HistoricalEntry newHistoricalEntry) {
        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.START_LISTENING);
            Bundle data = new Bundle();
            data.putString("id", userID);
            data.putSerializable("newHistoricalEntry",newHistoricalEntry);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<HistoricalEntry> retrieveAllUserHistoricalEntries(@PathParam("id") String userID) {
        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.START_LISTENING);
            Bundle data = new Bundle();
            data.putString("id", userID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<HistoricalEntry> retrieveUserHistoricalEntries(@PathParam("id") String userID, String searchHistoricalEntriesCriteria) {
        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.START_LISTENING);
            Bundle data = new Bundle();
            data.putString("id", userID);
            data.putString("searchHistoricalEntriesCriteria",searchHistoricalEntriesCriteria);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Response updateUserHistoricalEntries(@PathParam("id") String historicalEntryID, String valuesToUpdate) {
        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.START_LISTENING);
            Bundle data = new Bundle();
            data.putString("id", historicalEntryID);
            data.putString("valuesToUpdate",valuesToUpdate);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Response deleteUserHistoricalEntries(@PathParam("id") String historicalEntryID) {
        if (messenger != null) {
            Message msg = Message.obtain(null, CadeConstants.START_LISTENING);
            Bundle data = new Bundle();
            data.putString("id", historicalEntryID);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
   */

    public void createRequester(String newServicesRequester, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.CREATE_REQUESTER);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();
            data.putSerializable("newServicesRequester", newServicesRequester);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void retrieveRequester(String requesterId, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_REQUESTER);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataResponse(response));

            Bundle data = new Bundle();
            data.putString("id", requesterId);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void retrieveRequesterByTarget(String requesterAlfredId, String targetAlfredId, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_USER_REQUESTER);

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

    public void updateRequester(String requesterId, String valuesToUpdate, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.UPDATE_REQUESTER);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();
            data.putString("id", requesterId);
            data.putString("valuesToUpdate", valuesToUpdate);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteRequester(String requesterId, PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.DELETE_REQUESTER);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationSuccessResponse(response));

            Bundle data = new Bundle();
            data.putString("id", requesterId);
            msg.setData(data);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void retrieveUserID(PersonalizationResponse response) {
        if (messenger != null) {
            Message msg = Message.obtain(null, PersonalizationConstants.RETRIEVE_USER_ID);

            if (response != null)
                msg.replyTo = new Messenger(new PersonalizationDataResponse(response));

            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
