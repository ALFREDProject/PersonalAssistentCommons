package eu.alfred.api.personalization.client;

import com.google.gson.annotations.Expose;

import java.util.Map;

public class RequesterDto {

	@Expose
	public Map<String, Boolean> accessRightsToAttributes;
	@Expose
	public String id;
	@Expose
	public String requesterAlfredId;
	@Expose
	public String targetAlfredId;

}

