package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class RouteLength implements Route {
    @Override
    public void makeResponse(HttpServletResponse response, Optional<String> maybeTarget) {
        throw new UnsupportedOperationException("Not Implemented!");
    }
}
