package org.cj.alec.maintainableCode2;

import org.junit.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DispatcherTest {

    @Test
    public void helloWorld() throws IOException {
        //given
        final HttpServletRequestHandler dispatcher = new Dispatcher();
        final StubRequest request = new StubRequest("target=world", "/hello");
        final StubResponse actual = new StubResponse();

        //when
        dispatcher.handle(request, actual);
        //then
        assertThat(actual.lastStatus, equalTo(HttpServletResponse.SC_OK));
        assertThat(new String(actual.byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8), equalTo("Hello, world!"));
    }

    @Test
    public void lengthOfAlec() throws IOException{
        //given
        final HttpServletRequestHandler dispatcher = new Dispatcher();
        final StubRequest request = new StubRequest("target=Alec", "/length");
        final StubResponse actual = new StubResponse();

        //when
        dispatcher.handle(request, actual);
        //then
        assertThat(actual.lastStatus, equalTo(HttpServletResponse.SC_OK));
        assertThat(new String(actual.byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8), equalTo("Length: 4"));
    }

    @Test
    public void wrongPath() throws IOException{
        //given
        final HttpServletRequestHandler dispatcher = new Dispatcher();
        final StubRequest request = new StubRequest("target=Alec", "/xxx");
        final StubResponse actual = new StubResponse();

        //when
        dispatcher.handle(request, actual);
        //then
        assertThat(actual.lastStatus, equalTo(HttpServletResponse.SC_NOT_FOUND));
        assertThat(new String(actual.byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8), equalTo("Not Found, URI not found"));
    }

    @Test
    public void nullPathIsHandled() throws IOException{
        //given
        final HttpServletRequestHandler dispatcher = new Dispatcher();
        final StubRequest request = new StubRequest("target=Alec", null);
        final StubResponse actual = new StubResponse();

        //when
        dispatcher.handle(request, actual);
        //then
        assertThat(actual.lastStatus, equalTo(HttpServletResponse.SC_NOT_FOUND));
        assertThat(new String(actual.byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8), equalTo("Not Found, URI not found"));
    }

    @Test
    public void doNotAllowMultipleTargets() throws IOException{
        //given
        final HttpServletRequestHandler dispatcher = new Dispatcher();
        final StubRequest request = new StubRequest("target=Alec&target=cat", "/hello");
        final StubResponse actual = new StubResponse();

        //when
        dispatcher.handle(request, actual);
        //then
        assertThat(actual.lastStatus, equalTo(HttpServletResponse.SC_BAD_REQUEST));
        assertThat(new String(actual.byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8), equalTo("Bad Request, exactly one target param expected"));
    }

    @Test
    public void nullQueryIsHandled() throws IOException{
        //given
        final HttpServletRequestHandler dispatcher = new Dispatcher();
        final StubRequest request = new StubRequest(null, "/hello");
        final StubResponse actual = new StubResponse();

        //when
        dispatcher.handle(request, actual);
        //then
        assertThat(actual.lastStatus, equalTo(HttpServletResponse.SC_BAD_REQUEST));
        assertThat(new String(actual.byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8), equalTo("Bad Request, exactly one target param expected"));
    }

    @Test
    public void noRquestAtAll() throws IOException {
        //given
        final HttpServletRequestHandler dispatcher = new Dispatcher();
        final StubResponse actual = new StubResponse();

        //when
        dispatcher.handle(null, actual);

        //then
        assertThat(actual.lastStatus, equalTo(HttpServletResponse.SC_NOT_FOUND));
        assertThat(new String(actual.byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8), equalTo("Not Found, URI not found"));
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
