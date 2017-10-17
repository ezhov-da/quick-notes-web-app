package ru.ezhov.util;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ru.ezhov.User;
import ru.ezhov.db.CookieUserCheck;

/**
 * Класс, который проверяет куки и в случае соответсвия сразу перенаправляет на
 * старницу пользователя
 *
 * @author deonisius
 */
public class CookieCheck {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public CookieCheck(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public boolean check() throws IOException {
        if (request.getSession(true).getAttribute("user") != null) {
            return true;
        }

        String user = null;
        String info = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return false;
        }
        String name;
        for (Cookie cookie : cookies) {
            name = cookie.getName();
            if ("user".equals(name)) {
                user = cookie.getValue();
            } else if ("info".equals(name)) {
                info = cookie.getValue();
            }
        }

        if (user != null && info != null) {
            CookieUserCheck cookieUserCheck = new CookieUserCheck(user);
            try {
                User userObject = cookieUserCheck.getUser();
                HashCode code = Hashing.md5().hashString(userObject.getPass(), Charsets.UTF_8);
                String passHash = code.toString();
                if (info.equals(passHash)) {
                    HttpSession httpSession = request.getSession(true);
                    httpSession.setAttribute("user", userObject);
                    return true;
                } else {
                    return false;
                }
            } catch (Exception ex) {
                Logger.getLogger(CookieCheck.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }
}
