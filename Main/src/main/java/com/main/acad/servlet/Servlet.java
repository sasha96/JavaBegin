package com.main.acad.servlet;

import com.main.acad.annotation.MappingMethod;
import com.main.acad.error.ControllerNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Servlet extends HttpServlet implements javax.servlet.Servlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        String url = request.getRequestURL().toString();
        url = url.substring(url.indexOf("4") + 2);
        try {
            invokeController(url, request, response);
        } catch (Exception e) {
            try {
                logger.info("An error occurred in the Servlet class in the doGet method" + e.getMessage());
                response.sendRedirect(response.encodeRedirectURL("/exception_page.html"));
            } catch (Exception e1) {
                throw new ControllerNotFoundException(e.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String url = request.getRequestURL().toString();
        url = url.substring(url.indexOf("4") + 2);
        try {
            invokeController(url, request, response);
        } catch (Exception e) {
            try {
                logger.info("An error occurred in the Servlet class in the doPost method" + e.getMessage());
                response.sendRedirect(response.encodeRedirectURL("/exception_page.html"));
            } catch (IOException e1) {
                throw new ControllerNotFoundException(e.getMessage());
            }
        }
    }

    private void invokeController(String url, HttpServletRequest request, HttpServletResponse response) {
        List<Class<?>> listControllers = null;
        try {
            listControllers = getAllControllers("com.main.acad.controller");
            for (Class<?> foreachControllers : listControllers) {
                String methodOfController = getMethod(foreachControllers, url);
                if (methodOfController != null) {
                    Class clazz = Class.forName(foreachControllers.getName());
                    Object newInstance = clazz.newInstance();
                    Class newInstanceClass = newInstance.getClass();
                    Class[] paramTypes = new Class[]{HttpServletRequest.class, HttpServletResponse.class};
                    Method method = newInstanceClass.getMethod(methodOfController, paramTypes);
                    Object[] args = new Object[]{request, response};
                    method.invoke(newInstance, args);
                }
            }
        } catch (Exception e) {
            logger.info("An error occurred in the Servlet class in the invokeController method" + e.getMessage());
            throw new ControllerNotFoundException(e.getMessage());
        }
    }

    private List<Class<?>> getAllControllers(String namePackage) {
        ClassLoader classLoader = MappingMethod.class.getClassLoader();
        String packageDir = namePackage.replaceAll("[.]", "/");
        List<Class<?>> listControllers = new ArrayList<>();
        URL packageName = classLoader.getResource(packageDir);
        InputStream inputStream = null;
        try {
            inputStream = (InputStream) packageName.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.endsWith(".class"))
                    listControllers.add(Class.forName(namePackage + "." + line.substring(0, line.lastIndexOf('.'))));
            }
            return listControllers;
        } catch (IOException | ClassNotFoundException e) {
            logger.info("An error occurred in the Servlet class in the getAllControllers method" + e.getMessage());
            throw new ControllerNotFoundException(e.getMessage());
        }
    }

    private String getMethod(Class clazz, String url) {
        Method[] method = clazz.getMethods();
        String nameMethod = null;
        for (Method searchMethod : method) {
            if (searchMethod.isAnnotationPresent(MappingMethod.class)) {
                if (Arrays.toString(searchMethod.getAnnotations()).contains(url)) {
                    nameMethod = searchMethod.getName();
                    return nameMethod;
                }
            }
        }
        return nameMethod;
    }
}