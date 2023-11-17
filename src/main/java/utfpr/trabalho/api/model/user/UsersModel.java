package utfpr.trabalho.api.model.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import utfpr.trabalho.api.model.tracking.Tracking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity(name = "users")
public class UsersModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String cpf;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Date birthDate;

    @Column
    private String email;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String phoneNumber;

    @Column
    @CreatedDate
    private LocalDateTime createdAt ;

    @Column(name = "isActive" ,columnDefinition = "" )
    private boolean isActive;

    @Column
    private UserRole role;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private ArrayList<Tracking> listTrackings = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private transient List<Tracking> listTrackings = new ArrayList<>();
  //  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   // private ArrayList<Tracking> listTrackings = new ArrayList<>();



    public UsersModel(String login, String password, UserRole role){
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UsersModel(String cpf, String firstName, String lastName, Date birthDate,
                      String email, String login, String password, String phoneNumber, UserRole role) {
        this.cpf = cpf;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
