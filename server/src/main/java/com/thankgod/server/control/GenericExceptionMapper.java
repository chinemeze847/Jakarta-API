package com.thankgod.server.control;


import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

  @Override
  public Response toResponse(final Exception exception) {
    Response.ResponseBuilder responseBuilder;

    if (exception instanceof WebApplicationException) {
      responseBuilder = Response.status(((WebApplicationException) exception).getResponse().getStatus());
    } else {
      responseBuilder = Response.serverError();
    }

    Logger.getLogger(GenericExceptionMapper.class.getName()).log(Level.SEVERE, null, exception);

    return responseBuilder
      .entity(new ErrorDto(exception == null ? "Unknown error" : exception.getMessage()))
      .type(MediaType.APPLICATION_JSON)
      .build();
  }
  
  public static class ErrorDto {
    private final String error;

    public ErrorDto(String error) {
      this.error = error;
    }

    public String getError() {
      return error;
    }
  } 
}
