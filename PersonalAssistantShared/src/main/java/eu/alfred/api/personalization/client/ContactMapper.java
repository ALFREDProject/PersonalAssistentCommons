package eu.alfred.api.personalization.client;

import eu.alfred.api.personalization.model.Contact;

public class ContactMapper {

	public static Contact toModel(ContactDto contactDto) {

		Contact contact = new Contact();
		contact.setId(contactDto.id);
		contact.setAlfredUserName(contactDto.alfredUserName);
		contact.setContactsAlfredId(contactDto.contactsAlfredId);
		contact.setUserID(contactDto.userID);
		contact.setFirstName(contactDto.firstName);
		contact.setMiddleName(contactDto.middleName);
		contact.setLastName(contactDto.lastName);
		contact.setPrefferedName(contactDto.prefferedName);
		contact.setGender(contactDto.gender);
		contact.setDateOfBirth(contactDto.dateOfBirth);
		contact.setPhone(contactDto.phone);
		contact.setMobilePhone(contactDto.mobilePhone);
		contact.setEmail(contactDto.email);
		contact.setResidentialAddress(contactDto.residentialAddress);
		contact.setPostalAddress(contactDto.postalAddress);
		contact.setRelationToUser(contactDto.relationToUser);
		contact.setAccessLevels(contactDto.accessLevels);
		contact.setSocialMediaProfiles(contactDto.socialMediaProfiles);
		contact.setAccessRightsToAttributes(contactDto.accessRightsToAttributes);

		return contact;
	}

	public static ContactDto toDto(Contact contact) {

		ContactDto contactRequest = new ContactDto();
		contactRequest.id = contact.getId();
		contactRequest.alfredUserName = contact.getAlfredUserName();
		contactRequest.contactsAlfredId = contact.getContactsAlfredId();
		contactRequest.userID = contact.getUserID();
		contactRequest.firstName = contact.getFirstName();
		contactRequest.middleName = contact.getMiddleName();
		contactRequest.lastName = contact.getLastName();
		contactRequest.prefferedName = contact.getPrefferedName();
		contactRequest.gender = contact.getGender();
		contactRequest.dateOfBirth = contact.getDateOfBirth();
		contactRequest.phone = contact.getPhone();
		contactRequest.mobilePhone = contact.getMobilePhone();
		contactRequest.email = contact.getEmail();
		contactRequest.residentialAddress = contact.getResidentialAddress();
		contactRequest.postalAddress = contact.getPostalAddress();
		contactRequest.relationToUser = contact.getRelationToUser();
		contactRequest.accessLevels = contact.getAccessLevels();
		contactRequest.socialMediaProfiles = contact.getSocialMediaProfiles();
		contactRequest.accessRightsToAttributes = contact.getAccessRightsToAttributes();

		return contactRequest;
	}
}
