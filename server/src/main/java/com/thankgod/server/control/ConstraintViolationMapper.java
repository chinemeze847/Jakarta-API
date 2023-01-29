package com.thankgod.server.control;



import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;
import java.util.List;
import java.util.stream.Collectors;

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
      .entity(errors)
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
