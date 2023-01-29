package com.thankgod.server.entity;


import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name="users")
public class User implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  
  private String firstName;
  
  private String lastName;
  
  @Column(unique = true)
  private String email;
  
  private LocalDate birthday;
  
  @JsonbTransient
  private String password;
  
  private LocalDateTime updatedAt;
  
  private LocalDateTime createdAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
  
  @PreUpdate
	void updateTimeStamps() {
		updatedAt = LocalDateTime.now();
	}
  
  @PrePersist
	void persistTimeStamps() {
		updateTimeStamps();
		createdAt = LocalDateTime.now();
	}
}
