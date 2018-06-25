package advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class GreetingAfterAdvice implements AfterReturningAdvice {
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) {
        System.out.println("please enjoy yourself");
    }
}
