package com.main.acad;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = {"name", "enemies"})
@ToString
public class Dog {
//    private String nameD;
//    private List<Cat> enemies;
//
//    public Dog() {
//    }
//
//    public Dog(String nameD, List<Cat> enemies) {
//        this.nameD = nameD;
//        this.enemies = enemies;
//    }
}
