package com.furnifinders.backend.service.impl;



import com.furnifinders.backend.Repository.UserRepository;
import com.furnifinders.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
 private final UserRepository userRepository;
 @Override
 public UserDetailsService userDetailsService() {
	 return username -> userRepository.findByEmail(username)
             .orElseThrow(()->new UsernameNotFoundException("user not found"));
 }
}