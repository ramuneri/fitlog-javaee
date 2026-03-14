package lt.vu.fitlog.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class MuscleGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "muscleGroups")
    private List<Exercise> exercises;
}