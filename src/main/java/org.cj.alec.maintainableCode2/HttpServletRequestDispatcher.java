package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class HttpServletRequestDispatcher implements Dispatcher {
    private final RouteController routeController;

    public HttpServletRequestDispatcher(RouteController routeController) {
        this.routeController = routeController;
    }

    @Override
    public void handle(String path, String query, HttpServletResponse response) {
        Route route = routeController.getRoute(response, path);
        Optional<String> maybeTarget = GetTargetFromString.getSingleStringParameterFromQuery(query);
        route.makeResponse(response, maybeTarget);
    }
}
