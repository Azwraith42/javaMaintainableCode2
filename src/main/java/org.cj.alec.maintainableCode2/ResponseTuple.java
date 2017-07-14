package org.cj.alec.maintainableCode2;


public class ResponseTuple {
    int statusCode;
    String responseMessage;

    public ResponseTuple(int statusCode, String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
    }
}
