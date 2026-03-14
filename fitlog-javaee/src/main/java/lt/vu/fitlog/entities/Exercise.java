package lt.vu.fitlog.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;
    private Integer sets;
    private Integer reps;

    @ManyToOne
    private WorkoutPlan workoutPlan;

    @ManyToMany
    private List<MuscleGroup> muscleGroups;
}