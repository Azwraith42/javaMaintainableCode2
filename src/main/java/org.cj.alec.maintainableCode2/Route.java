package org.cj.alec.maintainableCode2;

import java.util.Optional;

public interface Route {
    ResponseTuple getResponseTuple(Optional<String> maybeTarget);
}
