package utfpr.trabalho.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.trabalho.api.model.tracking.Tracking;

import java.util.List;

public interface TrackingRepository extends JpaRepository<Tracking, Integer> {

    default List<Tracking> findByUserId(Integer userId) {
        return null;
    }

}
