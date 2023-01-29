package com.thankgod.server.boundary;


import com.thankgod.server.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
public class UserRepository {
  
  @PersistenceContext(name = "default1")
  private EntityManager entityManager;
  
  public boolean existsByEmail(final String email) {
    final User user = (User) entityManager
            .createQuery("SELECT u FROM User u WHERE u.email = :email")
            .setParameter("email", email)
            .getResultStream()
            .findFirst()
            .orElse(null);

    return user != null;
  }

  public void save(User user) {
    entityManager.persist(user);
  }
  
  public void update(User user) {
    entityManager.merge(user);
  }
  
  public User find(Long id) {
    return entityManager.find(User.class, id);
  }
  
  public List<User> findAll() {
    return entityManager.createQuery("SELECT u FROM User u").getResultList();
  }
}
