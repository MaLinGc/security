package com.ml.security;

import com.ml.entity.Role;
import com.ml.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String loginName;

    @Getter
    @Setter
    private String name;

    @Setter
    private String password;

    @Getter
    @Setter
    private Integer status;

    @Getter
    @Setter
    private Set<Role> roleSet;

    public CustomUserDetails(User user) {
        if (user != null) {
            this.setId(user.getId());
            this.setName(user.getName());
            this.setLoginName(user.getUsername());
            this.setPassword(user.getPassword());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (this.roleSet == null)
            return authorities;
        return roleSet.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                      .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status == null || 0 == this.status;
    }
}
