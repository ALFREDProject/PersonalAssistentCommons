package eu.alfred.api.personalization.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Requester implements Serializable {

  private String id;
  private String requesterAlfredId;
  private String targetAlfredId;
  private Map<String,Boolean> accessRightsToAttributes;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getRequesterAlfredId() {
    return requesterAlfredId;
  }
  public void setRequesterAlfredId(String requesterAlfredId) {
    this.requesterAlfredId = requesterAlfredId;
  }
  public String getTargetAlfredId() {
    return targetAlfredId;
  }
  public void setTargetAlfredId(String targetAlfredId) {
    this.targetAlfredId = targetAlfredId;
  }
  public Map<String, Boolean> getAccessRightsToAttributes() {
    return accessRightsToAttributes;
  }
  public void setAccessRightsToAttributes(
          Map<String, Boolean> accessRightsToAttributes) {
    this.accessRightsToAttributes = accessRightsToAttributes;
  }
}
