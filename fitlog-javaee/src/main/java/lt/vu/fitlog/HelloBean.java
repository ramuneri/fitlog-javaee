package lt.vu.fitlog;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class HelloBean {

    public String getMessage() {
        return "Welcome to FitLog!";
    }
}