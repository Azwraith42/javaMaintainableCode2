package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

interface Dispatcher{
    ResponseTuple handle(String path, String query) throws IOException;
}