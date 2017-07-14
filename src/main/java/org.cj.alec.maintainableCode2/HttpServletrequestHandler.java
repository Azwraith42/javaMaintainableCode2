package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HttpServletrequestHandler {

    void handle(HttpServletRequest request, HttpServletResponse response);
}
