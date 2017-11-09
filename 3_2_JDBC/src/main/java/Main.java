import accounts.AccountService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SingInServlet;
import servlets.SingUpServlet;

/**
 * Created by Andrey on 04.11.17.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        AccountService accountService = new AccountService();
        accountService.prepareDB();

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new SingUpServlet(accountService)), "/signup");
        contextHandler.addServlet(new ServletHolder(new SingInServlet(accountService)), "/signin");

        Server server = new Server(8080);

        server.setHandler(contextHandler);

        server.start();
        server.join();

    }

}
