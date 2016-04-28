package eu.alfred.api.personalization.model.eventrecommendation;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true) 
public class Tickets {

	@SerializedName("at_event")
	private Event at_event;
	
	@SerializedName("end_date")
	private Date end_date;

	@SerializedName("include_fee")
	private int include_fee;

	@SerializedName("min")
	private int min;

	@SerializedName("max")
	private int max;

	@SerializedName("visible")
	private int visible;

	@SerializedName("price")
	private double price;

	@SerializedName("name")
	private String name;
	@SerializedName("currency")
	private String currency;
	@SerializedName("description")
	private String description;
	
	public Event getAt_event() {
		return at_event;
	}
	public void setAt_event(Event at_event) {
		this.at_event = at_event;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public int getInclude_fee() {
		return include_fee;
	}
	public void setInclude_fee(int include_fee) {
		this.include_fee = include_fee;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
