package utfpr.trabalho.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.trabalho.api.model.event.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {
    Optional<Event> findAllById(Integer id);
}
