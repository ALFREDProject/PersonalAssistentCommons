package eu.alfred.api.personalization.client.eventrecommendation;

import eu.alfred.api.personalization.model.eventrecommendation.Eventrating;

/**
 * Created by thardes on 20/04/2016.
 */
public class EventratingMapper {
    public static Eventrating toModel(EventratingDto eventratingDto) {
        return new Eventrating(eventratingDto.id.isAccepted(),eventratingDto.id.getRating(),eventratingDto.id.getEventId());
    }
    public static EventratingDto toDto(Eventrating rating) {
        EventratingDto dto = new EventratingDto();
        dto.id = rating;
        return dto;
    }
}
