package com.taski.taskmanagement.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document("user")
public class User {

    @Id
    private String id;

    private String password;
    @NotBlank
    private String email;

    private String username;

    private Date cratedDate;

    private boolean enabled = true;

    private Set<com.taski.taskmanagement.entity.user.UserType> userTypes = new HashSet<>();


    public String getId() {
        return id;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public com.taski.taskmanagement.entity.user.UserTypeE userTypeE;

    public void setUserTypeE(com.taski.taskmanagement.entity.user.UserTypeE userTypeE) {
        this.userTypeE = userTypeE;
    }

    public Set<com.taski.taskmanagement.entity.user.UserType> getUserTypes() {
        return userTypes;
    }

    public void addRole(com.taski.taskmanagement.entity.user.UserType userType){
        this.userTypes.add(userType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                enabled == user.enabled &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(userTypes, user.userTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, email, enabled, userTypes);
    }

    @JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }

}
