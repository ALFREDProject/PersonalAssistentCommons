package eu.alfred.api.personalization.client;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;

import eu.alfred.api.personalization.model.Address;
import eu.alfred.api.personalization.model.Contact;
import eu.alfred.api.personalization.model.EducationLevel;
import eu.alfred.api.personalization.model.EmploymentStatus;
import eu.alfred.api.personalization.model.Gender;
import eu.alfred.api.personalization.model.Language;
import eu.alfred.api.personalization.model.MaritalStatus;
import eu.alfred.api.personalization.model.MobilityLevel;
import eu.alfred.api.personalization.model.MyersBriggsTypeIndicator;

public class UserProfileDto {
	@Expose
	public Date alfedAppInstalationDate;
	@Expose
	public String alfredUserName;
	@Expose
	public Date anniversaryDate;
	@Expose
	public String citizenship;
	@Expose
	public List<String> culturalOrFamilyNeeds;
	@Expose
	public Date dateOfBirth;
	@Expose
	public EducationLevel educationLevel;
	@Expose
	public String email;
	@Expose
	public EmploymentStatus employmentStatus;
	@Expose
	public String firstName;
	@Expose
	public Gender gender;
	@Expose
	public String healthInsurance;
	@Expose
	public String id;
	@Expose
	public List<String> interests;
	@Expose
	public Language language;
	@Expose
	public String lastName;
	@Expose
	public Date lastUpdated;
	@Expose
	public MaritalStatus maritalStatus;
	@Expose
	public String middleName;
	@Expose
	public String mobilePhone;
	@Expose
	public MobilityLevel mobilityLevel;
	@Expose
	public MyersBriggsTypeIndicator myersBriggsIndicator;
	@Expose
	public String nationality;
	@Expose
	public Contact nextOfKin;
	@Expose
	public String phone;
	@Expose
	public Address postalAddress;
	@Expose
	public String prefferedName;
	@Expose
	public String profession;
	@Expose
	public Address residentialAddress;
	@Expose
	public List<String> selfDescrPersonalityChar;
	@Expose
	public List<String> socialMediaProfiles;
	@Expose
	public String socialSecurityNumber;
}
