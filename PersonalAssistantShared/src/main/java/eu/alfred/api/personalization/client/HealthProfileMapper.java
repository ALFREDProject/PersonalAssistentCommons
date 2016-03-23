package eu.alfred.api.personalization.client;

import eu.alfred.api.personalization.model.HealthProfile;
import eu.alfred.api.personalization.model.LengthUnit;
import eu.alfred.api.personalization.model.WeightUnit;

public class HealthProfileMapper {

	public static HealthProfile toModel(HealthProfileDto profileRequest) {

		HealthProfile profile = new HealthProfile(profileRequest.id);
		profile.setUserID(profileRequest.userID);
		profile.setBloodType(profileRequest.bloodType);
		profile.setWeight(profileRequest.weight, WeightUnit.KILOGRAM);
		profile.setHeight(profileRequest.height, LengthUnit.METER);
		profile.setChronicalConditions(profileRequest.chronicalConditions);
		profile.setVisionAcuityScore(profileRequest.visionAcuityScore);
		profile.setHearingAcuityScore(profileRequest.hearingAcuityScore);
		profile.setVisionCondition(profileRequest.visionCondition);
		profile.setHearingCondition(profileRequest.hearingCondition);
		profile.setKnownAllergies(profileRequest.knownAllergies);
		profile.setDietaryRequirements(profileRequest.dietaryRequirements);
		profile.setSurgeries(profileRequest.surgeries);
		profile.setDisabilities(profileRequest.disabilities);
		profile.setVisualAnalogScaleScore((float)profileRequest.visualAnalogScaleScore);
		profile.setMiniMentalStateExamScore((float)profileRequest.miniMentalStateExamScore);
		profile.setElderlyMobilityScaleScore((float)profileRequest.elderlyMobilityScaleScore);
		profile.setBergBalanceScaleScore((float)profileRequest.bergBalanceScaleScore);
		profile.setFiveTimesSitToStandScore(profileRequest.fiveTimesSitToStandScore);
		profile.setHanHeldDynamometerScore((float)profileRequest.hanHeldDynamometerScore);
		profile.setSixMinuteWalkingTestScore((float)profileRequest.sixMinuteWalkingTestScore);
		profile.setRangeOfMotionScore((float)profileRequest.rangeOfMotionScore);
		profile.setModifiedFallEfficiencyScaleScore((float)profileRequest.modifiedFallEfficiencyScaleScore);
		profile.setLowBackPainScore((float)profileRequest.lowBackPainScore);
		profile.setStepsPerDay(profileRequest.stepsPerDay);
		profile.setPulseAtRest((float)profileRequest.pulseAtRest);
		profile.setRespiratoryRateAtRest((float)profileRequest.respiratoryRateAtRest);
		profile.setBodyTemperatureAtRest((float)profileRequest.bodyTemperatureAtRest);
		profile.setLifestyleHealthRelated(profileRequest.lifestyleHealthRelated);
		profile.setSocietyParticipationScore(profileRequest.societyParticipationScore);
		profile.setCreationDate(profileRequest.creationDate);

		return profile;
	}

	public static HealthProfileDto toDto(HealthProfile profile) {

		HealthProfileDto profileRequest = new HealthProfileDto();
		profileRequest.id = profile.getId();
		profileRequest.userID = profile.getUserID();
		profileRequest.bloodType = profile.getBloodType();
		profileRequest.weight = profile.getWeight(WeightUnit.KILOGRAM);
		profileRequest.height = profile.getHeight(LengthUnit.METER);
		profileRequest.chronicalConditions = profile.getChronicalConditions();
		profileRequest.visionAcuityScore = profile.getVisionAcuityScore();
		profileRequest.hearingAcuityScore = profile.getHearingAcuityScore();
		profileRequest.visionCondition = profile.getVisionCondition();
		profileRequest.hearingCondition = profile.getHearingCondition();
		profileRequest.knownAllergies = profile.getKnownAllergies();
		profileRequest.dietaryRequirements = profile.getDietaryRequirements();
		profileRequest.surgeries = profile.getSurgeries();
		profileRequest.disabilities = profile.getDisabilities();
		profileRequest.visualAnalogScaleScore = profile.getVisualAnalogScaleScore();
		profileRequest.miniMentalStateExamScore = profile.getMiniMentalStateExamScore();
		profileRequest.elderlyMobilityScaleScore = profile.getElderlyMobilityScaleScore();
		profileRequest.bergBalanceScaleScore = profile.getBergBalanceScaleScore();
		profileRequest.fiveTimesSitToStandScore = profile.getFiveTimesSitToStandScore();
		profileRequest.hanHeldDynamometerScore = profile.getHanHeldDynamometerScore();
		profileRequest.sixMinuteWalkingTestScore = profile.getSixMinuteWalkingTestScore();
		profileRequest.rangeOfMotionScore = profile.getRangeOfMotionScore();
		profileRequest.modifiedFallEfficiencyScaleScore = profile.getModifiedFallEfficiencyScaleScore();
		profileRequest.lowBackPainScore = profile.getLowBackPainScore();
		profileRequest.stepsPerDay = profile.getStepsPerDay();
		profileRequest.pulseAtRest = profile.getPulseAtRest();
		profileRequest.respiratoryRateAtRest = profile.getRespiratoryRateAtRest();
		profileRequest.bodyTemperatureAtRest = profile.getBodyTemperatureAtRest();
		profileRequest.lifestyleHealthRelated = profile.getLifestyleHealthRelated();
		profileRequest.societyParticipationScore = profile.getSocietyParticipationScore();
		profileRequest.creationDate = profile.getCreationDate();

		return profileRequest;
	}
}
