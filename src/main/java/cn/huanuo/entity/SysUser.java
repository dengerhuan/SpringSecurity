package cn.huanuo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class SysUser implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    @JsonIgnore
    private String password;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<SysRole> roles = new ArrayList<SysRole>(0);


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<SysRole> getRoles() {
        return roles;
    }


    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String getUsername() {
        return this.username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        List<SysRole> roles = this.getRoles();
        for (SysRole role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
