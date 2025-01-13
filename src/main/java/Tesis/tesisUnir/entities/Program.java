package Tesis.tesisUnir.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "programs")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Faculty faculty;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (name != null) {
            this.name = this.name.toLowerCase().trim();
        }
    }
}
