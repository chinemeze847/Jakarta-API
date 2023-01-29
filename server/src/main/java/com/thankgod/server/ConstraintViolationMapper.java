package com.thankgod.server;

import com.thankgod.client.ResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

  @Override
  public Response toResponse(final ConstraintViolationException exception) {
     final List<ValidationError> errors = exception.getConstraintViolations().stream()
      .map(constraintViolation -> new ValidationError(
          ValidationError.nameFromPath(constraintViolation.getPropertyPath()), 
          constraintViolation.getInvalidValue(),
          constraintViolation.getMessage()
      ))
     .collect(Collectors.toList());
     
    return Response
      .status(Response.Status.BAD_REQUEST)
      .entity(ResponseDto.error(errors))
      .type(MediaType.APPLICATION_JSON)
      .build();
  }
  
  public static class ValidationError {
    private final String name;
    
    private final Object value;
    
    private final String message;

    public ValidationError(String name, Object value, String message) {
      this.name = name;
      this.value = value;
      this.message = message;
    }

    public String getName() {
      return name;
    }

    public Object getValue() {
      return value;
    }

    public String getMessage() {
      return message;
    }
    
    public static String nameFromPath(Path propertyPath) {
      String property = null;
        
      for(Path.Node path : propertyPath) {
        property = path.getName();
      }
      
      return property;
    }
  } 
}
