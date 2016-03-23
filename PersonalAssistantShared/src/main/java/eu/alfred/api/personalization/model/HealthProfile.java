package eu.alfred.api.personalization.model;

import java.util.Date;
import java.util.UUID;

public class HealthProfile {

  private String id;
  private String userID;
  private BloodType bloodType;
  private double weight;
  private double height;
  private String[] chronicalConditions;
  private VisionAcuityScale visionAcuityScore;
  private HearingAcuityScale hearingAcuityScore;
  private boolean visionCondition;
  private boolean hearingCondition;
  private String[] knownAllergies;
  private String[] dietaryRequirements;
  private String[] surgeries;
  private String[] disabilities;
  private double visualAnalogScaleScore;
  private double miniMentalStateExamScore;
  private double elderlyMobilityScaleScore;
  private double bergBalanceScaleScore;
  private int fiveTimesSitToStandScore;
  private double hanHeldDynamometerScore;
  private double sixMinuteWalkingTestScore;
  private double rangeOfMotionScore;
  private double modifiedFallEfficiencyScaleScore;
  private double lowBackPainScore;
  private int stepsPerDay;
  private double pulseAtRest;
  private double respiratoryRateAtRest;
  private double bodyTemperatureAtRest;
  private LifestyleHealthRelated[] lifestyleHealthRelated;
  private SocietyParticipationScale societyParticipationScore;
  private Date creationDate;


  public HealthProfile () {
    setId();
    setCreationDate();
  }
  public HealthProfile (String id) {
    this.id = id;
    setCreationDate();
  }

  public String getId() {
    return id;
  }
  private void setId() {
    this.id = "alfred-user-healthProfile-"+UUID.randomUUID().toString();
  }
  public String getUserID() {
    return userID;
  }
  public void setUserID(String userID) {
    this.userID = userID;
  }

  public BloodType getBloodType() {
    return bloodType;
  }
  public void setBloodType(BloodType bloodType) {
    this.bloodType = bloodType;
  }
  public double getWeight(WeightUnit unit) {
    switch (unit) {
      case GRAM:
        return weight * 1000;
      case POUND:
        return weight * 2.20462;
      case KILOGRAM:
        break;
    }
    return weight;
  }
  public void setWeight(double weight, WeightUnit unit) {
    switch (unit) {
      case KILOGRAM:
        this.weight = weight;
        break;
      case GRAM:
        this.weight = weight / 1000;
        break;
      case POUND:
        this.weight = weight / 2.20462;
        break;
    }
  }
  public double getHeight(LengthUnit unit) {
    switch (unit) {
      case CENTIMETER:
        return height * 100;
      case INCH:
        return height * 0.0254;
      case METER:
    }
    return height;
  }
  public void setHeight(double height, LengthUnit unit) {
    switch (unit) {
      case METER:
        this.height = height;
        break;
      case CENTIMETER:
        this.height = height / 100;
        break;
      case INCH:
        this.height = height / 0.0254;
        break;
    }
  }

  public double getBMI() {
    // weight must be in kilogram, height must be in meters
    return weight / (height * height);
  }
  public BMICategory getBMICategory() {
    return BMICategory.getBMICategoryForValue(getBMI());
  }

  public String[] getChronicalConditions() {
    return chronicalConditions;
  }
  public void setChronicalConditions(String[] chronicalConditions) {
    this.chronicalConditions = chronicalConditions;
  }
  public VisionAcuityScale getVisionAcuityScore() {
    return visionAcuityScore;
  }
  public void setVisionAcuityScore(VisionAcuityScale visionAcuityScore) {
    this.visionAcuityScore = visionAcuityScore;
  }
  public HearingAcuityScale getHearingAcuityScore() {
    return hearingAcuityScore;
  }
  public void setHearingAcuityScore(HearingAcuityScale hearingAcuityScore) {
    this.hearingAcuityScore = hearingAcuityScore;
  }
  public boolean getVisionCondition() {
    return visionCondition;
  }
  public void setVisionCondition(boolean visionCondition) {
    this.visionCondition = visionCondition;
  }
  public boolean getHearingCondition() {
    return hearingCondition;
  }
  public void setHearingCondition(boolean hearingCondition) {
    this.hearingCondition = hearingCondition;
  }
  public String[] getKnownAllergies() {
    return knownAllergies;
  }
  public void setKnownAllergies(String[] knownAllergies) {
    this.knownAllergies = knownAllergies;
  }
  public String[] getDietaryRequirements() {
    return dietaryRequirements;
  }
  public void setDietaryRequirements(String[] dietaryRequirements) {
    this.dietaryRequirements = dietaryRequirements;
  }
  public String[] getSurgeries() {
    return surgeries;
  }
  public void setSurgeries(String[] surgeries) {
    this.surgeries = surgeries;
  }
  public String[] getDisabilities() {
    return disabilities;
  }
  public void setDisabilities(String[] disabilities) {
    this.disabilities = disabilities;
  }
  public double getVisualAnalogScaleScore() {
    return visualAnalogScaleScore;
  }
  public void setVisualAnalogScaleScore(double visualAnalogScaleScore) {
    this.visualAnalogScaleScore = visualAnalogScaleScore;
  }
  public double getMiniMentalStateExamScore() {
    return miniMentalStateExamScore;
  }

