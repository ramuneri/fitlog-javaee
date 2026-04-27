package lt.vu.fitlog.usecases;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ApplicationScoped
public class AsyncProgress implements Serializable {

    private int progress = 0;
    private boolean running = false;
    private boolean finished = false;
    private String message = "Not started";

    public void start() {
        progress = 0;
        running = true;
        finished = false;
        message = "Calculation started";
    }

    public void update(int progress, String message) {
        this.progress = progress;
        this.message = message;
    }

    public void finish() {
        progress = 100;
        running = false;
        finished = true;
        message = "Calculation finished";
    }

    public int getProgress() {
        return progress;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isFinished() {
        return finished;
    }

    public String getMessage() {
        return message;
    }
}