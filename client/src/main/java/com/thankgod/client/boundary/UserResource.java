package com.thankgod.client.boundary;


import com.thankgod.client.entity.CreateUserDto;
import com.thankgod.client.entity.UpdateUserDto;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface UserResource {

  @POST
  public Response create(@NotNull @Valid final CreateUserDto userDto);

  @GET
  public Response findMany();

  @PUT
  @Path("{id}")
  public Response update(
          @PathParam("id") final Long id,
    @NotNull @Valid final UpdateUserDto userDto
  );

  @GET
  @Path("{id}")
  public Response findOne(@PathParam("id") final Long id);
}
