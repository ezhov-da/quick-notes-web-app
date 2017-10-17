package ru.ezhov;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ru.ezhov.db.LoginChecked;
import ru.ezhov.util.CookieCheck;

/**
 *
 * @author deonisius
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class SrvltLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("email");
        String pass = request.getParameter("pass");

        CookieCheck cookieCheck = new CookieCheck(request, response);
        cookieCheck.check();

        LoginChecked loginChecked = new LoginChecked(name, pass);
        User user = null;
        String pageForRedirect;
        try {
            user = loginChecked.getUser();
        } catch (Exception ex) {
            Logger.getLogger(SrvltLogin.class.getName()).log(Level.SEVERE, null, ex);
            pageForRedirect = "login.jsp";
            response.sendRedirect(pageForRedirect);
            return;
        }
        if (user != null) {
            HttpSession httpSession = request.getSession(true);
            httpSession.setAttribute("user", user);
            HashCode code = Hashing.md5().hashString(pass, Charsets.UTF_8);
            String passHash = code.toString();
            Cookie userCookie = new Cookie("user", name);
            userCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(userCookie);
            Cookie infoCookie = new Cookie("info", passHash);
            infoCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(infoCookie);
            pageForRedirect = "user";
        } else {
            pageForRedirect = "login.jsp";
        }
        response.sendRedirect(pageForRedirect);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
