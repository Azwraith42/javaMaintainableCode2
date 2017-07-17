package org.cj.alec.maintainableCode2;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HttpRouterTest {
    @Test
    public void normalCase(){
        //given
        final Map<String, Route> routeMap = new HashMap<>();
        final Route hello = new RouteHello();
        routeMap.put("/hello", hello);
        final String routeRequest = "/hello";
        final HttpRouter router = new HttpRouter(routeMap);
        final Route gottenRoute;

        //when
        gottenRoute = router.getRoute(routeRequest);

        //then
        assertThat(gottenRoute, is(hello));
    }

    @Test
    public void routeNotFound(){
        //given
        final Map<String, Route> routeMap = new HashMap<>();
        final Route notFound = new RouteNotFound();
        final String routeRequest = "/someRoute";
        final HttpRouter router = new HttpRouter(routeMap);
        final Route gottenRoute;

        //when
        gottenRoute = router.getRoute(routeRequest);

        //then
        assertThat(gottenRoute.toString(), is(notFound.toString()));
    }
}
