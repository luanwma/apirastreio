package utfpr.trabalho.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utfpr.trabalho.api.model.event.Event;
import utfpr.trabalho.api.repository.EventRepository;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/tracking/event")
public class EventControler {

    private final EventRepository eventRepository;

    @GetMapping("/getallevents/{id}")
    public ResponseEntity<Optional<Event>> showAllEvents(@PathVariable Integer id){
        return ResponseEntity.ok(eventRepository.findAllById(id));
    }

}
