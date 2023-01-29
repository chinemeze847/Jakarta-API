package com.thankgod.client;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface UserResource {
  
  public Response create(@NotNull @Valid final CreateUserDto userDto);
  
  public Response findMany();
  
  public Response update(
    @PathParam("id") final Long id, 
    @NotNull @Valid final UpdateUserDto userDto
  );
  
  public Response findOne(@PathParam("id") final Long id);
}
