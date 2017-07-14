package org.cj.alec.maintainableCode2;

import org.junit.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HttpServletRequestDispatcherTest {

    @Test
    public void dispatchToHello(){
        //given
        final RouteControllerStub routeController = new RouteControllerStub();
        final HttpServletRequestDispatcher dispatcher = new HttpServletRequestDispatcher(routeController);
        final String path = "/hello";
        final String query = "some query";
        HttpServletResponseStub response = new HttpServletResponseStub();

        //when
        dispatcher.handle(path, query, response);

        //then
        assertThat(response.getWhatWasWrittenToOutputAsUtf8String(), is("Hello, world!"));
        assertThat(response.status, is(HttpServletResponse.SC_OK));
    }

    class HttpServletResponseStub extends HttpServletResponseNotImplemented{
        int status;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String getWhatWasWrittenToOutputAsUtf8String(){
            return new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
        }
        ServletOutputStream outputStream = new ServletOutputStream() {
            @Override
            public void write(int b) throws IOException {
                byteArrayOutputStream.write(b);
            }
        };

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return outputStream;
        }

        @Override
        public void setStatus(int sc) {
            this.status = sc;
        }
    }

    class RouteControllerStub implements RouteController{
        final private Route hello = new RouteHelloStub();

        @Override
        public Route getRoute(HttpServletResponse response, String path){
            return hello;
        }

    }

    class RouteHelloStub implements Route {
        @Override
        public void makeResponse(HttpServletResponse response, Optional<String> maybeTarget){
            ResponseMutator.setResponse(response, HttpServletResponse.SC_OK, "Hello, world!");
        }
    }
}
