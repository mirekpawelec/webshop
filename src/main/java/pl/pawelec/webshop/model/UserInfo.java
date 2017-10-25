/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;
import pl.pawelec.webshop.model.enum_.UserStatus;
import pl.pawelec.webshop.validator.UserLogin;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "users")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(nullable = false, length = 50)
    private String login;
    
    @Column(name = "passwd", nullable = false, length = 100)
    private String password;
    
    @Transient
    private String repeatPassword;
    
    @Column(name = "first_name", length = 25)
    private String firstName;
    
    @Column(name = "last_name", length = 25)
    private String lastName;

    @Column(length = 50)
    private String email;
    
    @Column(nullable = false, length = 50)
    private String role;

    @Column(length = 2)
    private String status;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "last_login")
    private LocalDateTime lastLoginDate;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "lm_date")
    private LocalDateTime lastModificationDate;
            
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "c_date")
    private LocalDateTime createDate;

    
    
    public UserInfo() {
        this.status = UserStatus.OK.name();
        this.lastModificationDate = LocalDateTime.now();
        this.createDate = LocalDateTime.now();
    }

    public UserInfo(String login, String password, String role) {
        this();
        this.login = login;
        this.password = password;
        this.role = role;
    }

    
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @NotEmpty(message = "{NotEmpty.UserInfo.login.validation}")
    @Size(max = 50, message = "{Size.UserInfo.login.validation}")
    @UserLogin
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @NotEmpty(message = "{NotEmpty.UserInfo.password.validation}")
    @Size(max = 100, message = "{Size.UserInfo.password.validation}")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty(message = "{NotEmpty.UserInfo.repeatPassword.validation}")
    @Size(max = 100, message = "{Size.UserInfo.repeatPassword.validation}")
    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @NotEmpty(message = "{NotEmpty.UserInfo.firstName.validation}")
    @Size(max = 25, message = "{Size.UserInfo.firstName.validation}")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty(message = "{NotEmpty.UserInfo.lastName.validation}")
    @Size(max = 25, message = "{Size.UserInfo.lastName.validation}")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotEmpty(message = "{NotEmpty.UserInfo.email.validation}")
    @Size(max = 50, message = "{Size.UserInfo.email.validation}")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty(message = "{NotEmpty.UserInfo.role.validation}")
    @Size(max = 50, message = "{Size.UserInfo.role.validation}")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public LocalDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(LocalDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.userId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserInfo other = (UserInfo) obj;
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "UserInfo{" 
                + " userId=" + userId 
                + ", login=" + login 
                + ", password=" + password
                + ", repeatPassword=" + repeatPassword
                + ", firstName=" + firstName 
                + ", lastName=" + lastName 
                + ", email=" + email 
                + ", role=" + role 
                + ", status=" + status 
                + ", lastLoginDate=" + lastLoginDate 
                + ", lastModificationDate=" + lastModificationDate 
                + ", createDate=" + createDate 
                + '}';
    }  
}
