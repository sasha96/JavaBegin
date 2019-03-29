package com.main.acad;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import lombok.experimental.Builder;


@Getter
@Setter
@Builder(builderMethodName = "_myuserbuilder")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"login", "password", "role"})

public class User {
    private String login;
    private Integer password;
    private String role;
}