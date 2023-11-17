package utfpr.trabalho.api.model.tracking;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import utfpr.trabalho.api.model.event.Event;
import utfpr.trabalho.api.model.user.UsersModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "tracking")
@NoArgsConstructor
@AllArgsConstructor
public class Tracking  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String codeTracking;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersModel user;

    // Construtores, getters e setters

    // Métodos para obter e definir o usuário
    public UsersModel getUser() {
        return user;
    }

    public void setUser(UsersModel user) {
        this.user = user;
    }

    @Column
    private int timeInterval;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime lastUpdate;

    @PreUpdate
    public void preUpdate(){
        lastUpdate = LocalDateTime.now();
    }

   // @JoinColumn
   @OneToMany(mappedBy = "tracking", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Event> events = new ArrayList<>();


    public void addEvent(Event event) {
        events.add(event);
        event.setTracking(this);
    }
    public void removeEvent(Event event) {
        events.remove(event);
        event.setTracking(null);
    }

}
