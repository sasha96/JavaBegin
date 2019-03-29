package com.main.acad.serializator;

import com.main.acad.entity.User;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class JsonSerializatorImplementation implements JsonSerializer {

    @Override
    public String write(Object obj) throws IllegalAccessException {
        if (obj instanceof List) {
            return writeList(obj);
        } else {
            return writeObj(obj);
        }
    }

    private String writeList(Object obj) throws IllegalAccessException {
        List list = (List) obj;
        String result = "[";
        for (Object listItem : list) {
            result = result + writeObj(listItem) + ",";
        }
        if (list.size() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return result + "]";
    }

    private String writeObj(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return null;
        }
        String result = "{";
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            boolean isNumber = field.get(obj) instanceof Number;
            boolean isBoolean = field.get(obj) instanceof Boolean;
            boolean isString = field.get(obj) instanceof String;
            boolean isList = field.get(obj) instanceof List;
            if (isNumber || isBoolean) {
                result = result + "\"" + field.getName() + "\":" +
                        field.get(obj) + ",";
            } else {
                if (!isNumber && !isBoolean && !isString && !isList) {
                    result = result + "\"" + field.getName() + "\":" + writeObj(field.get(obj)) + ",";
                } else if (isList) {
                    result = result + "\"" + field.getName() + "\":" + writeList(field.get(obj)) + ",";
                } else {
                    result = result + "\"" + field.getName() + "\":\"" +
                            field.get(obj) + "\",";
                }
            }

        }
        result = result.substring(0, result.length() - 1);
        return result + "}";
    }

    @Override
    public Object read(String string, Class clazz, Class objClass) throws Exception {
        if (clazz.getTypeName().equals("java.util.List")) {
            return readList(string, clazz, objClass);
        } else {
            return readObject(string, clazz);
        }
    }

    private Object readObject(String string, Class clazz) throws Exception {

        string = string.replaceAll("\"", "");
        string = string.replaceAll("}", ",");
        Object objec = clazz.newInstance();
        Field field[] = clazz.getDeclaredFields();
       // string += ",";
        for (Field f : field) {
            if (!string.contains(":")) {
                f.setAccessible(false);
            } else {
                f.setAccessible(true);
                if (f.getType() == Integer.class) {
                    f.set(objec, Integer.parseInt(string.substring(string.indexOf(":") + 1, string.indexOf(","))));
                    string = string.substring(string.indexOf(",") + 1);
                } else if (f.getType() == Boolean.TYPE) {
                    f.set(objec, Boolean.parseBoolean(string.substring(string.indexOf(":") + 1, string.indexOf(","))));
                    string = string.substring(string.indexOf(",") + 1);
                } else if (f.getType() != String.class && f.getType() != List.class) {
                    String s = string.substring(string.indexOf("{"));
                    f.set(objec, readObject(s, Class.forName(String.valueOf(f.getType().getName()))));
                } else if (f.getType() == List.class) {
                    if (string.contains("null")) {
                        f.set(objec, readList(string, List.class, null));
                    } else {
                        String s = string.substring(string.indexOf("["), string.indexOf("]") + 1);
                        char[] chars = s.toCharArray();
                        int counter = 0;
                        for (int i = 0; i < chars.length; i++) {
                            if (chars[i] == '[') {
                                counter++;
                            } else if (chars[i] == '{') {
                                counter++;
                            } else if (chars[i] == ']') {
                                counter--;
                            } else if (chars[i] == '}') {
                                counter--;
                            }
                            if (counter == 0) {
                                s = string.substring(string.indexOf('['), string.indexOf(chars[i]) + 1);
                                f.set(objec, readList(s, (Class<?>) (((ParameterizedType) f.getGenericType()).getActualTypeArguments()[0]), null));
                            }
                        }
                    }
                } else {
                    f.set(objec, (string.substring(string.indexOf(":") + 1, string.indexOf(","))));
                    string = string.substring(string.indexOf(",") + 1);
                }
            }
        }
        return objec;
    }

    private List<String> readList(String string, Class clazz, Class objClass) throws Exception {
        System.out.println(string);
        if (objClass != null) {
            string = string.replaceAll("\\[", "");
            string = string.replaceAll("]", "");
            List<String> list = new ArrayList();
            char[] chars = string.toCharArray();
            int counter = 0;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '{') {
                    counter++;
                } else if (chars[i] == '}') {
                    counter--;
                }
                if (counter == 0) {
                    if (!string.equals("")) {
                        String w = string.substring(string.indexOf("{"), string.indexOf("}"));
                        list.add((String) readObject(w, objClass));
                        string = string.substring(string.indexOf("}") + 1, string.length());
                    }
                }
            }
            return list;
        }

        if (string.contains("{")) {
            List list = new ArrayList();
            int o = 0;
            int counter = 0;
            char[] chars = string.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '{') {
                    counter++;
                } else if (chars[i] == '}') {
                    counter--;
                    if (counter == 0) {
                        if (chars[i + 1] == ',') {
                            o++;
                            break;
                        }
                    }
                }
            }
            for (int r = 0; r <= o; r++) {
                if (string.contains("},")) {
                    list.add(string.substring(string.indexOf("\"") + 1, string.indexOf(",")));
                    string = string.substring(string.indexOf(",") + 1);
                } else {
                    list.add(read(string, clazz, null));

                    return list;
                }
            }
            return list;
        } else {
            List<String> list = new ArrayList<>();
            int o = 0;
            char[] chars = string.toCharArray();
            for (char aChar : chars) {
                if (aChar == ',')
                    o++;
            }
            for (int r = 0; r <= o; r++) {
                System.out.println(string);
                string = string.replace("{", "");
                if (string.contains(",")) {
                    if (string.substring(string.indexOf(":")).contains("null")) {
                        list.add(null);
                        return list;
                    }
                    list.add(string.substring(string.indexOf("\"") + 1, string.indexOf(",") - 1));
                    string = string.substring(string.indexOf(",") + 1);
                } else {
                    list.add(string.substring(string.indexOf(",") + 2, string.length() - 2));
                    return list;
                }
            }
            return list;
        }
    }
}
