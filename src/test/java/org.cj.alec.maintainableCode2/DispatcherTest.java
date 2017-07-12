package org.cj.alec.maintainableCode2;

import org.junit.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DispatcherTest {

    public HttpServletRequestHandler newDispatcher(){
        final RouteController routeController = new RouteController();
        final HttpServletRequestHandler dispatcher = new Dispatcher(routeController);
        return dispatcher;
    }

    @Test
    public void dispatchToRouteController() throws IOException{
        //given
        final HttpServletRequestHandler dispatcher = newDispatcher();
        final StubRequest request = new StubRequest("target=cat", "/hello");
        final StubResponse actual = new StubResponse();

        //when
        dispatcher.handle(request, actual);

        //then
        assertThat(actual.lastStatus, equalTo(HttpServletResponse.SC_OK));
    }

    @Test
    public void nullRequestReturnsServerError() throws IOException{
        //given
        final HttpServletRequestHandler dispatcher = newDispatcher();
        final StubResponse actual = new StubResponse();
        final StubRequest request = null;

        //when
        dispatcher.handle(request, actual);

        //then
        assertThat(actual.lastStatus, equalTo(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
    }


    private class StubRequest extends HttpServletRequestNotImplemented {
        final String query;
        final String path;

        StubRequest(String query, String path){
            this.query = query;
            this.path = path;
        }

        @Override
        public String getMethod() {
            return "GET";
        }

        @Override
        public String getQueryString() {
            return query;
        }

        @Override
        public String getRequestURI() {
            return path;
        }
    }

    private class StubResponse extends HttpServletResponseNotImplemented {
        int lastStatus = -1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        public String currentOutputStream(){
            return new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
        }

        @Override
        public void setStatus(int sc) {
            lastStatus = sc;
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new ServletOutputStream() {
                @Override
                public void write(int b) throws IOException {
                    byteArrayOutputStream.write(b);
                }
            };
        }
    }

}
