package com.aliakseila.springsecurityacl.service;

import com.aliakseila.springsecurityacl.exception.AlreadyExistException;
import com.aliakseila.springsecurityacl.exception.NotFoundException;
import com.aliakseila.springsecurityacl.model.entity.User;
import com.aliakseila.springsecurityacl.service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format("User with username \"%s\" not found", username)));
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id \"%d\" not found", id)));
    }

    public void createNewUser(User user) {
        userRepository.findByUsername(user.getUsername())
                .ifPresentOrElse(
                        u -> {
                            throw new AlreadyExistException(String.format("User with username \"%s\" already exists", u.getUsername()));
                        },
                        () -> userRepository.save(user));
    }
}
