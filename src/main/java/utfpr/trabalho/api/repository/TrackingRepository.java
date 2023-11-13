package utfpr.trabalho.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.trabalho.api.model.tracking.Tracking;

public interface TrackingRepository extends JpaRepository<Tracking, Integer> {
}
