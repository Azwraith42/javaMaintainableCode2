package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

interface Dispatcher{
    void handle(String path, String query, HttpServletResponse response) throws IOException;
}