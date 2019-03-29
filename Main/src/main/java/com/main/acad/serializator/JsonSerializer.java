package com.main.acad.serializator;

public interface JsonSerializer {
    String write(Object obj) throws IllegalAccessException;

    Object read(String string, Class clazz, Class objClass) throws Exception;
}
