package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpServletRequestDelegator implements HttpServletrequestHandler {
    final private Dispatcher dispatcher;

    HttpServletRequestDelegator(Dispatcher dispatcher){
        this.dispatcher = dispatcher;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        try{
            tryDispatch(request, response);
        }catch(Exception e){
            ResponseMutator.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private void tryDispatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String path = request.getRequestURI();
        final String query = request.getQueryString();
        dispatcher.handle(path, query, response);
    }
}
