package Tesis.tesisUnir.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    private String cedula;

    private String nombre;

    private String apellido;

    private String correo;

    private String usuario;

    private String contrasena;

    private String rol;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST) // Agregado CascadeType.PERSIST
    @JoinColumn(name = "program_id") // Asegura que el campo para el join sea el correcto
    private Program program;

}
