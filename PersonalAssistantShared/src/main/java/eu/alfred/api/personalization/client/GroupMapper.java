package eu.alfred.api.personalization.client;

import eu.alfred.api.personalization.model.Group;


public class GroupMapper {

	public static Group toModel(GroupDto groupDto) {

		Group group = new Group();
		group.setId(groupDto.id);
		group.setUserID(groupDto.userID);
		group.setMemberIds(groupDto.memberIds);
		group.setName(groupDto.name);
		group.setDescription(groupDto.description);
		group.setCreationDate(groupDto.creationDate);
		group.setLastUpdated(groupDto.lastUpdated);

		return group;
	}

	public static GroupDto toDto(Group group) {

		GroupDto groupDto = new GroupDto();
		groupDto.id = group.getId();
		groupDto.userID = group.getUserID();
		groupDto.memberIds = group.getMemberIds();
		groupDto.name = group.getName();
		groupDto.description = group.getDescription();
		groupDto.creationDate = group.getCreationDate();
		groupDto.lastUpdated = group.getLastUpdated();

		return groupDto;
	}
}
