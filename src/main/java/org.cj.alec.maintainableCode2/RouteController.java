package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletResponse;

interface RouteController {
    Route getRoute(HttpServletResponse response, String path);
}
