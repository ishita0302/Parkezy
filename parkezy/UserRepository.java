package com.parkezy.parkezy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User,Long>{
   final List<User> users = new ArrayList<>();

    @Autowired
    public default Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    /*@Autowired
    public default Optional<User> findById(long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }*/

    /*@Autowired
    public default User save(User user) {
        users.add(user);
        return null;
    }*/

    // Additional methods as needed
}
