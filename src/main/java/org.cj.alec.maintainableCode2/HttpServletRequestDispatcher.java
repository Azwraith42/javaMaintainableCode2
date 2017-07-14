package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletResponse;

public class HttpServletRequestDispatcher implements Dispatcher {
    @Override
    public void handle(String path, String query, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not Implemented!");
    }
}
