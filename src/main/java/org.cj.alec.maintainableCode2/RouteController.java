package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class RouteController {
    final private Map<String, Route> commandMap;

    RouteController(){
        commandMap = new HashMap<>();
        commandMap.put("/hello",  new Hello());
        commandMap.put("/length", new Length());
    }

    void handleRoute(String path, String query, HttpServletResponse response){
        Optional<String> parameter = GetTargetFromString.getSingleStringParameterFromQuery(query);
        Route route = commandMap.getOrDefault(path, this::notFound);

        parameter.ifPresent( (target) -> route.makeResponse(response, target));


        if(!parameter.isPresent()) badRequest(response);
    }

    private void badRequest(HttpServletResponse response) {
        ResponseMutator.setResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Bad Request, exactly one target param expected");
    }

    private void notFound(HttpServletResponse response, String message) {
        ResponseMutator.setResponse(response, HttpServletResponse.SC_NOT_FOUND, "Not Found, URI not found");
    }
}

