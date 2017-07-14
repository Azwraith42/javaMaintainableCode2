package org.cj.alec.maintainableCode2;

import java.util.Optional;

public class RequestDispatcher implements Dispatcher {
    private final Router router;

    public RequestDispatcher(Router router) {
        this.router = router;
    }

    @Override
    public ResponseTuple handle(String path, String query) {
        Route route = router.getRoute(path);
        Optional<String> maybeTarget = GetTargetFromString.getSingleStringParameterFromQuery(query);
        return route.getResponseTuple(maybeTarget);
    }
}
