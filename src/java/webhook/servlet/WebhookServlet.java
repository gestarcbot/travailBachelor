package webhook.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import webhook.logic.ChatbotLogic;

/**
 *
 * @author boris.klett
 */
public class WebhookServlet extends HttpServlet {

    @Inject
    ChatbotLogic chatbotLogic;

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
        try {
            StringBuilder jb = new StringBuilder();
            @SuppressWarnings("UnusedAssignment")
            String line = null;

            //Get the request from API.AI
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }

            //Parse the request in JSONObject
            JSONObject jsonObject = new JSONObject(jb.toString());

            //The logic
            JSONObject resultObject = jsonObject.getJSONObject("result");
            String action = resultObject.getString("action");
            String defaultMess = "Désolé! j’ai de la peine à comprendre ce que tu attends réellement. Souhaiterais-tu contacter un membre de la Gest’arc pour te renseigner à ce sujet ? car je suis uniquement là pour répondre aux questions en relations aux différents event de la Gest’arc";

            @SuppressWarnings("UnusedAssignment")
            String speech = new String();

            switch (action) {
                case "shipping-cost":
                    speech = chatbotLogic.getCosts(resultObject);
                    break;
                default:
                    speech = defaultMess;
                    break;
            }
            //Generate the response
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                JSONObject resultOject = new JSONObject("{\n"
                        + "        \"speech\": " + speech + ",\n"
                        + "        \"displayText\": " + speech + ",\n"
                        + "        \"data\": {},\n"
                        + "        \"contextOut\": [],\n"
                        + "        \"source\": \"webhook-boris-test\"}");

                out.println(resultOject);
            }

        } catch (IOException | JSONException ex) {
            Logger.getLogger(WebhookServlet.class.getName()).log(Level.SEVERE, null, ex);
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
