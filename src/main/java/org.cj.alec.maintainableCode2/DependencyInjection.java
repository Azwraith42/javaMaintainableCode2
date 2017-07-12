package org.cj.alec.maintainableCode2;

class DependencyInjection {

    private RouteController routeController = new RouteController();
    HttpServletRequestHandler httpServletRequestHandler = new Dispatcher(routeController);
}
