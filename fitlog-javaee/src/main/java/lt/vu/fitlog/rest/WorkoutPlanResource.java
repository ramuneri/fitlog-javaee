package lt.vu.fitlog.rest;

import lt.vu.fitlog.entities.WorkoutPlan;
import lt.vu.fitlog.persistence.WorkoutPlanDAO;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/workoutPlans")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkoutPlanResource {

    @Inject
    private WorkoutPlanDAO workoutPlanDAO;

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        WorkoutPlan workoutPlan = workoutPlanDAO.findOne(id);

        if (workoutPlan == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        WorkoutPlanDto dto = new WorkoutPlanDto();
        dto.id = workoutPlan.getId();
        dto.name = workoutPlan.getName();
        dto.difficulty = workoutPlan.getDifficulty();

        return Response.ok(dto).build();
    }

    @POST
    @Transactional
    public Response create(WorkoutPlanDto dto) {
        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlan.setName(dto.name);
        workoutPlan.setDifficulty(dto.difficulty);
        workoutPlan.setVersion(0L);

        workoutPlanDAO.persist(workoutPlan);

        dto.id = workoutPlan.getId();

        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, WorkoutPlanDto dto) {
        WorkoutPlan existingWorkoutPlan = workoutPlanDAO.findOne(id);

        if (existingWorkoutPlan == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existingWorkoutPlan.setName(dto.name);
        existingWorkoutPlan.setDifficulty(dto.difficulty);

        workoutPlanDAO.update(existingWorkoutPlan);

        WorkoutPlanDto responseDto = new WorkoutPlanDto();
        responseDto.id = existingWorkoutPlan.getId();
        responseDto.name = existingWorkoutPlan.getName();
        responseDto.difficulty = existingWorkoutPlan.getDifficulty();

        return Response.ok(responseDto).build();
    }
}
