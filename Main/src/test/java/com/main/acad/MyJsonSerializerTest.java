package com.main.acad;

import com.main.acad.serializator.JsonSerializatorImplementation;
import com.main.acad.serializator.JsonSerializer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyJsonSerializerTest {

    JsonSerializer serializer;

    @Before
    public void setUp() {
        serializer = new JsonSerializatorImplementation();
    }

//    @Test
//    public void testWriteMouse() throws IllegalAccessException {
//        Mouse mouse = new Mouse();
//        mouse.setAge(10);
//        mouse.setName("mouse");
//        String expectedResult = "{\"age\":10,\"name\":\"mouse\"}";
//        String actualResult = serializer.write(mouse);
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    public void testWriteCat() throws IllegalAccessException {
//        Cat cat = new Cat();
//        cat.setName("Cat");
//        cat.setAge(10);
//        cat.setHungry(true);
//        List<Cat> list = new ArrayList<>();
//        cat.setEnemy(new Dog("Dog1", list));
//        String expectedResult = "{\"name\":\"Cat\",\"age\":10,\"isHungry\":true,\"enemy\":{\"nameD\":\"Dog1\",\"enemies\":[]}}";
//        String actualResult = serializer.write(cat);
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    public void testWriteDog() throws IllegalAccessException {
//        Cat cat = new Cat();
//        cat.setName("Cat");
//        cat.setAge(10);
//        cat.setHungry(true);
//        cat.setEnemy(null);
//        List<Cat> list = new ArrayList<>();
//        list.add(cat);
//        Dog dog = new Dog("Dog", list);
//        String expectedResult = "{\"nameD\":\"Dog\",\"enemies\":[{\"name\":\"Cat\",\"age\":10,\"isHungry\":true,\"enemy\":null}]}";
//        String actualResult = serializer.write(dog);
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    public void testWriteListOfCats() throws IllegalAccessException {
//        Cat cat1 = new Cat();
//        cat1.setName("Cat1");
//        cat1.setAge(10);
//        cat1.setEnemy(new Dog("Dog1", new ArrayList<>()));
//        cat1.setHungry(true);
//        Cat cat2 = new Cat();
//        cat2.setName("Cat2");
//        cat2.setAge(5);
//        cat2.setHungry(false);
//        cat2.setEnemy(new Dog("Dog2", new ArrayList<>()));
//        String expectedResult = "[{\"name\":\"Cat1\",\"age\":10,\"isHungry\":true,\"enemy\":{\"nameD\":\"Dog1\",\"enemies\":[]}}" +
//                ",{\"name\":\"Cat2\",\"age\":5,\"isHungry\":false,\"enemy\":{\"nameD\":\"Dog2\",\"enemies\":[]}}]";
//        String actualResult = serializer.write(Arrays.asList(cat1, cat2));
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    public void testReadUser(){
//        String json = "{\"login\":\"sasha\",\"password\":1,\"role\":\"admin\"}";
//        User user = new User();
//        user.setLogin("sasha");
//        user.setPassword(1);
//        user.setRole("admin");
//        Object actualResult = null;
//        try {
//            actualResult = serializer.read(json, User.class, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        assertEquals(user, actualResult);
//    }
//
//    @Test
//    public void testReadList() throws Exception {
//        List<String> list = new ArrayList<>();
//        list.add("window");
//        list.add("desc");
//        list.add("pen");
//        String expectedResult = "[\"window\",\"desc\",\"pen\"]";
//        Object actualResult = serializer.read(expectedResult, list.getClass(), null);
//        assertEquals(list, actualResult);
//    }
//
//    @Test
//    public void testReadMouseList() throws Exception {
//        List<Mouse> list = new ArrayList<>();
//        Mouse mouse = new Mouse();
//        mouse.setAge(10);
//        mouse.setName("mouse");
//        Mouse mouse2 = new Mouse();
//        mouse2.setAge(9);
//        mouse2.setName("micky");
//        String json = "[{\"age\":10,\"name\":\"mouse\"},{\"age\":9,\"name\":\"micky\"}]";
//        list.add(mouse);
//        list.add(mouse2);
//        Object actualResult = serializer.read(json, list.getClass(), Mouse.class);
//        assertEquals(list, actualResult);
//    }

//    @Test
//    public void testReadCatsList() throws Exception {
//        List<Cat> list = new ArrayList<>();
//        Cat cat1 = new Cat();
//        cat1.setName("Cat1");
//        cat1.setAge(5);
//        cat1.setEnemy(new Dog("pety", null));
//        list.add(cat1);
//
//        String json = "[{\"name\":\"Cat1\",\"age\":5,\"isHungry\":false,\"enemy\":{\"nameD\":\"pety\",\"enemies\":null}}]";
//
//        Object actualResult = serializer.read(json, list.getClass(), Cat.class);
//        assertEquals(list, actualResult);
//    }
//
//    @Test
//    public void testReadCatsListWithEmptyEnemies() throws Exception {
//        List<Cat> list = new ArrayList<>();
//        Cat cat1 = new Cat();
//        cat1.setName("Cat1");
//        cat1.setAge(5);
//        cat1.setEnemy(new Dog("pety", new ArrayList<>()));
//        list.add(cat1);
//
//        String json = "[{\"name\":\"Cat1\",\"age\":5,\"isHungry\":false,\"enemy\":{\"nameD\":\"pety\",\"enemies\":[]}}]";
//
//        Object actualResult = serializer.read(json, Cat.class, Dog.class);
//        assertEquals(list, actualResult);
//    }
//
//    @Test
//    public void testReadCatsListWithDogsEnemy() throws Exception {
//        Cat enemy = new Cat();
//        enemy.setAge(2);
//
//        List<Cat> list = new ArrayList<>();
//        Cat cat1 = new Cat();
//        cat1.setName("Cat1");
//        cat1.setAge(5);
//        cat1.setEnemy(new Dog("pety", Arrays.asList(enemy)));
//        list.add(cat1);
//
//        String json = "[{\"name\":\"Cat1\",\"age\":5,\"isHungry\":false,\"enemy\":{\"nameD\":\"pety\",\"enemies\":[{\"name\":null,\"age\":2,\"isHungry\":false,\"enemy\":null}]}}]";
//
//        Object actualResult = serializer.read(json, Cat.class, Dog.class);
//        assertEquals(list, actualResult);
//    }
}