  public void setMiniMentalStateExamScore(double miniMentalStateExamScore) {
    this.miniMentalStateExamScore = miniMentalStateExamScore;
  }
  public double getElderlyMobilityScaleScore() {
    return elderlyMobilityScaleScore;
  }
  public void setElderlyMobilityScaleScore(double elderlyMobilityScaleScore) {
    this.elderlyMobilityScaleScore = elderlyMobilityScaleScore;
  }
  public double getBergBalanceScaleScore() {
    return bergBalanceScaleScore;
  }
  public void setBergBalanceScaleScore(double bergBalanceScaleScore) {
    this.bergBalanceScaleScore = bergBalanceScaleScore;
  }
  public int getFiveTimesSitToStandScore() {
    return fiveTimesSitToStandScore;
  }
  public void setFiveTimesSitToStandScore(int fiveTimesSitToStandScore) {
    this.fiveTimesSitToStandScore = fiveTimesSitToStandScore;
  }
  public double getHanHeldDynamometerScore() {
    return hanHeldDynamometerScore;
  }
  public void setHanHeldDynamometerScore(double hanHeldDynamometerScore) {
    this.hanHeldDynamometerScore = hanHeldDynamometerScore;
  }
  public double getSixMinuteWalkingTestScore() {
    return sixMinuteWalkingTestScore;
  }
  public void setSixMinuteWalkingTestScore(double sixMinuteWalkingTestScore) {
    this.sixMinuteWalkingTestScore = sixMinuteWalkingTestScore;
  }
  public double getRangeOfMotionScore() {
    return rangeOfMotionScore;
  }
  public void setRangeOfMotionScore(double rangeOfMotionScore) {
    this.rangeOfMotionScore = rangeOfMotionScore;
  }
  public double getModifiedFallEfficiencyScaleScore() {
    return modifiedFallEfficiencyScaleScore;
  }
  public void setModifiedFallEfficiencyScaleScore(
          double modifiedFallEfficiencyScaleScore) {
    this.modifiedFallEfficiencyScaleScore = modifiedFallEfficiencyScaleScore;
  }
  public double getLowBackPainScore() {
    return lowBackPainScore;
  }
  public void setLowBackPainScore(double lowBackPainScore) {
    this.lowBackPainScore = lowBackPainScore;
  }
  public int getStepsPerDay() {
    return stepsPerDay;
  }
  public void setStepsPerDay(int stepsPerDay) {
    this.stepsPerDay = stepsPerDay;
  }
  public double getPulseAtRest() {
    return pulseAtRest;
  }
  public void setPulseAtRest(double pulseAtRest) {
    this.pulseAtRest = pulseAtRest;
  }
  public double getRespiratoryRateAtRest() {
    return respiratoryRateAtRest;
  }
  public void setRespiratoryRateAtRest(double respiratoryRateAtRest) {
    this.respiratoryRateAtRest = respiratoryRateAtRest;
  }
  public double getBodyTemperatureAtRest() {
    return bodyTemperatureAtRest;
  }
  public void setBodyTemperatureAtRest(double bodyTemperatureAtRest) {
    this.bodyTemperatureAtRest = bodyTemperatureAtRest;
  }
  public LifestyleHealthRelated[] getLifestyleHealthRelated() {
    return lifestyleHealthRelated;
  }
  public void setLifestyleHealthRelated(
          LifestyleHealthRelated[] lifestyleHealthRelated) {
    this.lifestyleHealthRelated = lifestyleHealthRelated;
  }
  public SocietyParticipationScale getSocietyParticipationScore() {
    return societyParticipationScore;
  }
  public void setSocietyParticipationScore(
          SocietyParticipationScale societyParticipationScore) {
    this.societyParticipationScore = societyParticipationScore;
  }


  public Date getCreationDate() {
    return creationDate;
  }

  private void setCreationDate() {
    this.creationDate = new Date();
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("HealthProfile{");
    sb.append("id='").append(id).append('\'');
    sb.append(", userID='").append(userID).append('\'');
    sb.append(", creationDate=").append(creationDate);
    sb.append(", bloodType=").append(bloodType);
    sb.append(", weight=").append(weight);
    sb.append(", height=").append(height);
    sb.append('}');
    return sb.toString();
  }
}
