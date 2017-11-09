package servlets;

public class ServletsHelper {

    public static boolean notValid(String... parameters) {

        for (String p : parameters) {
            if (p == null || p.isEmpty()) {
                return true;
            }
        }

        return false;
    }

}
