package utfpr.trabalho.api.model.event;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

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


    @Column
    @CreatedDate
    private LocalDateTime eventStart ;



}
