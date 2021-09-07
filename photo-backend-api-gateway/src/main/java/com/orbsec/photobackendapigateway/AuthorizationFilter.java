package com.orbsec.photobackendapigateway;

import io.jsonwebtoken.Jwts;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    @Autowired
    private Environment environment;
    private Logger logger = Logger.getLogger(getClass().getName());

    public AuthorizationFilter() {
        super(Config.class);
    }

    public static class Config {
        // Add Configuration properties here.
    }

    @Override
    public GatewayFilter apply(Config config) {

        return ((exchange, chain) -> {
            // get the request object from the exchange server
            var request = exchange.getRequest();

//            // make sure the request object contains a key with the title "Authorization". Notice the '!' in front of the boolean returned by this evaluation
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }
            // extract the header from the request --> this returns an array. Get the first object.
            var header = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            // extract the token from header. Remove the word 'Bearer'
            var token = header.replace("Bearer", "");
            logger.info("AUTHORIZATION TOKEN: " + token);

            // send token for validation
            var validToken = isTokenValid(token);
            if (!validToken) {
                return onError(exchange, "Invalid Token", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        });
    }

    // onError() the subscription is cancelled --> the request is not authorized --> is not routed to the destination microservice
    private Mono<Void> onError(ServerWebExchange exchange, String error, HttpStatus statusCode) {
        var response = exchange.getResponse();
        response.setStatusCode(statusCode);
        return response.setComplete();
    }

    // token validation
    private boolean isTokenValid(String token) {
        boolean tokenIsValid = true;
        String subject = null;
        try {
            subject = Jwts.parser()
                    .setSigningKey(environment.getProperty("token.secret"))
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            tokenIsValid = false;
        }


        if (subject == null || subject.isEmpty()) {
            tokenIsValid = false;
        }
        return tokenIsValid;
    }
}
