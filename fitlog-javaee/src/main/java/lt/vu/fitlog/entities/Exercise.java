package lt.vu.fitlog.entities;

import javax.persistence.*;
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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<MuscleGroup> muscleGroups = new java.util.ArrayList<>();

    public Exercise() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getSets() {
        return sets;
    }

    public Integer getReps() {
        return reps;
    }

    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }

    public List<MuscleGroup> getMuscleGroups() {
        return muscleGroups;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public void setWorkoutPlan(WorkoutPlan workoutPlan) {
        this.workoutPlan = workoutPlan;
    }

    public void setMuscleGroups(List<MuscleGroup> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }
}