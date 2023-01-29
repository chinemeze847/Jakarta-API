package com.thankgod.server;

import com.thankgod.client.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class UserRepository {
  
  @PersistenceContext(unitName = "default")
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
