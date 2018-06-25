package proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;
//测试类目录要与源代码目录保持一致
//在同一个目录包名下的测试代码与源代码共享包作用域
public class ForumServiceTest {
    @Test
    public void proxy(){
        ForumService target=new ForumServiceImpl();

        PerformanceHandler handler=new PerformanceHandler(target);

        ForumService proxy=(ForumService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler
        );
        proxy.removeForum(11);
        proxy.removeTopic(1000);
    }

    @Test
    public void cglibProxy(){
        CglibProxy proxy = new CglibProxy();
        ForumServiceImpl forumService=(ForumServiceImpl) proxy.getProxy(ForumServiceImpl.class);//创建一个子类
        forumService.removeForum(10);
        forumService.removeTopic(1111);
    }
}
