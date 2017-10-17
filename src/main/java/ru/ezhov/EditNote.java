package ru.ezhov;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.ezhov.db.NoteDetail;
import ru.ezhov.db.UpdateNote;
import ru.ezhov.util.DateTimeConverter;
import ru.ezhov.util.HtmlToText;

/**
 *
 * @author deonisius
 */
@WebServlet(name = "EditNote", urlPatterns = {"/editNote"})
public class EditNote extends HttpServlet {

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

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String text = request.getParameter("text");
        String link = request.getParameter("link");
        String dateTime = request.getParameter("dateTime");

        if (dateTime == null) {
            return;
        }

        DateTimeConverter dateTimeConverter = new DateTimeConverter(dateTime);
        Timestamp timestamp;
        try {
            timestamp = dateTimeConverter.getTimeStamp();
        } catch (ParseException ex) {
            Logger.getLogger(AddNewToBase.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        User user = (User) request.getSession(true).getAttribute("user");

        if (user != null) {
            name = HtmlToText.convert(name);
            link = HtmlToText.convert(link);
            //TODO: вносить нужную дату
            NoteDetail noteDetail = new NoteDetail(text, link, Integer.valueOf(id), name, null, timestamp);
            UpdateNote updateNote = new UpdateNote(noteDetail, user.getId());
            try {
                updateNote.update();
            } catch (Exception ex) {
                Logger.getLogger(EditNote.class.getName()).log(Level.SEVERE, null, ex);
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
