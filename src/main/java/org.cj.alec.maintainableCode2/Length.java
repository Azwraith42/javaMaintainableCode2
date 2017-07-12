package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

public class Length implements Route {
    @Override
    public void makeResponse(HttpServletResponse response, String parameter) {
        final String message = String.format("Length: %d", parameter.length());
        ResponseMutator.setResponse(response, HttpServletResponse.SC_OK, message);
    }
}
