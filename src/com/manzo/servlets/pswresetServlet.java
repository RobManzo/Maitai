package com.manzo.servlets;

import com.manzo.misc.Database;
import com.manzo.misc.MailService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Classe per la gestione del reset della password
 */
@WebServlet(name="pswresetServlet", urlPatterns={"/pswreset"})

public class pswresetServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("rtype")!=null && request.getParameter("rtype").equals("pswReset")){
            // Richiesta di reimpostazione password
            String email = request.getParameter("email");
            String codfisc = request.getParameter("codfisc");
            try {
                PrintWriter pr = response.getWriter();
                response.setContentType("application/json");
                // Generazione nuova password
                String newpsw = Database.resetPassword(email, codfisc);
                if(newpsw!=null){
                    // Preparazione ed invio email con nuova password
                    String messaggio = "<p>Ciao, <br>"
                            + "Ecco a te la tua nuova password per accedere al portale.<br><br>"
                            + "Nuova Password: " + newpsw + "<br>"
                            + "<br>A presto! <br>"
                            + "Lido Maitai</p>";
                    MailService mailer = new MailService(email, "Lido Maitai - Reset Password", messaggio);
                    Thread thread = new Thread(mailer);
                    thread.start();
                    pr.write("{\"Message\" : \"Controlla la tua casella mail per consultare la nuova password. \"}");
                } else pr.write("{\"Message\" : \"Utente non trovato o dati errati. \"}");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(request, response);

    }
}
