package com.thankgod.client;

import java.time.LocalDate;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class UpdateUserDto {
  private Optional<@NotEmpty String> firstName;
  
  private Optional<@NotEmpty String> lastName;
  
  private Optional<@NotEmpty @EmailDoNotExist String> email;
  
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

  public Optional<String> getEmail() {
    return email;
  }

  public void setEmail(Optional<String> email) {
    this.email = email;
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
