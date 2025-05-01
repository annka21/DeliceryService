package ru.mirea.deliveryservice.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mirea.deliveryservice.models.User;
import ru.mirea.deliveryservice.repos.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void create(User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User already exists!");
        }
        userRepo.save(user);
    }

    public User getByUsername(String username) {
        return userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public Long getCurrentUserId() {
        User user = getCurrentUser();
        return user.getId();
    }
}
