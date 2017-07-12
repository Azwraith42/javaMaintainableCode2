package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletResponse;

public class Hello implements Route {

    @Override
    public void makeResponse(HttpServletResponse response, String parameter) {
        final String message = String.format("Hello, %s!", parameter);
        ResponseMutator.setResponse(response, HttpServletResponse.SC_OK, message);
    }
}
