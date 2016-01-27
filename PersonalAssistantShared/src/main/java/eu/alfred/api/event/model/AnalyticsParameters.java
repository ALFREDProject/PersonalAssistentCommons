package eu.alfred.api.event.model;

import java.util.List;

import eu.alfred.api.personalization.model.*;


public class AnalyticsParameters {
  
	private Integer page;
	private Integer page_size;
	private Boolean olap;
	private Integer age_from;
	private Integer age_to;
	private Gender gender;
	private List<MaritalStatus> marital_status;
	private List<MobilityLevel> mobility_level;
	private List<String> postal_code;	//Why a list?
	private String address;
	private List<String> city;
	private List<String> country;	  //Redundant?
	private List<String> country_code;//Redundant?
	private List<String> organiser;
	private Integer min_participant_count;  
	private Integer max_participant_count;  
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPage_size() {
		return page_size;
	}
	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}
	public Boolean getOlap() {
		return olap;
	}
	public void setOlap(Boolean olap) {
		this.olap = olap;
	}
	private Integer age_min_average_age;  
	private Integer age_max_average_age;  
	private Integer gender_min_percent_male; 
	private Integer gender_max_percent_male;  
	private Integer gender_min_percent_female;  
	private Integer gender_max_percent_female;  
	private Integer mobility_min_percent_wheelchair;  
	private Integer mobility_min_percent_rollator;  
	private Integer mobility_max_percent_rollator;  
	private Integer mobility_min_percent_cane;  
	private Integer mobility_max_percent_cane;  
	private Integer mobility_min_percent_healthy;  
	private Integer mobility_max_percent_healthy;  
	private Integer mobility_min_percent_fit;  
	private Integer mobility_max_percent_fit;  
	private Integer marital_min_percent_single; 
	private Integer marital_max_percent_single;  
	private Integer marital_min_percent_married;  
	private Integer marital_max_percent_married;  
	private Integer marital_min_percent_widow_widower;  
	private Integer marital_max_percent_widow_widower;  
	private Integer education_min_percent_bachelor;  
	private Integer education_max_percent_bachelor; 
	private Integer education_min_percent_doctoral;  
	private Integer education_max_percent_doctoral;  
	private Integer education_min_percent_lo_secondary;  
	private Integer education_max_percent_lo_secondary; 
	private Integer education_min_percent_master;  
	private Integer education_max_percent_master;  
	private Integer education_min_percent_post_secondary; 
	private Integer education_max_percent_post_secondary;  
	private Integer education_min_percent_primary;  
	private Integer education_max_percent_primary;  
	private Integer education_min_percent_tertiary;  
	private Integer education_max_percent_tertiary;  
	private Integer education_min_percent_up_secondary;  
	private Integer education_max_percent_up_secondary;
	private Long min_start_date;
	private Long max_start_date;
	private List<String> participants;
	private List<String> tags;
	private List<String> categories;
	
	public Integer getAge_from() {
		return age_from;
	}
	public void setAge_from(Integer age_from) {
		this.age_from = age_from;
	}
	public Integer getAge_to() {
		return age_to;
	}
	public void setAge_to(Integer age_to) {
		this.age_to = age_to;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public List<MaritalStatus> getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(List<MaritalStatus> marital_status) {
		this.marital_status = marital_status;
	}
	public List<MobilityLevel> getMobility_level() {
		return mobility_level;
	}
	public void setMobility_level(List<MobilityLevel> mobility_level) {
		this.mobility_level = mobility_level;
	}
	public List<String> getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(List<String> postal_code) {
		this.postal_code = postal_code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<String> getCity() {
		return city;
	}
	public void setCity(List<String> city) {
		this.city = city;
	}
	public List<String> getCountry() {
		return country;
	}
	public void setCountry(List<String> country) {
		this.country = country;
	}
	public List<String> getCountry_code() {
		return country_code;
	}
	public void setCountry_code(List<String> country_code) {
		this.country_code = country_code;
	}
	public List<String> getOrganiser() {
		return organiser;
	}
	public void setOrganiser(List<String> organiser) {
		this.organiser = organiser;
	}
	public Integer getMin_participant_count() {
		return min_participant_count;
	}
	public void setMin_participant_count(Integer min_participant_count) {
		this.min_participant_count = min_participant_count;
	}
	public Integer getMax_participant_count() {
		return max_participant_count;
	}
	public void setMax_participant_count(Integer max_participant_count) {
		this.max_participant_count = max_participant_count;
	}
	public Integer getAge_min_average_age() {
		return age_min_average_age;
	}
	public void setAge_min_average_age(Integer age_min_average_age) {
		this.age_min_average_age = age_min_average_age;
	}
	public Integer getAge_max_average_age() {
		return age_max_average_age;
	}
	public void setAge_max_average_age(Integer age_max_average_age) {
		this.age_max_average_age = age_max_average_age;
	}
	public Integer getGender_min_percent_male() {
		return gender_min_percent_male;
	}
	public void setGender_min_percent_male(Integer gender_min_percent_male) {
		this.gender_min_percent_male = gender_min_percent_male;
	}
	public Integer getGender_max_percent_male() {
		return gender_max_percent_male;
	}
	public void setGender_max_percent_male(Integer gender_max_percent_male) {
		this.gender_max_percent_male = gender_max_percent_male;
	}
	public Integer getGender_min_percent_female() {
		return gender_min_percent_female;
	}
	public void setGender_min_percent_female(Integer gender_min_percent_female) {
		this.gender_min_percent_female = gender_min_percent_female;
	}
	public Integer getGender_max_percent_female() {
		return gender_max_percent_female;
	}
	public void setGender_max_percent_female(Integer gender_max_percent_female) {
		this.gender_max_percent_female = gender_max_percent_female;
	}
	public Integer getMobility_min_percent_wheelchair() {
		return mobility_min_percent_wheelchair;
	}
	public void setMobility_min_percent_wheelchair(
			Integer mobility_min_percent_wheelchair) {
		this.mobility_min_percent_wheelchair = mobility_min_percent_wheelchair;
	}
	public Integer getMobility_min_percent_rollator() {
		return mobility_min_percent_rollator;
	}
	public void setMobility_min_percent_rollator(Integer mobility_min_percent_rollator) {
		this.mobility_min_percent_rollator = mobility_min_percent_rollator;
	}
	public Integer getMobility_max_percent_rollator() {
		return mobility_max_percent_rollator;
	}
	public void setMobility_max_percent_rollator(Integer mobility_max_percent_rollator) {
		this.mobility_max_percent_rollator = mobility_max_percent_rollator;
	}
	public Integer getMobility_min_percent_cane() {
		return mobility_min_percent_cane;
	}
	public void setMobility_min_percent_cane(Integer mobility_min_percent_cane) {
		this.mobility_min_percent_cane = mobility_min_percent_cane;
	}
	public Integer getMobility_max_percent_cane() {
		return mobility_max_percent_cane;
	}
	public void setMobility_max_percent_cane(Integer mobility_max_percent_cane) {
		this.mobility_max_percent_cane = mobility_max_percent_cane;
	}
	public Integer getMobility_min_percent_healthy() {
		return mobility_min_percent_healthy;
	}
	public void setMobility_min_percent_healthy(Integer mobility_min_percent_healthy) {
		this.mobility_min_percent_healthy = mobility_min_percent_healthy;
	}
	public Integer getMobility_max_percent_healthy() {
		return mobility_max_percent_healthy;
	}
	public void setMobility_max_percent_healthy(Integer mobility_max_percent_healthy) {
		this.mobility_max_percent_healthy = mobility_max_percent_healthy;
	}
	public Integer getMobility_min_percent_fit() {
		return mobility_min_percent_fit;
	}
	public void setMobility_min_percent_fit(Integer mobility_min_percent_fit) {
		this.mobility_min_percent_fit = mobility_min_percent_fit;
	}
	public Integer getMobility_max_percent_fit() {
		return mobility_max_percent_fit;
	}
	public void setMobility_max_percent_fit(Integer mobility_max_percent_fit) {
		this.mobility_max_percent_fit = mobility_max_percent_fit;
	}
	public Integer getMartial_min_percent_single() {
		return marital_min_percent_single;
	}
	public void setMartial_min_percent_single(Integer martial_min_percent_single) {
		this.marital_min_percent_single = martial_min_percent_single;
	}
	
	public Integer getMarital_min_percent_single() {
		return marital_min_percent_single;
	}
	public void setMarital_min_percent_single(Integer marital_min_percent_single) {
		this.marital_min_percent_single = marital_min_percent_single;
	}
	public Integer getMarital_max_percent_single() {
		return marital_max_percent_single;
	}
	public void setMarital_max_percent_single(Integer marital_max_percent_single) {
		this.marital_max_percent_single = marital_max_percent_single;
	}
	public Integer getMarital_min_percent_married() {
		return marital_min_percent_married;
	}
	public void setMarital_min_percent_married(Integer marital_min_percent_married) {
		this.marital_min_percent_married = marital_min_percent_married;
	}
	public Integer getMarital_max_percent_married() {
		return marital_max_percent_married;
	}
	public void setMarital_max_percent_married(Integer marital_max_percent_married) {
		this.marital_max_percent_married = marital_max_percent_married;
	}
	public Integer getMarital_min_percent_widow_widower() {
		return marital_min_percent_widow_widower;
	}
	public void setMarital_min_percent_widow_widower(
			Integer marital_min_percent_widow_widower) {
		this.marital_min_percent_widow_widower = marital_min_percent_widow_widower;
	}
	public Integer getMarital_max_percent_widow_widower() {
		return marital_max_percent_widow_widower;
	}
	public void setMarital_max_percent_widow_widower(
			Integer marital_max_percent_widow_widower) {
		this.marital_max_percent_widow_widower = marital_max_percent_widow_widower;
	}
	public Integer getEducation_min_percent_bachelor() {
		return education_min_percent_bachelor;
	}
	public void setEducation_min_percent_bachelor(Integer education_min_percent_bachelor) {
		this.education_min_percent_bachelor = education_min_percent_bachelor;
	}
	public Integer getEducation_max_percent_bachelor() {
		return education_max_percent_bachelor;
	}
	public void setEducation_max_percent_bachelor(Integer education_max_percent_bachelor) {
		this.education_max_percent_bachelor = education_max_percent_bachelor;
	}
	public Integer getEducation_min_percent_doctoral() {
		return education_min_percent_doctoral;
	}
	public void setEducation_min_percent_doctoral(Integer education_min_percent_doctoral) {
		this.education_min_percent_doctoral = education_min_percent_doctoral;
	}
	public Integer getEducation_max_percent_doctoral() {
		return education_max_percent_doctoral;
	}
	public void setEducation_max_percent_doctoral(Integer education_max_percent_doctoral) {
		this.education_max_percent_doctoral = education_max_percent_doctoral;
	}
	public Integer getEducation_min_percent_lo_secondary() {
		return education_min_percent_lo_secondary;
	}
	public void setEducation_min_percent_lo_secondary(
			Integer education_min_percent_lo_secondary) {
		this.education_min_percent_lo_secondary = education_min_percent_lo_secondary;
	}
	public Integer getEducation_max_percent_lo_secondary() {
		return education_max_percent_lo_secondary;
	}
	public void setEducation_max_percent_lo_secondary(
			Integer education_max_percent_lo_secondary) {
		this.education_max_percent_lo_secondary = education_max_percent_lo_secondary;
	}
	public Integer getEducation_min_percent_master() {
		return education_min_percent_master;
	}
	public void setEducation_min_percent_master(Integer education_min_percent_master) {
		this.education_min_percent_master = education_min_percent_master;
	}
	public Integer getEducation_max_percent_master() {
		return education_max_percent_master;
	}
	public void setEducation_max_percent_master(Integer education_max_percent_master) {
		this.education_max_percent_master = education_max_percent_master;
	}
	public Integer getEducation_min_percent_post_secondary() {
		return education_min_percent_post_secondary;
	}
	public void setEducation_min_percent_post_secondary(
			Integer education_min_percent_post_secondary) {
		this.education_min_percent_post_secondary = education_min_percent_post_secondary;
	}
	public Integer getEducation_max_percent_post_secondary() {
		return education_max_percent_post_secondary;
	}
	public void setEducation_max_percent_post_secondary(
			Integer education_max_percent_post_secondary) {
		this.education_max_percent_post_secondary = education_max_percent_post_secondary;
	}
	public Integer getEducation_min_percent_primary() {
		return education_min_percent_primary;
	}
	public void setEducation_min_percent_primary(Integer education_min_percent_primary) {
		this.education_min_percent_primary = education_min_percent_primary;
	}
	public Integer getEducation_max_percent_primary() {
		return education_max_percent_primary;
	}
	public void setEducation_max_percent_primary(Integer education_max_percent_primary) {
		this.education_max_percent_primary = education_max_percent_primary;
	}
	public Integer getEducation_min_percent_tertiary() {
		return education_min_percent_tertiary;
	}
	public void setEducation_min_percent_tertiary(Integer education_min_percent_tertiary) {
		this.education_min_percent_tertiary = education_min_percent_tertiary;
	}
	public Integer getEducation_max_percent_tertiary() {
		return education_max_percent_tertiary;
	}
	public void setEducation_max_percent_tertiary(Integer education_max_percent_tertiary) {
		this.education_max_percent_tertiary = education_max_percent_tertiary;
	}
	public Integer getEducation_min_percent_up_secondary() {
		return education_min_percent_up_secondary;
	}
	public void setEducation_min_percent_up_secondary(
			Integer education_min_percent_up_secondary) {
		this.education_min_percent_up_secondary = education_min_percent_up_secondary;
	}
	public Integer getEducation_max_percent_up_secondary() {
		return education_max_percent_up_secondary;
	}
	public void setEducation_max_percent_up_secondary(
			Integer education_max_percent_up_secondary) {
		this.education_max_percent_up_secondary = education_max_percent_up_secondary;
	}
	public Long getMin_start_date() {
		return min_start_date;
	}
	public void setMin_start_date(Long min_start_date) {
		this.min_start_date = min_start_date;
	}
	public Long getMax_start_date() {
		return max_start_date;
	}
	public void setMax_start_date(Long max_start_date) {
		this.max_start_date = max_start_date;
	}
	public List<String> getParticipants() {
		return participants;
	}
	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	} 
	
}
