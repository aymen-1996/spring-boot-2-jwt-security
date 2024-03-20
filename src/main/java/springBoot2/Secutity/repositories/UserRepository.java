package springBoot2.Secutity.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springBoot2.Secutity.entities.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByMail(String mail);

    @Query("SELECT u FROM User u WHERE u.mail=?1 ")
    User findUserByMail(String mail);


    @Query("SELECT u FROM User u WHERE u.mail=?1 and u.password=?2")
    User findUserByMailAAndPassword(String mail, String password);

    @Query("SELECT u FROM User u WHERE u.mail=?1 ")
    Optional<User> findByrespass(String mail);



}
