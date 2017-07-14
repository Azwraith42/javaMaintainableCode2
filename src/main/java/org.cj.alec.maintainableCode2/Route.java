package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface Route {
    void makeResponse(HttpServletResponse response, Optional<String> maybeTarget);
}
