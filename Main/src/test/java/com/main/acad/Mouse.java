package com.main.acad;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = {"age", "name"})
@ToString
public class Mouse {
    private int age;
    private String name;

    public Mouse(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Mouse() {
    }
}
