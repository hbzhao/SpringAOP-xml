package advice;

import advisor.Seller;
import advisor.Waiter;
import advisor.WaiterDelegate;
import introduce.Monitorable;
import org.junit.Test;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import proxy.ForumService;

//@ContextConfiguration(locations = {"classpath:/proxy.xml"})
public class BeforeAdviceTest {

    String configPath="classpath:/proxy.xml";

    @Test
    public void dynamicMethod(){

       ApplicationContext ctx=new ClassPathXmlApplicationContext(configPath);
       Waiter waiter=(Waiter)ctx.getBean("waiter2");
       waiter.serveTo("hongbo");
       //special client
       waiter.serveTo("John");
       waiter.greetTo("Tom");
    }

    @Test
    public void controlFlow(){
        ApplicationContext ctx=new ClassPathXmlApplicationContext(configPath);
        Waiter waiter=(Waiter)ctx.getBean("waiterComposable");
        WaiterDelegate wd= new WaiterDelegate();
        //本质上还是增强了Waiter类，然后根据是否被调用来筛选增强行为
        wd.setWaiter(waiter);
        waiter.serveTo("Peter");
        waiter.greetTo("Peter");
        wd.service("Peter");
    }

//    @Test
//    public void staticMethod(){
//		String configPath = "classpath:/proxy.xml";
//		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
//		Waiter waiter = (Waiter)ctx.getBean("waiterAdvisor");
//		Seller seller = (Seller)ctx.getBean("seller");
//		waiter.greetTo("John");
//		waiter.serveTo("John");
//		seller.greetTo("John");
//	}


//    @Test
//    public void introduce(){
//		String configPath = "classpath:/proxy.xml";
//		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
//        ForumService forumService = (ForumService)ctx.getBean("forumService");
//        forumService.removeForum(10);
//        forumService.removeTopic(1022);
//
//
//        Monitorable moniterable = (Monitorable)forumService;
//        moniterable.setMonitorActive(true);
//        forumService.removeForum(10);
//        forumService.removeTopic(1022);
//	}

//    @Test
//    public void xmlBefore(){
//        ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:/proxy.xml");
//        Waiter waiter=(Waiter) ctx.getBean("waiter");
//        waiter.greetTo("fda");
//    }

//    @Test
//    public void before(){
//        Waiter target=new NativeWaiter();
//        BeforeAdvice beforeAdvice=new GreetingBeforeAdvice();
//        AfterReturningAdvice afterAdvice=new GreetingAfterAdvice();
//
//        ProxyFactory pf=new ProxyFactory();//代理工厂
//
//        pf.setTarget(target);//加入增强的目标对象
//
//        pf.addAdvice(beforeAdvice);//加入一个前置增强
//        pf.addAdvice(afterAdvice);
//
//
//        Waiter proxy=(Waiter)pf.getProxy();
//        proxy.greetTo("john");
//        proxy.serveTo("john");
//    }
}
