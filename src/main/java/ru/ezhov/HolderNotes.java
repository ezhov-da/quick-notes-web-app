package ru.ezhov;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.ezhov.db.Note;
import ru.ezhov.db.SelectNotes;

/**
 *
 * @author ezhov_da
 */
@WebServlet(name = "HolderNotes", urlPatterns = {"/holderNotes"})
public class HolderNotes extends HttpServlet {

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

        HttpSession httpSession = request.getSession(true);
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            SelectNotes selectNotes = new SelectNotes(user.getId());
            List<Note> list;
            try {
                list = selectNotes.getNotes();

                JSONArray array = new JSONArray();

                list.forEach(n -> {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", n.getId());
                    jSONObject.put("name", n.getName());
                    jSONObject.put("html", "<li><a  class=\"go\" id = \"" + n.getId() + "\">" + n.getName() + "</a></li>");
                    array.add(jSONObject);
                });
                response.setContentType("text/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(array.toJSONString());
            } catch (Exception ex) {
                Logger.getLogger(HolderNotes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

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
