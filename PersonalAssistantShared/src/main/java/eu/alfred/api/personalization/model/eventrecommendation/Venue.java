package eu.alfred.api.personalization.model.eventrecommendation;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true) 
public class Venue implements java.io.Serializable{

	private String name;
	private String postal_code;
	private String address;//Why not Address()??
	private String address_2;
	private String city;
	private String country;
	private String country_code;
	private double latitude;
	private double longtitude;
	public Venue()
	{
		super();
		this.name = "";
		this.postal_code = "";
		this.address = "";
		this.address_2 = "";
		this.city = "";
		this.country = "";
		this.country_code = "";
		this.latitude =0.0;
		this.longtitude = 0.0;
	}
	public Venue(String name, String postal_code, String address, String address_2, String city, String country, String country_code, double latitude, double longtitude) {
		super();
		this.name = name;
		this.postal_code = postal_code;
		this.address = address;
		this.address_2 = address_2;
		this.city = city;
		this.country = country;
		this.country_code = country_code;
		this.latitude = latitude;
		this.longtitude = longtitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress_2() {
		return address_2;
	}
	public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(double longitude) {
		this.longtitude = longitude;
	}

	public String toString()
	{
		return ""+this.getAddress()+", "+this.getPostal_code()+" "+this.getCity()+", "+this.getCountry();
	}
}
