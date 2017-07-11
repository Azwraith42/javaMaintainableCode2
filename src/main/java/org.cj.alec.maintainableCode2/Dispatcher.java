package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dispatcher implements HttpServletRequestHandler{
    private Map<String, BiConsumer<HttpServletResponse, String>> commandMap;

        Dispatcher(){
        commandMap = new HashMap<>();
        commandMap.put("/hello",  this::sayHello);
        commandMap.put("/length", this::displayLength);
    }


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            tryOrError(request, response);
        }catch(Exception e){
            setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong");
        }

    }

    private void tryOrError(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getRequestURI();
        String query = request.getQueryString();
        Optional<String> parameter = GetSingleStringParameterFromQuery(request.getQueryString());

        parameter.ifPresent((target) -> commandMap.getOrDefault(path, this::notFound).accept(response, target));
        if(!parameter.isPresent()) badRequest(response);
    }

    private void badRequest(HttpServletResponse response) {
        setResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Bad Request, exactly one target param expected");
    }

    private void displayLength(HttpServletResponse response, String parameter) {
        final String message = String.format("Length: %d", parameter.length());
        setResponse(response, HttpServletResponse.SC_OK, message);
    }

    private void sayHello(HttpServletResponse response, String parameter) {
        final String message = String.format("Hello, %s!", parameter);
        setResponse(response, HttpServletResponse.SC_OK, message);
    }

    private Optional<String> GetSingleStringParameterFromQuery(String queryString) {
        List<String> matches = getStrings(queryString);
        int numTargets = matches.toArray().length;
        if(numTargets == 1){
            return Optional.of(getParameter(matches));
        }else{
            return Optional.empty(); // throw error or trigger badRequest
        }
    }

    private String getParameter(List<String> matches) {
        String [] words = matches.get(0).split("=");
        return words[1];
    }

    private List<String> getStrings(String queryString) {
        List<String> matches = new ArrayList<>();
        Matcher m = Pattern.compile("target=\\w+")
                .matcher(queryString);
        while (m.find()) {
            matches.add(m.group());
        }
        return matches;
    }

    private void setResponse(HttpServletResponse response, int statusCode, String message) {
        try{
            response.setStatus(statusCode);
            response.getOutputStream().print(message);
        }catch(IOException e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void notFound(HttpServletResponse response, String message) {
        setResponse(response, HttpServletResponse.SC_NOT_FOUND, "Not Found, URI not found");
    }
}