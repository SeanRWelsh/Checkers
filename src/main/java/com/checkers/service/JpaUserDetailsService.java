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
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        playerRepository.findByUserName(userName);
        return playerRepository
                .findByUserName(userName)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + userName));

    }
}
