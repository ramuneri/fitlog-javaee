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

    public MuscleGroup() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}