package org.cj.alec.maintainableCode2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

final class ResponseMutator {
    static void setResponse(HttpServletResponse response, int statusCode, String message){
        try{
            response.setStatus(statusCode);
            response.getOutputStream().print(message);
        }catch(IOException e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
