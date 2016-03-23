package eu.alfred.api.personalization.client;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.Set;

public class GroupDto {

	@Expose
	public String id;
	@Expose
	public String userID;
	@Expose
	public Set<String> memberIds;
	@Expose
	public String name;
	@Expose
	public String description;
	@Expose
	public Date creationDate;
	@Expose
	public Date lastUpdated;

}
