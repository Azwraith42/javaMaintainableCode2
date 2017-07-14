package org.cj.alec.maintainableCode2;

import java.util.Optional;

public class RouteNotFound implements Route {
    @Override
    public ResponseTuple getResponseTuple(Optional<String> maybeTarget) {
        throw new UnsupportedOperationException("Not Implemented!");
    }
}
