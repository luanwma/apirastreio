package utfpr.trabalho.api.model.tracking;

import utfpr.trabalho.api.model.event.Event;
import utfpr.trabalho.api.model.location.Location;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record TrackingResponseDTO(String codeTracking, String description, LocalDateTime createdDateTime, String type,
                                  Location currentLocation, Location destinyLocation , ArrayList<Event> listEvents) {





}
