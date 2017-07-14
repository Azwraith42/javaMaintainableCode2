package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class RouteLength implements Route {
    @Override
    public ResponseTuple getResponseTuple(Optional<String> maybeTarget) {
        return maybeTarget.map((target) -> new ResponseTuple(HttpServletResponse.SC_OK, String.format("Length: %d!", target.length()))).orElseGet(
                () -> new ResponseTuple(HttpServletResponse.SC_BAD_REQUEST, "Bad Request")
        );
    }
}
