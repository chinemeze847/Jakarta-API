package com.thankgod.server;

import com.thankgod.client.CreateUserDto;
import com.thankgod.client.ResponseDto;
import com.thankgod.client.UpdateUserDto;
import com.thankgod.client.User;
import com.thankgod.client.UserResource;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.mindrot.jbcrypt.BCrypt;

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
    user.setEmail(userDto.getEmail());
    user.setBirthday(userDto.getBirthday());
    user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
   
    userRepository.save(user);
    
    return Response
      .created(null)
      .entity(ResponseDto.success(user))
      .build();
  }
  
  @GET
  @Override
  public Response findMany() {
    final List<User> users = userRepository.findAll();
    
    return Response
      .ok(ResponseDto.success(users))
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
    
    if (userDto.getEmail() != null)
      userDto.getEmail().ifPresent(user::setEmail);
    
    if (userDto.getBirthday() != null)
      userDto.getBirthday().ifPresent(user::setBirthday);
    
    if (userDto.getPassword() != null)
      userDto.getPassword().ifPresent((password) -> user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt())));

    userRepository.update(user);
    
    return Response
      .ok(ResponseDto.success(user))
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
      .ok(ResponseDto.success(user))
      .build();
  }
}
