package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher implements HttpServletRequestHandler{
    private RouteController routeController;

    Dispatcher(RouteController routeController){
        this.routeController = routeController;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        try {
            dispatch(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response){
        String path = request.getRequestURI();
        String query = request.getQueryString();
        routeController.handleRoute(path, query, response);
    }
}