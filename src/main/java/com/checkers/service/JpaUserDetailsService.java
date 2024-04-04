package com.checkers.service;

import com.checkers.models.SecurityUser;
import com.checkers.models.Player;
import com.checkers.repositories.PlayerRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final PlayerRepository playerRepository;

    public JpaUserDetailsService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username is " + username);
        playerRepository.findByUsername(username);
        return playerRepository
                .findByUsername(username)

                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("username not found: " + username));

    }
}
