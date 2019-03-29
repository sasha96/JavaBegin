package com.main.acad.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.experimental.Builder;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@Builder(builderMethodName = "_myuserbuilder")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"login", "password", "role"})

public class User {
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String login;
    private Integer password;
    private String role;
}
