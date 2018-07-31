package com.shortener.entity;

import com.shortener.entity.abstractClasses.AbstractId;
import com.shortener.entity.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "_user")
public class User extends AbstractId implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Enumerated
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<CustomShortUrl> customShortUrls = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(role.name())));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<CustomShortUrl> getCustomShortUrls() {
        return customShortUrls;
    }

    public void setCustomShortUrls(List<CustomShortUrl> customShortUrls) {
        this.customShortUrls = customShortUrls;
    }

    public static class Builder {

        private final User user = new User();

        public Builder name(String name) {
            user.name = name;
            return this;
        }

        public Builder password(String password) {
            user.password = password;
            return this;
        }

        public Builder role(Role role) {
            user.role = role;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
