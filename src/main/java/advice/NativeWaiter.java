package advice;

public class NativeWaiter implements Waiter {
    public void greetTo(String name) {
        System.out.println("greet to "+name);
    }

    public void serveTo(String name) {
        System.out.println("serve to" + name);
    }
}
