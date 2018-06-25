package proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();
    public Object getProxy(Class clazz){
        enhancer.setSuperclass(clazz);//将目标类作为父类传入Class文件
        enhancer.setCallback(this);
        return enhancer.create();//创建子类实例
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
        throws Throwable{
        PerformanceMonitor.begin(o.getClass().getName()+method.getName());
        Object result=methodProxy.invokeSuper(o,objects);
        PerformanceMonitor.end();
        return result;
    }
}
