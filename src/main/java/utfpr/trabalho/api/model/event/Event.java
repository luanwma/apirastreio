package utfpr.trabalho.api.model.event;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import utfpr.trabalho.api.model.location.Location;
import utfpr.trabalho.api.model.tracking.Tracking;

import java.time.LocalDateTime;

@Data
@Entity(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String eventName;

    @Column
    private String eventType;

    @Column
    private LocalDateTime eventEnd;

    @ManyToOne()
    private Location currentLocation;

    //private Location destinyLocation;

    @ManyToOne
    @JoinColumn(name = "tracking_id")
    private Tracking tracking;



    @Column
    @CreatedDate
    private LocalDateTime eventStart ;



}
