package lt.vu.fitlog.components;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.UUID;

//@RequestScoped
@SessionScoped
//@ApplicationScoped

public class DemoCounter implements Serializable {

    private int count = 0;
    private final String instanceId = UUID.randomUUID().toString();

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public String getInstanceId() {
        return instanceId;
    }
}
