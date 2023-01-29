package com.thankgod.server.boundary;

import com.thankgod.client.entity.CreateUserDto;
import com.thankgod.client.entity.UpdateUserDto;
import com.thankgod.client.boundary.UserResource;


import com.thankgod.server.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResourceImp implements UserResource {
  
  @Inject
  private UserRepository userRepository;
 
  @POST
  @Override
  public Response create(@NotNull @Valid final CreateUserDto userDto) {
    final User user = new User();
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setBirthday(userDto.getBirthday());
    user.setEmail(userDto.getEmail());
    user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
   
    userRepository.save(user);
    
    return Response
      .created(null)
      .entity(user)
      .build();
  }
  
  @GET
  @Override
  public Response findMany() {
    final List<User> users = userRepository.findAll();
    
    return Response
      .ok(users)
      .build();
  }
  
  @PUT
  @Path("{id}")
  @Override
  public Response update(
    @PathParam("id") final Long id, 
    @NotNull @Valid final UpdateUserDto userDto
  ) {
    final User user = userRepository.find(id);
    
    if (user == null) {
      throw new NotFoundException("User not found");
    }
    
    if (userDto.getFirstName() != null)
      userDto.getFirstName().ifPresent(user::setFirstName);
    
    if (userDto.getLastName() != null)
      userDto.getLastName().ifPresent(user::setLastName);

    
    if (userDto.getBirthday() != null)
      userDto.getBirthday().ifPresent(user::setBirthday);
    
    if (userDto.getPassword() != null)
      userDto.getPassword().ifPresent((password) -> user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt())));

    userRepository.update(user);
    
    return Response
      .ok(user)
      .build();
  }
  
  @GET
  @Path("{id}")
  @Override
  public Response findOne(@PathParam("id") final Long id) {
    final User user = userRepository.find(id);
    
    if (user == null) {
      throw new NotFoundException("User not found");
    }
    
    return Response
      .ok(user)
      .build();
  }
}
