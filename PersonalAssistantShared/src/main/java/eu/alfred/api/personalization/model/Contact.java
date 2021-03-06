package eu.alfred.api.personalization.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Contact implements Serializable {

	private String id;
	private String alfredUserName; // contacts can be alfred users, we point to this user
	private String contactsAlfredId;
	private String userID;
	private String firstName;
	private String middleName;
	private String lastName;
	private String prefferedName;
	private Gender gender;
	private Date dateOfBirth;
	private String phone;
	private String mobilePhone;
	private String email;
	private Address residentialAddress;
	private Address postalAddress;
	private Relation[] relationToUser;
	private AccessLevels accessLevels;
	private String[] socialMediaProfiles;
	private Map<String,Boolean> accessRightsToAttributes;

	public Contact() {
		setId();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) { this.id = id; }
	// For cases we create id for the contact
	public String setId() {
		this.id =  "alfred-user-contact-"+UUID.randomUUID().toString();
		return this.id; // When creating a new contact we provide the id back so they assign them to the users
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPrefferedName() {
		return prefferedName;
	}
	public void setPrefferedName(String prefferedName) {
		this.prefferedName = prefferedName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getResidentialAddress() {
		return residentialAddress;
	}
	public void setResidentialAddress(Address residentialAddress) {
		this.residentialAddress = residentialAddress;
	}
	public Address getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(Address postalAddress) {
		this.postalAddress = postalAddress;
	}
	public Relation[] getRelationToUser() {
		return relationToUser;
	}
	public void setRelationToUser(Relation[] relationToUser) {
		this.relationToUser = relationToUser;
	}
	public String[] getSocialMediaProfiles() {
		return socialMediaProfiles;
	}
	public void setSocialMediaProfiles(String[] socialMediaProfiles) {
		this.socialMediaProfiles = socialMediaProfiles;
	}

	public AccessLevels getAccessLevels() {
		return accessLevels;
	}

	public void setAccessLevels(AccessLevels accessLevels) {
		this.accessLevels = accessLevels;
	}

	public Map<String, Boolean> getAccessRightsToAttributes() {
		return accessRightsToAttributes;
	}

	public void setAccessRightsToAttributes(
			Map<String, Boolean> accessRightsToAttributes) {
		this.accessRightsToAttributes = accessRightsToAttributes;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Contact{");
		sb.append("id='").append(id).append('\'');
		sb.append(", alfredUserName='").append(alfredUserName).append('\'');
		sb.append(", contactsAlfredId='").append(contactsAlfredId).append('\'');
		sb.append(", userID='").append(userID).append('\'');
		sb.append(", firstName='").append(firstName).append('\'');
		sb.append(", middleName='").append(middleName).append('\'');
		sb.append(", lastName='").append(lastName).append('\'');
		sb.append(", prefferedName='").append(prefferedName).append('\'');
		sb.append(", gender=").append(gender);
		sb.append(", dateOfBirth=").append(dateOfBirth);
		sb.append(", phone='").append(phone).append('\'');
		sb.append(", mobilePhone='").append(mobilePhone).append('\'');
		sb.append(", email='").append(email).append('\'');
		sb.append(", residentialAddress=").append(residentialAddress);
		sb.append(", postalAddress=").append(postalAddress);
		sb.append(", relationToUser=").append(Arrays.toString(relationToUser));
		sb.append(", accessLevels=").append(accessLevels);
		sb.append(", socialMediaProfiles=").append(Arrays.toString(socialMediaProfiles));
		sb.append(", accessRightsToAttributes=").append(accessRightsToAttributes);
		sb.append('}');
		return sb.toString();
	}

	public String getAlfredUserName() {
		return alfredUserName;
	}

	public void setAlfredUserName(String alfredUserName) {
		this.alfredUserName = alfredUserName;
	}
	public String getContactsAlfredId() {
		return contactsAlfredId;
	}

	public void setContactsAlfredId(String contactsAlfredId) {
		this.contactsAlfredId = contactsAlfredId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
