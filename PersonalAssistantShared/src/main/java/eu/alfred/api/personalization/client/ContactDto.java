package eu.alfred.api.personalization.client;

import com.google.gson.annotations.Expose;

import java.util.Map;

import eu.alfred.api.personalization.model.AccessLevels;
import eu.alfred.api.personalization.model.Address;
import eu.alfred.api.personalization.model.Gender;
import eu.alfred.api.personalization.model.Relation;

public class ContactDto {

	@Expose
	public String id;
	@Expose
	public String alfredUserName;
	@Expose
	public String contactsAlfredId;
	@Expose
	public String userID;
	@Expose
	public String firstName;
	@Expose
	public String middleName;
	@Expose
	public String lastName;
	@Expose
	public String prefferedName;
	@Expose
	public Gender gender;
	@Expose
	public String dateOfBirth;
	@Expose
	public String phone;
	@Expose
	public String mobilePhone;
	@Expose
	public String email;
	@Expose
	public Address residentialAddress;
	@Expose
	public Address postalAddress;
	@Expose
	public Relation[] relationToUser;
	@Expose
	public AccessLevels accessLevels;
	@Expose
	public String[] socialMediaProfiles;
	@Expose
	public Map<String,Boolean> accessRightsToAttributes;

}
