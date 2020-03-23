package com.mp.piggymetrics.auth.service;

import com.ibm.websphere.security.jwt.Claims;
import com.ibm.websphere.security.jwt.JwtBuilder;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 * The auth resource is responsible for providing JWTs to the caller:
 * 1) login-token: used to login or create a new user;
 * 2) users-token: used to call other end-points provided by micro-services;
 */
@Path("/auth")
@RequestScoped
public class AuthResource {
  
  @Path("/login")
  @GET
  public Response getLoginJwt() {
    String jwtTokenString = null;
    try {
      jwtTokenString =
          JwtBuilder.create("jwtAuthLoginBuilder")
              .claim(Claims.SUBJECT, "unauthenticated")
              .claim("upn", "unauthenticated") /* MP-JWT defined subject claim */
              .claim("groups", "login") /* MP-JWT defined group, seems Liberty makes an array from a comma separated list */
              .buildJwt()
              .compact();
    } catch (Throwable t) {
      return Response.status(Status.INTERNAL_SERVER_ERROR)
          .entity("Erorr building authorization token")
          .build();
    }

    JsonObjectBuilder builder = Json.createObjectBuilder();
    builder.add("login_token", jwtTokenString);

    return Response.ok(builder.build())
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtTokenString)
        .header("Access-Control-Expose-Headers", HttpHeaders.AUTHORIZATION)
        .build();
  }
}
