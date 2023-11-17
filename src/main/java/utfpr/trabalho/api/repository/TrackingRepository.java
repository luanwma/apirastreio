package utfpr.trabalho.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utfpr.trabalho.api.model.tracking.Tracking;

import java.util.List;

public interface TrackingRepository extends JpaRepository<Tracking, Integer> {

    @Query("SELECT t FROM tracking t WHERE t.user.id = :userId")
    List<Tracking> findByUserId(@Param("userId") Integer userId);

}
