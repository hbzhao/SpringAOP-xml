package advisor;

public class WaiterDelegate {
    private Waiter waiter;

    //目标是将增强织入这个方法
    public void service(String clientName){
        waiter.greetTo(clientName);
        waiter.serveTo(clientName);
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }
}
