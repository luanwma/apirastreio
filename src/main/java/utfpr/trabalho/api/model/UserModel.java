package utfpr.trabalho.api.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    private String address;

}
