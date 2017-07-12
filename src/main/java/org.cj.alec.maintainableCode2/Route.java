package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletResponse;

public interface Route {

    void makeResponse(HttpServletResponse response, String parameter);

}
