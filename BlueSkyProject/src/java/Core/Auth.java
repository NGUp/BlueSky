package Core;

import javax.servlet.http.HttpSession;

public class Auth {
    public static boolean authorizeAdmin(HttpSession session) {
        return (session.getAttribute("userPermission") != null && session.getAttribute("userPermission").equals("ADMIN"));
    }
    
    public static boolean authorizeManager(HttpSession session) {
        return (session.getAttribute("userPermission") != null && session.getAttribute("userPermission").equals("MANAGER"));
    }
    
    public static boolean authorizeConductor(HttpSession session) {
        return (session.getAttribute("userPermission") != null && session.getAttribute("userPermission").equals("CONDUCTOR"));
    }
    
    public static boolean authorizeEmployee(HttpSession session) {
        return (session.getAttribute("userPermission") != null && !session.getAttribute("userPermission").equals("ADMIN"));
    }
}
