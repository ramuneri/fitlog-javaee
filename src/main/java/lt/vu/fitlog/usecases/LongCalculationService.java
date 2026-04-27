package lt.vu.fitlog.usecases;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LongCalculationService {

    @Inject
    private AsyncProgress asyncProgress;


    @Asynchronous
    public void doLongCalculation() {
        try {
            asyncProgress.start();

            for (int i = 1; i <= 5; i++) {
                Thread.sleep(1000);
                asyncProgress.update(i * 20, "Working... step " + i);
            }

            asyncProgress.finish();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}