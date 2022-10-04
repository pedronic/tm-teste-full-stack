package com.api.backend.configs.security;

import com.api.backend.models.UserModel;
import com.api.backend.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return new User(userModel.getUsername(), userModel.getPassword(), userModel.isEnabled(), userModel.isAccountNonExpired(), userModel.isCredentialsNonExpired(), userModel.isAccountNonLocked(), userModel.getAuthorities());
    }

    @Transactional
    public UserModel save(UserModel user) {
        return userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Optional<UserModel> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<UserModel> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void delete(UserModel user) {
        userRepository.delete(user);
    }
}
