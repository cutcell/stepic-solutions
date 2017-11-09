package servlets;

public class ServletsHelper {

    public static boolean notValid(String... params) {

        for (String param : params) {
            if (param == null || param.isEmpty()) {
                return true;
            }
        }

        return false;

    }

}
