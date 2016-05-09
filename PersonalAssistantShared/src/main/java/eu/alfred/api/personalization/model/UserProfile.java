package eu.alfred.api.personalization.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class UserProfile implements Serializable {

	private String id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String alfredUserName;
	private String prefferedName;
	private Gender gender;
	private Date dateOfBirth;
	private String phone;
	private String mobilePhone;
	private String email;
	private Address residentialAddress;
	private Address postalAddress;
	private String citizenship;
	private String nationality;
	private Language language;
	private String socialSecurityNumber;
	private MaritalStatus maritalStatus;
	private EducationLevel educationLevel;
	private EmploymentStatus employmentStatus;
	private String healthInsurance;
	private String profession;
	private Date anniversaryDate;
	private Contact nextOfKin;
	private MyersBriggsTypeIndicator myersBriggsIndicator;
	private String[] selfDescrPersonalityChar;
	private String[] interests;
	private String[] culturalOrFamilyNeeds;
	private String[] socialMediaProfiles;
	private MobilityLevel mobilityLevel;
	private Date lastUpdated;
	private Date alfedAppInstalationDate;

	public UserProfile() {
		this.lastUpdated = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) { // Change it to create id's with UUID that start with alfred-user-...
		this.id = id;
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

	public int getAgeInYears() {
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTime(new Date());
		Calendar calendarBirth = Calendar.getInstance();
		calendarBirth.setTime(dateOfBirth);

		int diff = calendarNow.get(Calendar.YEAR) - calendarBirth.get(Calendar.YEAR);
		if (calendarBirth.get(Calendar.MONTH) > calendarNow.get(Calendar.MONTH)
				|| (calendarBirth.get(Calendar.MONTH) == calendarNow.get(Calendar.MONTH)
				&& calendarBirth.get(Calendar.DATE) > calendarNow.get(Calendar.DATE))) {
			diff--;
		}
		return diff;
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

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public EducationLevel getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	public EmploymentStatus getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(EmploymentStatus employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public String getHealthInsurance() {
		return healthInsurance;
	}

	public void setHealthInsurance(String healthInsurance) {
		this.healthInsurance = healthInsurance;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Date getAnniversaryDate() {
		return anniversaryDate;
	}

	public void setAnniversaryDate(Date anniversaryDate) {
		this.anniversaryDate = anniversaryDate;
	}

	public Contact getNextOfKin() {
		return nextOfKin;
	}

	public void setNextOfKin(Contact nextOfKin) {
		this.nextOfKin = nextOfKin;
	}

	public MyersBriggsTypeIndicator getMyersBriggsIndicator() {
		return myersBriggsIndicator;
	}

	public void setMyersBriggsIndicator(
			MyersBriggsTypeIndicator myersBriggsIndicator) {
		this.myersBriggsIndicator = myersBriggsIndicator;
	}

	public String[] getSelfDescrPersonalityChar() {
		return selfDescrPersonalityChar;
	}

	public void setSelfDescrPersonalityChar(String[] selfDescrPersonalityChar) {
		this.selfDescrPersonalityChar = selfDescrPersonalityChar;
	}

	public String[] getInterests() {
		return interests;
	}

	public void setInterests(String[] interests) {
		this.interests = interests;
	}

	public String[] getCulturalOrFamilyNeeds() {
		return culturalOrFamilyNeeds;
	}

	public void setCulturalOrFamilyNeeds(String[] culturalOrFamilyNeeds) {
		this.culturalOrFamilyNeeds = culturalOrFamilyNeeds;
	}

	public String[] getSocialMediaProfiles() {
		return socialMediaProfiles;
	}

	public void setSocialMediaProfiles(String[] socialMediaProfiles) {
		this.socialMediaProfiles = socialMediaProfiles;
	}

	public Date getAlfedAppInstalationDate() {
		return alfedAppInstalationDate;
	}

	public void setAlfedAppInstalationDate(Date alfedAppInstalationDate) {
		this.alfedAppInstalationDate = alfedAppInstalationDate;
	}

	public MobilityLevel getMobilityLevel() {
		return mobilityLevel;
	}

	public void setMobilityLevel(MobilityLevel mobilityLevel) {
		this.mobilityLevel = mobilityLevel;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("UserProfile{");
		sb.append("id='").append(id).append('\'');
		sb.append(", firstName='").append(firstName).append('\'');
		sb.append(", middleName='").append(middleName).append('\'');
		sb.append(", lastName='").append(lastName).append('\'');
		sb.append(", alfredUserName='").append(alfredUserName).append('\'');
		sb.append(", prefferedName='").append(prefferedName).append('\'');
		sb.append(", gender=").append(gender);
		sb.append(", dateOfBirth=").append(dateOfBirth);
		sb.append(", phone='").append(phone).append('\'');
		sb.append(", mobilePhone='").append(mobilePhone).append('\'');
		sb.append(", email='").append(email).append('\'');
		sb.append(", residentialAddress=").append(residentialAddress);
		sb.append(", postalAddress=").append(postalAddress);
		sb.append(", citizenship='").append(citizenship).append('\'');
		sb.append(", nationality='").append(nationality).append('\'');
		sb.append(", language=").append(language);
		sb.append(", socialSecurityNumber='").append(socialSecurityNumber).append('\'');
		sb.append(", maritalStatus=").append(maritalStatus);
		sb.append(", educationLevel=").append(educationLevel);
		sb.append(", employmentStatus=").append(employmentStatus);
		sb.append(", healthInsurance='").append(healthInsurance).append('\'');
		sb.append(", profession='").append(profession).append('\'');
		sb.append(", anniversaryDate=").append(anniversaryDate);
		sb.append(", nextOfKin=").append(nextOfKin);
		sb.append(", myersBriggsIndicator=").append(myersBriggsIndicator);
		sb.append(", selfDescrPersonalityChar=").append(Arrays.toString(selfDescrPersonalityChar));
		sb.append(", interests=").append(Arrays.toString(interests));
		sb.append(", culturalOrFamilyNeeds=").append(Arrays.toString(culturalOrFamilyNeeds));
		sb.append(", socialMediaProfiles=").append(Arrays.toString(socialMediaProfiles));
		sb.append(", mobilityLevel=").append(mobilityLevel);
		sb.append(", lastUpdated=").append(lastUpdated);
		sb.append(", alfedAppInstalationDate=").append(alfedAppInstalationDate);
		sb.append('}');
		return sb.toString();
	}

	public String getAlfredUserName() {
		return alfredUserName;
	}

	public void setAlfredUserName(String alfredUserName) {
		this.alfredUserName = alfredUserName;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
