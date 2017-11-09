package servlets;

import service.ResourceService;
import resources.xml.ResourceReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResorceServlet extends HttpServlet {

    private ResourceService resourceService;

    public ResorceServlet(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getParameter("path");

        if (path == null || path.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Object obj = ResourceReader.readViaSax(path);

        resourceService.bindToResource(obj);

        resp.setStatus(HttpServletResponse.SC_OK);

    }
}
