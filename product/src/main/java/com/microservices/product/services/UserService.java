package com.microservices.product.services;

import com.microservices.product.models.User;
import com.microservices.product.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        Optional<User> userData = userRepo.findUserByEmail(user.getEmail());
        if(userData.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        String passwd= user.getPassword();
        String encodedPasswod = passwordEncoder.encode(passwd);
        user.setPassword(encodedPasswod);
        user = userRepo.save(user);
        return user;
    }

    public User getUserByEmail(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findUserByEmail(email);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User with email: " +email +" not found !");
        } else {
            return user.get();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userDetails = userRepo.findUserByEmail(email);

        if(userDetails.isEmpty())
            throw new UsernameNotFoundException("User with email: " +email +" not found !");
        else {
            User user = userDetails.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles()
                            .stream()
                            .map(role-> new SimpleGrantedAuthority(role))
                            .collect(Collectors.toSet())
            );
        }
    }
}
