package lt.vu.fitlog.usecases;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class LongCalculationService {

    @Asynchronous
    public void doLongCalculation() {
        try {
            System.out.println("ASYNC TASK STARTED");

            for (int i = 1; i <= 8; i++) {
                Thread.sleep(1000);
                System.out.println("ASYNC TASK WORKING... step " + i);
            }

            System.out.println("ASYNC TASK FINISHED");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}