package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class SingInServlet extends HttpServlet {

    private final AccountService accountService;

    public SingInServlet(AccountService accountService) {

        this.accountService = accountService;

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (ServletsHelper.notValid(login, password)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Optional<UserProfile> profile = accountService.getUser(login);

        if (!profile.isPresent() || !profile.get().getPassword().equals(password)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().print("Unautorized");
            return;
        }

        resp.getWriter().format("Autorized: %s", login);
        resp.setStatus(HttpServletResponse.SC_OK);

    }
}
