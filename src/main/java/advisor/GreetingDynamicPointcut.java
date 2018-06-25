package advisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

//创建动态切面首先创建一个切点的bean，在切点中定义各个连接点的筛选规则
//动态筛选连接点之前需要先进行对应的静态检查
//定义准备织入的增强，通过xml可以直接生成对应的代理类
public class GreetingDynamicPointcut extends DynamicMethodMatcherPointcut {
    private static List<String> specialClient=new ArrayList<String>();
    static {
        specialClient.add("John");
        specialClient.add("Tom");
    }
    public ClassFilter getClassFilter(){
        //classfilter函数对类进行静态检查
        return new ClassFilter() {
            public boolean matches(Class<?> aClass) {
                System.out.println("调用ClassFilter对增强类进行类级别的静态检查: "+aClass.getName());
                return Waiter.class.isAssignableFrom(aClass);
            }
        };
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        //对类的方法名进行静态检查
        System.out.println("对"+targetClass.getName()+"."+method.getName()+"进行静态检查");
        return "greetTo".equals(method.getName());//检查是否是greetTo函数
    }

    //动态检查调用的函数和静态检查调用的函数同名
    //应该是不同的实现类
    public boolean matches(Method method, Class<?> aClass, Object... objects) {
        System.out.println("对"+aClass.getName()+"."+method.getName()+"进行动态检查");
        String clientName=(String) objects[0];
        return specialClient.contains(clientName);
    }
}
