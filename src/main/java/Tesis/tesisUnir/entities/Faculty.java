package Tesis.tesisUnir.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Entity
@Table(name = "faculties")
@Data
@Slf4j
@Schema(name = "Faculty", description = "Faculty entity")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    private String name;

    @OneToMany(mappedBy = "faculty")
    private List<Program> programs;

    public void someMethod() {
        log.info("Test Lombok logging");
    }
}