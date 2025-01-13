package Tesis.tesisUnir.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Data
@Table(name = "users")
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Program program;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (this.nombre != null) {
            this.nombre = this.nombre.toLowerCase().trim();
        }
        if (this.apellido != null) {
            this.apellido = this.apellido.toLowerCase().trim();
        }
        if (this.correo != null) {
            this.correo = this.correo.toLowerCase().trim();
        }
        if (this.usuario != null) {
            this.usuario = this.usuario.toLowerCase().trim();
        }
        if (this.rol != null) {
            this.rol = this.rol.toLowerCase().trim();
        }

        if (this.contrasena != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            this.contrasena = passwordEncoder.encode(this.contrasena);
        }
    }
}