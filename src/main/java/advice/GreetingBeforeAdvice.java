package advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class GreetingBeforeAdvice implements MethodBeforeAdvice {

    public void before(Method method, Object[] objects, Object o) {
        String clientName=(String) objects[0];
        System.out.println("How are you"+clientName);
    }
}
