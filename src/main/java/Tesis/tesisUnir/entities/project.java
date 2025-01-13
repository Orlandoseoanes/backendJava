package Tesis.tesisUnir.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    private String title;

    private String description;

    private String dateradicated;

    private String state;

    @ManyToOne(fetch = FetchType.EAGER)
    private Program program;




}
