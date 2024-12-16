package com.validate.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.HashMap;
import java.util.Map;

public class AutenticacaoCPFHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        String cpf = input.getQueryStringParameters().get("cpf");

        Map<String, String> response = new HashMap<>();
        if (cpf == null || !cpf.matches("\\d{11}")) {
            response.put("message", "CPF inválido - PROVANDO QUE MEU PROJETO É NOTA 10");
            return createResponse(400, response);
        }

        response.put("message", "CPF válido");
        return createResponse(200, response);
    }

    private APIGatewayProxyResponseEvent createResponse(int statusCode, Map<String, String> body) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(statusCode);
        response.setBody(body.toString());
        return response;
    }
}
