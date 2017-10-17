package ru.ezhov.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ru.ezhov.util.CookieCheck;

/**
 * Фильтр, который отвечает за проверку авторизации данных
 *
 * @author deonisius
 */
public class FilterLogOn implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);
        String loginURI = httpServletRequest.getContextPath() + "/login.jsp";
        String loginURISrvlt = httpServletRequest.getContextPath() + "/login";

        //смотри есть ли куки
        CookieCheck cookieCheck = new CookieCheck(httpServletRequest, httpServletResponse);
        cookieCheck.check();        
        
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = httpServletRequest.getRequestURI().equals(loginURI);
        boolean loginRequestSrvlt = httpServletRequest.getRequestURI().equals(loginURISrvlt);
        
        if (loggedIn || loginRequest || loginRequestSrvlt) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {

    }

}
