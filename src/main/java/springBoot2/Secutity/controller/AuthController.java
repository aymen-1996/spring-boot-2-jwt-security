package springBoot2.Secutity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springBoot2.Secutity.Security.JwtResponse;
import springBoot2.Secutity.Security.SignInRequest;
import springBoot2.Secutity.Security.TokenUtil;
import springBoot2.Secutity.entities.User;
import springBoot2.Secutity.repositories.UserRepository;
import springBoot2.Secutity.service.StorageService;
import springBoot2.Secutity.service.UserServicesimp;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserServicesimp userServicesimp;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private StorageService storage;
    @PostMapping(value = {"/auth"})
    public JwtResponse signIn(@RequestBody SignInRequest signInRequest) {
        UserDetails userDetails = userServicesimp.loadUserByUsername(signInRequest.getUsername());
        JwtResponse response = new JwtResponse(null, null, null, null);

        try {

                final Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                String token = tokenUtil.generateToken(userDetails);
                String refreshToken = tokenUtil.generateRefreshtToken(userDetails);
                User user = userRepository.findUserByMail(signInRequest.getUsername());
                response = new JwtResponse(token, refreshToken, user.getRole(), user);

        } catch (BadCredentialsException e) {
            throw new IncorrectPasswordException("Incorrect password");
        } catch (UsernameNotFoundException e) {
            throw new IncorrectUsernameException("Incorrect username");
        }

        return response;
    }

    public class IncorrectPasswordException extends RuntimeException {
        public IncorrectPasswordException(String message) {
            super(message);
        }
    }

    public class IncorrectUsernameException extends RuntimeException {
        public IncorrectUsernameException(String message) {
            super(message);
        }
    }


    @PostMapping("/save")
    public User save(@RequestParam("file") MultipartFile file, User user) {
        String filename = storage.CreateNameCv(file);
        storage.store(file, filename);
        user.setImage(filename);
        user.setPassword(userServicesimp.encodePassword(user.getPassword()));
        return userRepository.save(user);
    }

}



