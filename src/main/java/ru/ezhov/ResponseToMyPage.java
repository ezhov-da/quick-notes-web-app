package ru.ezhov;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import ru.ezhov.db.GetNote;
import ru.ezhov.db.NoteDetail;
import ru.ezhov.util.CodeFinder;
import ru.ezhov.util.LinkChecker;

/**
 *
 * @author ezhov_da
 */
@WebServlet(name = "ResponseToMyPage", urlPatterns = {"/responseToMyPage"})
public class ResponseToMyPage extends HttpServlet {

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

        response.setContentType("text/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = null;
        try {

            String s = request.getParameter("id");
            GetNote getNote = new GetNote();
            try {
                NoteDetail noteDetail = getNote.getNoteDetail(Integer.valueOf(s));

                String text = noteDetail.getText();
                CodeFinder codeFinder = new CodeFinder();
                text = codeFinder.replaceCodeTag(text);

                printWriter = response.getWriter();

                LinkChecker linkChecker = new LinkChecker(noteDetail.getLink());
                String link;
                if (linkChecker.check()) {
                    link = "<a href=\"" + noteDetail.getLink() + "\">Link to original</a>";
                } else {
                    link = "No link";
                }

                String dateAdd = "    <p>"
                        + "<small>Date add: " + noteDetail.getDateAddStr() + "</small>"
                        + "</p>";

                String dateEdit;
                if ("".equals(noteDetail.getDateEditStr())) {
                    dateEdit = "";
                } else {
                    dateEdit = "<p>"
                            + "<small>Date edit: " + noteDetail.getDateEditStr() + "</small>"
                            + "</p>";
                }

                String select
                        = "<h3 class=\"page-header\">" + noteDetail.getName() + "</h3> " + dateEdit + dateAdd + " <div id=\"idNote\" value=\"" + noteDetail.getId() + "\"></div>"
                        + "<div><p>"
                        + "<button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"collapse\"  id=\"buttonEdit\" data-target=\"#editFrame\" onClick=\"CKEDITOR.replace('editorEdit');\">Edit note</button>\n"
                        + "<button type=\"button\" class=\"btn btn-info btn-sm\" id=\"buttonDeleteNote\">Delete note</button></div></p>\n"
                        + "\n"
                        + "<div id=\"editFrame\" class=\"collapse\">\n"
                        + "                        <form id=\"editNote\" method=\"post\" class=\"note-form\">\n"
                        + "                            <div class=\"label-caption\">\n"
                        + "                                <label for=\"caption\">Name:</label>\n"
                        + "                                <input name=\"name\" id=\"nameEdit\" type=\"text\">\n"
                        + "                            </div>\n"
                        + "                            <div class=\"label-caption\">\n"
                        + "                                <label for=\"outlink\">Link:</label>\n"
                        + "                                <input name=\"link\" id=\"linkEdit\" type=\"text\">\n"
                        + "                            </div>\n"
                        + "                            <button  type=\"button\" class=\"btn btn-info btn-sm\" id=\"insertCodeToTextEdit\">Inser code</button>\n"
                        + "                            <div class=\"label-caption\">\n"
                        + "                                <label for=\"content\">Text:</label>		\n"
                        + "                                <span class=\"area-text-block\"><textarea id=\"texEdit\" name=\"editorEdit\"></textarea></span>\n"
                        + "                            </div>\n"
                        + "                            <input  id=\"dateTimeEdit\" type=\"text\" hidden=\"true\" name=\"dateTime\">\n"
                        + "                            <input name=\"id\" id=\"idForEdit\" type=\"hidden\" value=\"\">\n"
                        + "                            <div class=\"footer-button-block\">\n"
                        + "                                <input id=\"editNote\" name=\"edit\" value=\"Edit\" type=\"button\"  class=\"btn btn-info btn-md\"    onclick=\"\n"
                        + "                                        $('#dateTimeEdit').val(getDateTimeNowStr());\n"
                        + "                                        ajaxFormRequest('editNote', 'editNote', function () {\n"
                        + "                                            $('#myModalEdit').modal('hide');\n"
                        + "                                            loadList();\n"
                        + "                                            loadSelectedLink(lastSelectedId);\n"
                        + "                                        })\">\n"
                        + "                            </div>\n"
                        + "                        </form>   \n"
                        + "</div>    "
                        + " <div id=\"linkNote\"><p>" + link + "</p></div>"
                        + "<h2 class=\"sub-header\"></h2>"
                        + text;

                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", noteDetail.getId());
                jSONObject.put("name", noteDetail.getName());
                jSONObject.put("link", noteDetail.getLink());
                jSONObject.put("text", text);
                jSONObject.put("rawText", noteDetail.getText());
                jSONObject.put("html", select);

                String json = jSONObject.toJSONString();
                printWriter.print(json);
                printWriter.flush();

            } finally {
                if (printWriter != null) {
                    printWriter.close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ResponseToMyPage.class.getName()).log(Level.SEVERE, null, ex);
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
