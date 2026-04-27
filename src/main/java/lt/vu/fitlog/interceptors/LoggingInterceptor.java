package lt.vu.fitlog.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Logged
@Interceptor
public class LoggingInterceptor {

    @AroundInvoke
    public Object logMethod(InvocationContext context) throws Exception {
        System.out.println("INTERCEPTOR BEFORE: " + context.getMethod().getName());

        Object result = context.proceed();

        System.out.println("INTERCEPTOR AFTER: " + context.getMethod().getName());

        return result;
    }
}