package org.cj.alec.maintainableCode2;

final class DependencyInjector {

    HttpRouter routeController = new HttpRouter();
    RequestDispatcher dispatcher = new RequestDispatcher(routeController);

    HttpServletrequestHandler httpServeletRequestHandler = new HttpServletRequestDelegator(dispatcher);
}
