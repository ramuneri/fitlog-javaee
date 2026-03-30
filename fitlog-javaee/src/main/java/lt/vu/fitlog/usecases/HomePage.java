package lt.vu.fitlog.usecases;

import lt.vu.fitlog.components.DemoCounter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class HomePage {

    @Inject
    private DemoCounter demoCounter;

    public String countVisit() {
        demoCounter.increment();
        return "index?faces-redirect=true";
    }

    public int getVisitCount() {
        return demoCounter.getCount();
    }

    public String getCounterInstanceId() {
        return demoCounter.getInstanceId();
    }
}
