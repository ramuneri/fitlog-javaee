package lt.vu.fitlog.usecases;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class AsyncDemo implements Serializable {

    @Inject
    private LongCalculationService longCalculationService;

    @Inject
    private AsyncProgress asyncProgress;

    public void startCalculation() {
        System.out.println("BUTTON CLICKED");

        longCalculationService.doLongCalculation();

        System.out.println("REQUEST FINISHED, USER DOES NOT WAIT");
    }

    public AsyncProgress getAsyncProgress() {
        return asyncProgress;
    }
}