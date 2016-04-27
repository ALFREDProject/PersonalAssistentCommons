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
		if(userProfile.dateOfBirth!=null) {
			result.setDateOfBirth(new Date(Long.parseLong(userProfile.dateOfBirth)));
		} else {
			result.setDateOfBirth(new Date());
		}
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
		if(userProfile.anniversaryDate!=null) {
			result.setAnniversaryDate(new Date(Long.parseLong(userProfile.anniversaryDate)));
		} else {
			result.setAnniversaryDate(new Date());
		}
		result.setNextOfKin(userProfile.nextOfKin);
		result.setMyersBriggsIndicator(userProfile.myersBriggsIndicator);
		if(userProfile.selfDescrPersonalityChar!=null) {
			result.setSelfDescrPersonalityChar(userProfile.selfDescrPersonalityChar.toArray(
					new String[userProfile.selfDescrPersonalityChar.size()]));
		} else {
			result.setSelfDescrPersonalityChar(new String[0]);
		}
		if(userProfile.interests!=null) {
			result.setInterests(userProfile.interests.toArray(
					new String[userProfile.interests.size()]));
		} else {
			result.setInterests(new String[0]);
		}
		if(userProfile.culturalOrFamilyNeeds!=null) {
			result.setCulturalOrFamilyNeeds(userProfile.culturalOrFamilyNeeds.toArray(
					new String[userProfile.culturalOrFamilyNeeds.size()]));
		} else {
			result.setCulturalOrFamilyNeeds(new String[0]);
		}
		if(userProfile.socialMediaProfiles!=null) {
			result.setSocialMediaProfiles(userProfile.socialMediaProfiles.toArray(
					new String[userProfile.socialMediaProfiles.size()]));
		} else {
			result.setSocialMediaProfiles(new String[0]);
		}
		if(userProfile.alfedAppInstalationDate!=null) {
			result.setAlfedAppInstalationDate(new Date(Long.parseLong(userProfile.alfedAppInstalationDate)));
		} else {
			result.setAlfedAppInstalationDate(new Date());
		}
		if(userProfile.lastUpdated!=null) {
			result.setLastUpdated(new Date(Long.parseLong(userProfile.lastUpdated)));
		} else {
			result.setLastUpdated(new Date());
		}
		result.setAlfredUserName(userProfile.alfredUserName);
		result.setMobilityLevel(userProfile.mobilityLevel);

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

	private static String time(Date date) {
		if (date == null) return "0";
		return Long.toString(date.getTime());
	}
}