package eu.alfred.api.personalization.client;

import java.util.Date;

import eu.alfred.api.personalization.model.Group;


public class GroupMapper {

	public static Group toModel(GroupDto groupDto) {

		Group group = new Group();
		group.setId(groupDto.id);
		group.setUserID(groupDto.userID);
		group.setMemberIds(groupDto.memberIds);
		group.setName(groupDto.name);
		group.setDescription(groupDto.description);
		group.setCreationDate(date(groupDto.creationDate));
		group.setLastUpdated(date(groupDto.lastUpdated));

		return group;
	}

	public static GroupDto toDto(Group group) {

		GroupDto groupDto = new GroupDto();
		groupDto.id = group.getId();
		groupDto.userID = group.getUserID();
		groupDto.memberIds = group.getMemberIds();
		groupDto.name = group.getName();
		groupDto.description = group.getDescription();
		groupDto.creationDate = time(group.getCreationDate());
		groupDto.lastUpdated = time(group.getLastUpdated());

		return groupDto;
	}

	private static String time(Date date) {
		if (date == null) return "0";
		return Long.toString(date.getTime());
	}

	private static Date date(String time) {
		if (time == null) return null;
		long tl = 0L;
		try {
			tl = Long.parseLong(time);
		}
		catch (NumberFormatException e) {
			return null;
		}
		return new Date(tl);
	}
}
