package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class HttpRouter implements Router {
    private final Map<String, Route> commandMap;

    public HttpRouter() {
        this.commandMap = new HashMap<>();
        this.commandMap.put("/hello", new RouteHello());
        this.commandMap.put("/length", new RouteLength());
    }

    @Override
    public Route getRoute(String path) {
        return commandMap.getOrDefault(path, new RouteNotFound());
    }
}
