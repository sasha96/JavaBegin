package com.main.acad.servlet;

import com.main.acad.dao.SimpleUserDao;
import com.main.acad.dao.UserDao;
import com.main.acad.error.NoAccessToFileException;
import com.main.acad.error.UserDaoFailedException;
import com.main.acad.util.EncryptUtils;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

public class CookieFilter implements Filter {
    private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURL().toString();
        if (url.contains("api/checkDateUser")||url.contains("api/createNewUser")) {
            try {
                chain.doFilter(request, response);
            } catch (ServletException | UserDaoFailedException | IOException e) {
                throw new NoAccessToFileException("error in CookieFilter");
            }
        } else {
            Cookie arrayCookies[] = httpServletRequest.getCookies();
            String cookieNameUser = "";
            ;
            String cookiePassword = "";
            for (Cookie cokies : arrayCookies) {
                String password = cokies.getName();
                String test1 = EncryptUtils.base64decode(password);
                String nameUser = cokies.getValue();
                String subString = nameUser.substring(0, 2);
                password += subString;
                nameUser = nameUser.substring(2, nameUser.length());
                String test2 = EncryptUtils.base64decode(password);
                cookiePassword = EncryptUtils.base64decode(password);
                cookieNameUser = EncryptUtils.base64decode(nameUser);
            }
            UserDao dao = SimpleUserDao.getInstance();
            String userStatus = dao.findUserByLogin(cookieNameUser, cookiePassword);
            if (userStatus.equals("admin")) {
                try {
                    chain.doFilter(request, response);
                } catch (ServletException | IOException e) {
                    throw new NoAccessToFileException("error in CookieFilter");
                }
            } else if (!userStatus.equals("admin")) {
                if (url.equals("http://localhost:8084/api/createFile") ||
                        url.equals("http://localhost:8084/api/deleteSubChapter") ||
                        url.equals("http://localhost:8084/api/updateSubChapter")
                        ) {
                } else {
                    try {
                        chain.doFilter(request, response);
                    } catch (ServletException | UserDaoFailedException | IOException e) {
                        throw new NoAccessToFileException("error in CookieFilter");
                    }
                }
            }
        }
    }


    @Override
    public void destroy() {

    }

}
