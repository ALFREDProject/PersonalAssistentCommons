package eu.alfred.api.personalization.model;

import java.util.Date;
import java.util.Set;

public class Group {

  private String id;
  private String userID;
  private Set<String> memberIds;
  private String name;
  private String description;
  private Date creationDate;
  private Date lastUpdated;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getUserID() {
    return userID;
  }
  public void setUserID(String userID) {
    this.userID = userID;
  }
  public Set<String> getMemberIds() {
    return memberIds;
  }
  public void setMemberIds(Set<String> memberIds) {
    this.memberIds = memberIds;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  public Date getCreationDate() {
    return creationDate;
  }
  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }
  public Date getLastUpdated() {
    return lastUpdated;
  }
  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }
}
