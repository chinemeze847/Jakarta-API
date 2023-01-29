package com.thankgod.client.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateUserDto {
  @NotEmpty
  private String firstName;
  
  @NotEmpty
  private String lastName;

  @NotEmpty
  private String email;

  
  @NotNull
  @Past
  private LocalDate birthday;
  
  @NotEmpty
  @Size(min = 6, max = 30)
  private String password;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
