package com.mp.piggymetrics.auth.controller;

import com.ibm.websphere.security.jwt.Claims;
import com.ibm.websphere.security.jwt.JwtBuilder;
import com.mp.piggymetrics.auth.domain.User;
import com.mp.piggymetrics.auth.service.UserManager;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 * The AuthResource is responsible for providing JWTs (users-token) to the
 * caller who is already logged-in, so they call other end-points with the
 * returned token
 */
@Path("")
@RequestScoped
public class AuthResource {

  @Inject
  private UserManager userManager;

  @POST
  @Path("/login")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response loginUser(User user) {
    User dbUser = userManager.get(user.getUsername());
    if (null == dbUser) {
      return Response.status(Status.BAD_REQUEST).entity("User not found!").build();
    }
    if (!dbUser.getPassword().equals(user.getPassword())) {
      return Response.status(Status.BAD_REQUEST).entity("Password incorrect!").build();
    }

    String jwtTokenString = null;
    try {
      jwtTokenString = JwtBuilder.create("jwtAuthUserBuilder").claim(Claims.SUBJECT, "authenticated")
          .claim("upn", user.getUsername()) /* MP-JWT defined subject claim */
          .claim("groups", "user") /* MP-JWT defined group, seems Liberty makes an array from a comma separated list */
          .buildJwt().compact();
    } catch (Throwable t) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erorr building authorization token").build();
    }

    JsonObjectBuilder builder = Json.createObjectBuilder();
    builder.add("access_token", jwtTokenString);

    return Response.ok(builder.build()).header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtTokenString)
        .header("Access-Control-Expose-Headers", HttpHeaders.AUTHORIZATION).build();
  }

  @GET
  @Path("users/{name}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response get(@PathParam("name") String name) {
    User user = userManager.get(name);
    return Response.ok(user).build();
  }

  @POST
  @Path("users")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response add(User user) {
    User savedUser = userManager.add(user);

    return Response
        .created(UriBuilder.fromResource(this.getClass()).path(String.valueOf(savedUser.getUsername())).build()).build();
  }
}
