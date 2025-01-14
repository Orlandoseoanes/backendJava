package Tesis.tesisUnir.entities.dtos;

import Tesis.tesisUnir.entities.Program;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String cedula;

    private String nombre;

    private String apellido;

    private String correo;

    private String usuario;

    private String contrasena;

    private String rol;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Program program;
}
