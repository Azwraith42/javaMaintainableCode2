package org.cj.alec.maintainableCode2;

import org.junit.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HttpServletRequestDelegatorTest {
    @Test
    public void testTypicalCase(){
        // given
        DispatcherStub dispatcher = new DispatcherStub();
        HttpServletRequestDelegator delegator = new HttpServletRequestDelegator(dispatcher);
        String path = "some path";
        String query = "some query";
        HttpServletRequestStub request = new HttpServletRequestStub(path, query);
        HttpServletResponseStub response = new HttpServletResponseStub();
        // when
        delegator.handle(request, response);

        // then
        assertThat(dispatcher.invocations.size(), is(1));
        Invocation invocation = dispatcher.invocations.get(0);
        assertThat(invocation.path, is(path));
        assertThat(invocation.query, is(query));
        assertThat(response.status, is(HttpServletResponse.SC_OK));
        assertThat(response.getWhatWasWrittenToOutputAsUtf8String(), is("some stuff"));
    }

    class HttpServletRequestStub extends HttpServletRequestNotImplemented{
        final String path;
        final String query;

        public HttpServletRequestStub(String path, String query) {
            this.path = path;
            this.query = query;
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

    class Invocation {
        final String path;
        final String query;

        Invocation(String path, String query) {
            this.path = path;
            this.query = query;
        }
    }

    class DispatcherStub implements Dispatcher{
        List<Invocation> invocations = new ArrayList<>();
        @Override
        public ResponseTuple handle(String path, String query) throws IOException {
            Invocation invocation = new Invocation(path, query);
            invocations.add(invocation);
            return new ResponseTuple(200, "some stuff");
        }
    }
}
