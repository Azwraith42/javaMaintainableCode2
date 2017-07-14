package org.cj.alec.maintainableCode2;

import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RequestDispatcherTest {

    @Test
    public void dispatchToHello(){
        //given
        final RouterStub routeController = new RouterStub();
        final RequestDispatcher dispatcher = new RequestDispatcher(routeController);
        final String path = "/hello";
        final String query = "some query";
        final ResponseTuple rt;

        //when
        rt = dispatcher.handle(path, query);

        //then
        assertThat(rt.responseMessage, is("Hello, world!"));
        assertThat(rt.statusCode, is(HttpServletResponse.SC_OK));
    }

    class RouterStub implements Router {
        final private Route hello = new RouteHelloStub();

        @Override
        public Route getRoute(String path) {
            return hello;
        }
    }

    class RouteHelloStub implements Route {
        private final ResponseTuple rt = new ResponseTuple(HttpServletResponse.SC_OK, "Hello, world!");

        @Override
        public ResponseTuple getResponseTuple(Optional<String> maybeTarget) {
            return rt;
        }
    }
}
