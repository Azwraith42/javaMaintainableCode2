package org.cj.alec.maintainableCode2;

final class DependencyInjector {

    // routeController
    // routes
    // getSingleParameterFromQuery
    // response mutator
    HttpServletRequestDispatcher dispatcher = new HttpServletRequestDispatcher();

    HttpServletrequestHandler httpServeletRequestHandler = new HttpServletRequestDelegator(dispatcher);
}
