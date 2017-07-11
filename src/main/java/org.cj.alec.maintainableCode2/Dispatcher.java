package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;

public class Dispatcher implements HttpServletRequestHandler {
    private Map<String, BiConsumer<Optional<String>, HttpServletResponse> > commandMap;

    Dispatcher(){
        commandMap = new HashMap<>();
        commandMap.put("/hello",  this::sayHello);
        commandMap.put("/length", this::displayLength);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        if(request == null){
            notFound(Optional.empty(), response);
            return;
        }
        String path = request.getRequestURI();
        String query = request.getQueryString();

        commandMap.getOrDefault(path, this::notFound).accept(singleParameterQuery(query, "target"), response);
    }

    private Optional<String> singleParameterQuery(String query, String target){
        if (query == null) return Optional.empty();
        String[] pairs = query.split("&");
        List<String> matches = new ArrayList<>();
        for( String pair : pairs){
            String [] keys = pair.split("=");
            if(keys.length < 2) return Optional.empty();
            String key = keys[0];
            if(target.equals(key)){
                String value = keys[1];
                matches.add(value);
            }
        }
        return (matches.size() == 1) ? Optional.of(matches.get(0)) : Optional.empty();
    }

    private void setResponse(HttpServletResponse response, Integer statusCode, String message) {
        try {
            response.setStatus(statusCode);
            response.getOutputStream().print(message);
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }



    private void sayHello(Optional<String> maybeTarget, HttpServletResponse response){
        if(!maybeTarget.isPresent()) badRequest(response);
        maybeTarget.ifPresent( (target) -> setResponse(response, HttpServletResponse.SC_OK, String.format("Hello, %s!", target)));
    }

    private void displayLength(Optional<String> maybeTarget, HttpServletResponse response){
        if(!maybeTarget.isPresent()) badRequest(response);
        maybeTarget.ifPresent( (target) -> setResponse(response, HttpServletResponse.SC_OK, String.format("Length: %d", target.length())));
    }

    private void badRequest(HttpServletResponse response){
        setResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Bad Request, exactly one target param expected");
    }

    private void notFound(Optional<String> maybeTarget, HttpServletResponse response){
        setResponse(response, HttpServletResponse.SC_NOT_FOUND, "Not Found, URI not found");
    }
}
