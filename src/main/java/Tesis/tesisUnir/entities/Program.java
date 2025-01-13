package Tesis.tesisUnir.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "programs")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @ManyToOne
    private Faculty faculty;
}
