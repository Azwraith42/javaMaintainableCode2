package org.cj.alec.maintainableCode2;

final class DependencyInjector {
    private Route defaultRoute = new RouteNotFound();
    private RouteMap routeMap = new RouteMap(defaultRoute);
    private RequestDispatcher dispatcher = new RequestDispatcher(routeMap);
    HttpServletRequestHandler httpServletRequestHandler = new HttpServletRequestDelegator(dispatcher);
}
