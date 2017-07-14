package org.cj.alec.maintainableCode2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EntryPointServlet extends HttpServlet{
    private static DependencyInjector dependencyInjector = new DependencyInjector();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dependencyInjector.httpServeletRequestHandler.handle(req, resp);
    }
}
