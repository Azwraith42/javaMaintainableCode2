package org.cj.alec.maintainableCode2;

import java.util.HashMap;
import java.util.Map;

public class HttpRouter implements Router {
    private final Map<String, Route> commandMap;

    public HttpRouter(Map<String, Route> commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public Route getRoute(String path) {
        return commandMap.getOrDefault(path, new RouteNotFound());
    }
}
