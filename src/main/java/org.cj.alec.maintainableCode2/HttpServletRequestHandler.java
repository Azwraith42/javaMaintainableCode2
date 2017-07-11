package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HttpServletRequestHandler {
    void handle(HttpServletRequest request, HttpServletResponse response) throws IOException;
}