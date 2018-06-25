package proxy;
//通过java动态代理实现AOP
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerformanceHandler implements InvocationHandler {
    private Object target;//目标对象/类
    public PerformanceHandler(Object target){
        this.target=target;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable{
        PerformanceMonitor.begin(
                target.getClass().getName()+"."+method.getName());
        Object object=method.invoke(target,args);
        PerformanceMonitor.end();
        return object;
    }
}
