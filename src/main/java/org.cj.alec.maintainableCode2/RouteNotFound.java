package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class RouteNotFound implements Route {
    @Override
    public ResponseTuple getResponseTuple(Optional<String> maybeTarget) {
        return new ResponseTuple(HttpServletResponse.SC_NOT_FOUND, "Not Found");
    }

    @Override
    public String toString() {
        return "Class: RouteNotFound, Implements: Route";
    }
}
