package eu.alfred.api.personalization.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import eu.alfred.api.personalization.model.UserProfile;

public class UserProfileMapper {

	public static UserProfile toModel(UserProfileDto userProfile) {
		UserProfile result = new UserProfile();

		result.setId(userProfile.id);
		result.setFirstName(userProfile.firstName);
		result.setMiddleName(userProfile.middleName);
		result.setLastName(userProfile.lastName);
		result.setPrefferedName(userProfile.prefferedName);
		result.setGender(userProfile.gender);
		result.setDateOfBirth(date(userProfile.dateOfBirth));
		result.setPhone(userProfile.phone);
		result.setMobilePhone(userProfile.mobilePhone);
		result.setEmail(userProfile.email);
		result.setResidentialAddress(userProfile.residentialAddress);
		result.setPostalAddress(userProfile.postalAddress);
		result.setCitizenship(userProfile.citizenship);
		result.setNationality(userProfile.nationality);
		result.setLanguage(userProfile.language);
		result.setSocialSecurityNumber(userProfile.socialSecurityNumber);
		result.setMaritalStatus(userProfile.maritalStatus);
		result.setEducationLevel(userProfile.educationLevel);
		result.setEmploymentStatus(userProfile.employmentStatus);
		result.setHealthInsurance(userProfile.healthInsurance);
		result.setProfession(userProfile.profession);
		result.setAnniversaryDate(date(userProfile.anniversaryDate));
		result.setNextOfKin(userProfile.nextOfKin);
		result.setMyersBriggsIndicator(userProfile.myersBriggsIndicator);
		result.setSelfDescrPersonalityChar(list(userProfile.selfDescrPersonalityChar));
		result.setInterests(list(userProfile.interests));
		result.setCulturalOrFamilyNeeds(list(userProfile.culturalOrFamilyNeeds));
		result.setSocialMediaProfiles(list(userProfile.socialMediaProfiles));
		result.setAlfedAppInstalationDate(date(userProfile.alfedAppInstalationDate));
		result.setAlfredUserName(userProfile.alfredUserName);
		result.setMobilityLevel(userProfile.mobilityLevel);
		result.setLastUpdated(date(userProfile.lastUpdated));

		return result;
	}

	public static UserProfileDto toDto(UserProfile userProfile) {
		UserProfileDto userProfileRequest = new UserProfileDto();

		userProfileRequest.id = userProfile.getId();
		userProfileRequest.firstName = userProfile.getFirstName();
		userProfileRequest.middleName = userProfile.getMiddleName();
		userProfileRequest.lastName = userProfile.getLastName();
		userProfileRequest.prefferedName = userProfile.getPrefferedName();
		userProfileRequest.gender = userProfile.getGender();
		userProfileRequest.dateOfBirth = time(userProfile.getDateOfBirth());
		userProfileRequest.phone = userProfile.getPhone();
		userProfileRequest.mobilePhone = userProfile.getMobilePhone();
		userProfileRequest.email = userProfile.getEmail();
		userProfileRequest.residentialAddress = userProfile.getResidentialAddress();
		userProfileRequest.postalAddress = userProfile.getPostalAddress();
		userProfileRequest.citizenship = userProfile.getCitizenship();
		userProfileRequest.nationality = userProfile.getNationality();
		userProfileRequest.language = userProfile.getLanguage();
		userProfileRequest.socialSecurityNumber = userProfile.getSocialSecurityNumber();
		userProfileRequest.maritalStatus = userProfile.getMaritalStatus();
		userProfileRequest.educationLevel = userProfile.getEducationLevel();
		userProfileRequest.employmentStatus = userProfile.getEmploymentStatus();
		userProfileRequest.healthInsurance = userProfile.getHealthInsurance();
		userProfileRequest.profession = userProfile.getProfession();
		userProfileRequest.anniversaryDate = time(userProfile.getAnniversaryDate());
		userProfileRequest.nextOfKin = userProfile.getNextOfKin();
		userProfileRequest.myersBriggsIndicator = userProfile.getMyersBriggsIndicator();
		userProfileRequest.selfDescrPersonalityChar = list(userProfile.getSelfDescrPersonalityChar());
		userProfileRequest.interests = list(userProfile.getInterests());
		userProfileRequest.culturalOrFamilyNeeds = list(userProfile.getCulturalOrFamilyNeeds());
		userProfileRequest.socialMediaProfiles = list(userProfile.getSocialMediaProfiles());
		userProfileRequest.alfedAppInstalationDate = time(userProfile.getAlfedAppInstalationDate());
		userProfileRequest.alfredUserName = userProfile.getAlfredUserName();
		userProfileRequest.mobilityLevel = userProfile.getMobilityLevel();
		userProfileRequest.lastUpdated = time(userProfile.getLastUpdated());

		return userProfileRequest;
	}

	private static List<String> list(String [] array) {
		if (array == null) return new ArrayList<String>();
		return Arrays.asList(array);
	}

	private static String[] list(List<String> list) {
		if (list == null) return new String[0];
		return list.toArray(new String[list.size()]);
	}

	private static String time(Date date) {
		if (date == null) return "0";
		return Long.toString(date.getTime());
	}

	private static Date date(String time) {
		if (time == null) return null;
		long tl = 0L;
		try {
			tl = Long.parseLong(time);
		}
		catch (NumberFormatException e) {
			return null;
		}
		return new Date(tl);
	}
}
