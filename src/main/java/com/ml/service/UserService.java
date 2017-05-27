package com.ml.service;

import com.ml.entity.Role;
import com.ml.entity.User;
import com.ml.entity.UserRole;
import com.ml.repository.UserRepository;
import com.ml.repository.UserRoleRepository;
import com.ml.security.CustomUserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomUserDetails findUserDetailsByUsername(String username) {
        if (StringUtils.isBlank(username))
            return null;
        User user = userRepository.findByUsername(username);
        if (user == null)
            return null;
        CustomUserDetails securityUser = new CustomUserDetails(user);
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        Set<Role> roleSet = userRoles.stream().map(UserRole::getRole).collect(Collectors.toSet());
        securityUser.setRoleSet(roleSet);
        return securityUser;
    }

    public void addUser() {
        User user = new User();
        user.setName("maling");
        user.setAge(18);
        user.setPassword(passwordEncoder.encode("123456"));
        user.setGender("男");
        user.setStatus("normal");


        userRepository.save(user);
    }
}