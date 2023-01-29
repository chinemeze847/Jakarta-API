package com.thankgod.server;

import com.thankgod.client.ResponseDto;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExecptionMapper implements ExceptionMapper<Exception> {

  @Override
  public Response toResponse(final Exception exception) {
    Response.ResponseBuilder responseBuilder;

    if (exception instanceof WebApplicationException) {
      responseBuilder = Response.status(((WebApplicationException) exception).getResponse().getStatus());
    } else {
      responseBuilder = Response.serverError();
    }

    Logger.getLogger(GenericExecptionMapper.class.getName()).log(Level.SEVERE, null, exception);

    return responseBuilder
      .entity(ResponseDto.error(exception == null ? "Unknown error" : exception.getMessage()))
      .type(MediaType.APPLICATION_JSON)
      .build();
  }
  
}
