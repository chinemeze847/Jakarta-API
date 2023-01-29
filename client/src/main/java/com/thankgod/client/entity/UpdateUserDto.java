package com.thankgod.client.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Optional;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
  private Optional<@NotEmpty String> firstName;
  
  private Optional<@NotEmpty String> lastName;
  
  private  Optional<@NotNull @Past LocalDate> birthday;
  
  private Optional<@NotEmpty @Size(min = 6, max = 30) String> password;

  public Optional<String> getFirstName() {
    return firstName;
  }

  public void setFirstName(Optional<String> firstName) {
    this.firstName = firstName;
  }

  public Optional<String> getLastName() {
    return lastName;
  }

  public void setLastName(Optional<String> lastName) {
    this.lastName = lastName;
  }


  public Optional<LocalDate> getBirthday() {
    return birthday;
  }

  public void setBirthday(Optional<LocalDate> birthday) {
    this.birthday = birthday;
  }

  public Optional<String> getPassword() {
    return password;
  }

  public void setPassword(Optional<String> password) {
    this.password = password;
  }
}
