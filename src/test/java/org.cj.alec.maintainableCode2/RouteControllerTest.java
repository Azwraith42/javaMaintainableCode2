package org.cj.alec.maintainableCode2;

import org.junit.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class RouteControllerTest {


    @Test
    public void routeToHello(){
        //given
        final RouteController rc = new RouteController();
        final StubResponse actual = new StubResponse();
        final String path = "/hello";
        final String query = "target=world";

        //when
        rc.handleRoute(path, query, actual);

        //then
        assertThat(actual.lastStatus, equalTo(HttpServletResponse.SC_OK));
        assertThat(actual.currentOutputStream(), equalTo("Hello, world!"));
    }

    @Test
    public void routeToLength(){
        //given
        final RouteController rc = new RouteController();
        final StubResponse actual = new StubResponse();
        final String path = "/length";
        final String query = "target=world";

        //when
        rc.handleRoute(path, query, actual);

        //then
        assertThat(actual.lastStatus, equalTo(HttpServletResponse.SC_OK));
        assertThat(actual.currentOutputStream(), equalTo("Length: 5"));
    }

    @Test
    public void routeFromNullPath(){
        //given
        final RouteController rc = new RouteController();
        final StubResponse actual = new StubResponse();
        final String path = null;
        final String query = "target=world";

        //when
        rc.handleRoute(path, query, actual);

        //then
        assertThat(actual.lastStatus, equalTo(HttpServletResponse.SC_NOT_FOUND));
        assertThat(actual.currentOutputStream(), equalTo("Not Found, URI not found"));
    }

    @Test
    public void routeFromNullQuery(){
        //given
        final RouteController rc = new RouteController();
        final StubResponse actual = new StubResponse();
        final String path = "/hello";
        final String query = null;

        //when
        rc.handleRoute(path, query, actual);

        //then
        assertThat(actual.lastStatus, equalTo(HttpServletResponse.SC_BAD_REQUEST));
        assertThat(actual.currentOutputStream(), equalTo("Bad Request, exactly one target param expected"));
    }

    @Test
    public void badPathIsNotFound(){
        //given
        final RouteController rc = new RouteController();
        final StubResponse actual = new StubResponse();
        final String path = "/xxx";
        final String query = null;

        //when
        rc.handleRoute(path, query, actual);

        //then
        assertThat(actual.lastStatus, equalTo(HttpServletResponse.SC_BAD_REQUEST));
        assertThat(actual.currentOutputStream(), equalTo("Bad Request, exactly one target param expected"));
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
