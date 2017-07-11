package org.cj.alec.maintainableCode2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EntryPointServlet extends HttpServlet{
    private static DependencyInjection dependencyInjection = new DependencyInjection();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dependencyInjection.httpServletRequestHandler.handle(request, response);
    }
}
