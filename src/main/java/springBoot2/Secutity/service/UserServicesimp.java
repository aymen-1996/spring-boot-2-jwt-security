package springBoot2.Secutity.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springBoot2.Secutity.entities.User;
import springBoot2.Secutity.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServicesimp implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("mail is : " + username);
        User loclaUser=userRepository.findByMail(username);
        if(loclaUser!=null){
            org.springframework.security.core.userdetails.User springUser=new org.springframework.security.core.userdetails.User(loclaUser.getMail(),loclaUser.getPassword(),new ArrayList<>());
            return springUser;
        }
        return null;
    }

    public String encodePassword(String password) {
        return passwordEncoder().encode(password);

    }

    public void save(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> findall() {
        return userRepository.findAll();
    }

}
