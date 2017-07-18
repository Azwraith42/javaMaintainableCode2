package org.cj.alec.maintainableCode2;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RouteMapTest {

    @Test
    public void verifyRouteMap(){
        // given
        Route defaultRoute = new DefaultRouteForTest();
        RouteMap routeMap = new RouteMap(defaultRoute);

        // when & then
        verifyRoute(routeMap, "/hello", RouteHello.class);
        verifyRoute(routeMap, "/length", RouteLength.class);
        verifyRoute(routeMap, "/foo", DefaultRouteForTest.class);
    }

    private void verifyRoute(RouteMap routeMap, String name, Class<? extends Route> expectedClass){
        Route route = routeMap.getOrDefault(name);
        Class<? extends Route> actualClass = route.getClass();
        String actualClassName = actualClass.getName();
        String expectedClassName = expectedClass.getName();
        assertThat(actualClassName, is(expectedClassName));
    }

    class DefaultRouteForTest implements Route {
        @Override
        public ResponseTuple getResponseTuple(Optional<String> maybeTarget) {
            throw new UnsupportedOperationException("Not Implemented!");
        }
    }
}
