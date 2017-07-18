package org.cj.alec.maintainableCode2;

import java.util.Optional;

public class RequestDispatcher implements Dispatcher {
    private final RouteMap routeMap;

    public RequestDispatcher(RouteMap routeMap) {
        this.routeMap = routeMap;
    }

    @Override
    public ResponseTuple handle(String path, String query) {
        Route route = routeMap.getOrDefault(path);
        Optional<String> maybeTarget = GetTargetFromString.getSingleStringParameterFromQuery(query);
        return route.getResponseTuple(maybeTarget);
    }
}
