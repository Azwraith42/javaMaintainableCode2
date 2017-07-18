package org.cj.alec.maintainableCode2;

import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RequestDispatcherTest {

    @Test
    public void dispatcherHappyPath(){
        //given
        Route defaultRoute = new DefaultRouteForTest();
        RouteMapStub routeMap = new RouteMapStub(defaultRoute);
        RequestDispatcher requestDispatcher = new RequestDispatcher(routeMap);
        String path = "some path";
        String query = "target=someTarget";
        final ResponseTuple responseTuple;
        final ResponseTuple expectedResponseTuple = new ResponseTuple(HttpServletResponse.SC_OK, "Hello, someTarget!");


        //when
        responseTuple = requestDispatcher.handle(path, query);


        assertThat(responseTuple.responseMessage, is(expectedResponseTuple.responseMessage));
        assertThat(responseTuple.statusCode, is(expectedResponseTuple.statusCode));
    }


    class RouteMapStub extends RouteMap{
        RouteMapStub(Route defaultRoute) {
            super(defaultRoute);
        }

        @Override
        public Route getOrDefault(String name) {
            return new happyPathRouteStubForTest();
        }
    }

    class DefaultRouteForTest implements Route {
        @Override
        public ResponseTuple getResponseTuple(Optional<String> maybeTarget) {
            return new ResponseTuple(HttpServletResponse.SC_NOT_FOUND, "Default Route returned");
        }
    }

    class happyPathRouteStubForTest implements Route {
        @Override
        public ResponseTuple getResponseTuple(Optional<String> maybeTarget) {
            return maybeTarget.map((target) -> new ResponseTuple(HttpServletResponse.SC_OK, String.format("Hello, %s!", target))).orElseGet(
                    () -> new ResponseTuple(HttpServletResponse.SC_BAD_REQUEST, "Bad Request")
            );
        }
    }


}
