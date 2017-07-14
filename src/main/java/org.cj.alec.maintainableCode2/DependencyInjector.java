package org.cj.alec.maintainableCode2;

final class DependencyInjector {

    HttpRouteController routeController = new HttpRouteController();
    HttpServletRequestDispatcher dispatcher = new HttpServletRequestDispatcher(routeController);

    HttpServletrequestHandler httpServeletRequestHandler = new HttpServletRequestDelegator(dispatcher);
}
