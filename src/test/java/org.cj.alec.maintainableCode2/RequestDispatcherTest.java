package org.cj.alec.maintainableCode2;

import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RequestDispatcherTest {

    @Test
    public void dispatchTest(){
        //given
        final ResponseTuple responseTuple;
        final ResponseTuple expectedResponseTuple = new ResponseTuple(HttpServletResponse.SC_OK, "Hello, someTarget!");
        Route defaultRoute = new DefaultRouteForTest();
        RouteMapStub routeMap = new RouteMapStub(defaultRoute, expectedResponseTuple);
        RequestDispatcher requestDispatcher = new RequestDispatcher(routeMap);
        String path = "some path";
        String query = "target=someTarget";

        //when
        responseTuple = requestDispatcher.handle(path, query);


        assertThat(responseTuple.responseMessage, is(expectedResponseTuple.responseMessage));
        assertThat(responseTuple.statusCode, is(expectedResponseTuple.statusCode));
    }


    class RouteMapStub extends RouteMap{
        ResponseTuple rt;

        public RouteMapStub(Route defaultRoute, ResponseTuple rt) {
            super(defaultRoute);
            this.rt = rt;
        }

        @Override
        public Route getOrDefault(String name) {
            return new HelloRouteForTest(rt);
        }
    }

    class DefaultRouteForTest implements Route {
        @Override
        public ResponseTuple getResponseTuple(Optional<String> maybeTarget) {
            return new ResponseTuple(HttpServletResponse.SC_NOT_FOUND, "Default Route returned");
        }
    }

    class HelloRouteForTest implements Route {
        private ResponseTuple rt;

        public HelloRouteForTest(ResponseTuple rt) {
            this.rt = rt;
        }

        @Override
        public ResponseTuple getResponseTuple(Optional<String> maybeTarget) {
            return rt;
        }
    }


}
