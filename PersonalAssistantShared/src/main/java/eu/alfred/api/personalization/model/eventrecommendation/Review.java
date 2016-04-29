package eu.alfred.api.personalization.model.eventrecommendation;

public class Review implements java.io.Serializable{

	private String eventid;
	private String reviewerid;
	private String score;//int
	/**
	* Maybe use a bitfield java enums
	* */
	private boolean cane_friendly;
	private boolean wheelchair_friendly;
	private boolean rollator_friendly;
	private boolean healthy_friendly;
	private String remark;
	
	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventid == null) ? 0 : eventid.hashCode());
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
		Review other = (Review) obj;
		if (eventid == null) {
			if (other.eventid != null)
				return false;
		} else if (!eventid.equals(other.eventid))
			return false;
		return true;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public boolean isCane_friendly() {
		return cane_friendly;
	}
	public void setCane_friendly(boolean cane_friendly) {
		this.cane_friendly = cane_friendly;
	}
	public boolean isWheelchair_friendly() {
		return wheelchair_friendly;
	}
	public void setWheelchair_friendly(boolean wheelchair_friendly) {
		this.wheelchair_friendly = wheelchair_friendly;
	}
	public boolean isRollator_friendly() {
		return rollator_friendly;
	}
	public void setRollator_friendly(boolean rollator_friendly) {
		this.rollator_friendly = rollator_friendly;
	}
	public boolean isHealthy_friendly() {
		return healthy_friendly;
	}
	public void setHealthy_friendly(boolean healthy_friendly) {
		this.healthy_friendly = healthy_friendly;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReviewerid() {
		return reviewerid;
	}
	public void setReviewerid(String reviewerid) {
		this.reviewerid = reviewerid;
	}
		
	
}
