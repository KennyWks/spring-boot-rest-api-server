package com.metrodata.serverapp.service.impl;

import com.metrodata.serverapp.entity.AppUserDetail;
import com.metrodata.serverapp.entity.User;
import com.metrodata.serverapp.exception.CustomException;
import com.metrodata.serverapp.model.response.UserResponse;
import com.metrodata.serverapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmployee_Email(username, username)
                .orElseThrow(() -> new CustomException(
                        "Username or Password incorrect",
                        "INVALID_USERNAME_PASSWORD",
                        400));
        return new AppUserDetail(user);
    }
}
