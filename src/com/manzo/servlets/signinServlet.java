package com.manzo.servlets;

import com.manzo.misc.Database;
import com.manzo.misc.MailService;
import com.manzo.misc.Miscellaneous;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
 * Classe per la visualizzazione della paqina di registrazione e per la gestione dell'inoltro dei dati
 */
@WebServlet(name="signinServlet", urlPatterns={"/signin"})
public class signinServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String name, surname, codfisc, address, province, email, pass, confpass, phone, error;
            name = request.getParameter("Nome");
            surname = request.getParameter("Cognome");
            LocalDate birthdate = LocalDate.parse(request.getParameter("Birthdate"));
            codfisc = request.getParameter("CFiscale");
            address = request.getParameter("Indirizzo");
            email = request.getParameter("Email");
            province = request.getParameter("Provincia");
            pass = request.getParameter("Password");
            confpass = request.getParameter("Confpass");
            phone = request.getParameter("Telefono");

            PrintWriter pr = response.getWriter();
            response.setContentType("application/json");
            String status;

            if((error = Miscellaneous.checkForm(name, surname, email, pass, confpass, phone, birthdate.toString(), codfisc)) != null){
                //Check dati inseriti
                status = "{\"RESPONSE\" : \"Error\", \"Message\" : \"" + error + " Verrai reinderizzato all'inizio." + "\"}";

            } else if(Database.checkExist(email, codfisc)) {
                //Verifica l'esistenza dell'account nel database
                status = "{\"RESPONSE\" : \"Error\", \"Message\" : \"Email o persona già registrata. Verrai reinderizzato all'inizio.\"}";
            } else {
                //Effettua la registrazione dell'utente
                Database.userSignIn(name, surname, email, codfisc, phone, birthdate, pass, address, province);
                //Preparazione ed invio email di conferma registrazione
                String messaggio = "<p>Ciao " + name + " " + surname + ", <br>"
                        + "La registrazione è andata a buon fine. Ti auguriamo una buona permanenza presso la nostra struttura.<br><br>"
                        + "Password: " + pass + "<br>"
                        + "<br>A presto! <br>"
                        + "Lido Maitai</p>";
                MailService mailer = new MailService(email, "Lido Maitai - Conferma Prenotazione", messaggio);
                Thread thread = new Thread(mailer);
                thread.start();
                status = "{\"RESPONSE\" : \"Correct\", \"Message\" : \"Registrazione effettuata. Verrai reinderizzato alla home.\"}";
            }

            pr.write(status);

        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400);
        }

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/signin.jsp");
        dispatcher.forward(request, response);
    }
}