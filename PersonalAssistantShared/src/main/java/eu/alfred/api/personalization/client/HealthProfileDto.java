package eu.alfred.api.personalization.client;

import com.google.gson.annotations.Expose;

import java.util.Date;

import eu.alfred.api.personalization.model.BloodType;
import eu.alfred.api.personalization.model.HearingAcuityScale;
import eu.alfred.api.personalization.model.LifestyleHealthRelated;
import eu.alfred.api.personalization.model.SocietyParticipationScale;
import eu.alfred.api.personalization.model.VisionAcuityScale;

public class HealthProfileDto {

	@Expose
	public String id;
	@Expose
	public String userID;
	@Expose
	public BloodType bloodType;
	@Expose
	public double weight;
	@Expose
	public double height;
	@Expose
	public String[] chronicalConditions;
	@Expose
	public VisionAcuityScale visionAcuityScore;
	@Expose
	public HearingAcuityScale hearingAcuityScore;
	@Expose
	public boolean visionCondition;
	@Expose
	public boolean hearingCondition;
	@Expose
	public String[] knownAllergies;
	@Expose
	public String[] dietaryRequirements;
	@Expose
	public String[] surgeries;
	@Expose
	public String[] disabilities;
	@Expose
	public double visualAnalogScaleScore;
	@Expose
	public double miniMentalStateExamScore;
	@Expose
	public double elderlyMobilityScaleScore;
	@Expose
	public double bergBalanceScaleScore;
	@Expose
	public int fiveTimesSitToStandScore;
	@Expose
	public double hanHeldDynamometerScore;
	@Expose
	public double sixMinuteWalkingTestScore;
	@Expose
	public double rangeOfMotionScore;
	@Expose
	public double modifiedFallEfficiencyScaleScore;
	@Expose
	public double lowBackPainScore;
	@Expose
	public int stepsPerDay;
	@Expose
	public double pulseAtRest;
	@Expose
	public double respiratoryRateAtRest;
	@Expose
	public double bodyTemperatureAtRest;
	@Expose
	public LifestyleHealthRelated[] lifestyleHealthRelated;
	@Expose
	public SocietyParticipationScale societyParticipationScore;
	@Expose
	public Date creationDate;

}
