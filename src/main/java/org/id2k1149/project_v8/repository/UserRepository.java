package org.id2k1149.project_v8.repository;

import org.id2k1149.project_v8.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("H2")
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);


}
