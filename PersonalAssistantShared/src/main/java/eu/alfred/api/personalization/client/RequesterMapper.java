package eu.alfred.api.personalization.client;


import eu.alfred.api.personalization.model.Requester;

public class RequesterMapper {

	public static Requester toModel(RequesterDto requesterDto) {

		Requester requester = new Requester();
		requester.setId(requesterDto.id);
		requester.setAccessRightsToAttributes(requesterDto.accessRightsToAttributes);
		requester.setRequesterAlfredId(requesterDto.requesterAlfredId);
		requester.setTargetAlfredId(requesterDto.targetAlfredId);

		return requester;
	}

	public static RequesterDto toDto(Requester requester) {

		RequesterDto requesterDto = new RequesterDto();
		requesterDto.id = requester.getId();
		requesterDto.accessRightsToAttributes = requester.getAccessRightsToAttributes();
		requesterDto.requesterAlfredId = requester.getRequesterAlfredId();
		requesterDto.targetAlfredId = requester.getTargetAlfredId();

		return requesterDto;
	}
}
