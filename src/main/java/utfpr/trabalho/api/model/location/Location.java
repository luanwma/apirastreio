package utfpr.trabalho.api.model.location;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String country;

    @Column
    private String state;

    @Column
    private String street;

    @Column
    private String number;

    @Column
    private String zipcode;
    @Column
    private String complement;






}
