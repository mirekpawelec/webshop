/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.pawelec.webshop.model.enum_.UserStatus;

/**
 *
 * @author mirek
 */
public class UserDetailsAdapter implements UserDetails, Serializable{
    private UserInfo userInfo;
    
    
    
    public UserDetailsAdapter(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    
    
    
    public UserInfo getUserInfo() { 
        return userInfo; 
    }
    
    public Long getId() { 
        return userInfo.getUserId(); 
    }
    
    public String getFirstName() { 
        return userInfo.getFirstName(); 
    }
    
    public String getLastName() { 
        return userInfo.getLastName(); 
    }
    
    public String getFullName() { 
        return userInfo.getFirstName()+" "+userInfo.getLastName(); 
    }
    
    public String getEmail() { 
        return userInfo.getEmail(); 
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(userInfo.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getLogin();
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
        return userInfo.getStatus().equals(UserStatus.OK.name());
    }
    
}
