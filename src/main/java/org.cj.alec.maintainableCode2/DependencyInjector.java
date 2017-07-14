package org.cj.alec.maintainableCode2;

import java.util.HashMap;
import java.util.Map;

final class DependencyInjector {

    private Map<String, Route> routeMap = new HashMap<>();
    private HttpRouter router = new HttpRouter(routeMap);
    private RequestDispatcher dispatcher = new RequestDispatcher(router);
    HttpServletRequestHandler httpServletRequestHandler = new HttpServletRequestDelegator(dispatcher);

    public DependencyInjector() {
        routeMap.put("/hello", new RouteHello());
        routeMap.put("/length", new RouteLength());
    }
}
