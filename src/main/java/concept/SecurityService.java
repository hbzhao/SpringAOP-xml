package concept;

public interface SecurityService {
    boolean checkAccess(User user,String service);
}
