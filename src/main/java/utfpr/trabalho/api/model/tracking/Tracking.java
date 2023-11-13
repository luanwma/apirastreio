package utfpr.trabalho.api.model.tracking;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import utfpr.trabalho.api.model.event.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Entity(name = "tracking")
@NoArgsConstructor
@AllArgsConstructor
public class Tracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String codeTrackging;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime lastUpdate;

    @PreUpdate
    public void preUpdate(){
        lastUpdate = LocalDateTime.now();
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Event> events = new ArrayList<>();


}
