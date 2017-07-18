package org.cj.alec.maintainableCode2;

import java.util.HashMap;
import java.util.Map;

public class RouteMap {
    private final Map<String, Route> routeMap = new HashMap<>();
    private final Route defaultRoute;

    public RouteMap(Route defaultRoute) {
        routeMap.put("/hello", new RouteHello());
        routeMap.put("/length", new RouteLength());
        this.defaultRoute = defaultRoute;
    }
    public Route getOrDefault(String name){
        return routeMap.getOrDefault(name, defaultRoute);
    }
}
