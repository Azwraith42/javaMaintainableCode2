package org.cj.alec.maintainableCode2;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public abstract class HttpServletResponseNotImplemented implements HttpServletResponse {
    @Override
    public void addCookie(Cookie cookie) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public boolean containsHeader(String s) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public String encodeURL(String s) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public String encodeRedirectURL(String s) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public String encodeUrl(String s) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public String encodeRedirectUrl(String s) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void sendError(int i, String s) throws IOException {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void sendError(int i) throws IOException {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void sendRedirect(String s) throws IOException {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void setDateHeader(String s, long l) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void addDateHeader(String s, long l) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void setHeader(String s, String s1) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void addHeader(String s, String s1) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void setIntHeader(String s, int i) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void addIntHeader(String s, int i) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void setStatus(int i) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void setStatus(int i, String s) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public String getCharacterEncoding() {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public String getContentType() {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void setCharacterEncoding(String s) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void setContentLength(int i) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void setContentType(String s) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void setBufferSize(int i) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public int getBufferSize() {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void flushBuffer() throws IOException {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void resetBuffer() {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public boolean isCommitted() {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public void setLocale(Locale locale) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Locale getLocale() {
        throw new UnsupportedOperationException("Not Implemented!");
    }
}
