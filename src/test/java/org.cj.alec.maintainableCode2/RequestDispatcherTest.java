package org.cj.alec.maintainableCode2;

import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RequestDispatcherTest {

    // ask Sean, do I need to test the Dispatcher when it doesn't do anything itself?
    // no logic is actually done in my dispatcher, it's all delegated out.

    @Test
    public void dispatchToHello(){
        //given
        final Route defaultRoute = new RouteNotFound();
        final RouteMapStub routeMap = new RouteMapStub(defaultRoute);
        final RequestDispatcher dispatcher = new RequestDispatcher(routeMap);
        final String path = "/hello";
        final String query = "target=world";
        final String responseMessage = "Hello, world!";
        final ResponseTuple responseTuple;
        final ResponseTuple expected = new ResponseTuple(HttpServletResponse.SC_OK, responseMessage);

        //when
        responseTuple = dispatcher.handle(path, query);

        //then
        assertThat(responseTuple.statusCode, is(expected.statusCode));
        assertThat(responseTuple.responseMessage, is(expected.responseMessage));
    }

    class RouteMapStub extends RouteMap{
        public RouteMapStub(Route defaultRoute) {
            super(defaultRoute);
        }

        @Override
        public Route getOrDefault(String name) {
            return super.getOrDefault(name);
        }
    }


}
