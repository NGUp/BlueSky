package Core;

import javax.servlet.http.HttpSession;

public class Auth {
    public static boolean authorizeAdmin(HttpSession session) {
        return (session.getAttribute("userPermission") != null && session.getAttribute("userPermission").equals("ADMIN"));
    }
    
    public static boolean authorizeEmployee(HttpSession session) {
        return (session.getAttribute("userPermission") != null && !session.getAttribute("userPermission").equals("ADMIN"));
    }
}
