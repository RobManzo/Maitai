package com.manzo.servlets;

import com.manzo.entities.Utente;
import com.manzo.misc.Database;
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
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashMap;

/**
 * Classe per la visualizzazione della pagina principale dei clienti e per la gestione delle richieste
 */
@WebServlet(name="homeServlet", urlPatterns={"/cliente/home"})
public class homeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            PrintWriter pr = response.getWriter();
            response.setContentType("application/json");
            LocalDate data = LocalDate.now(ZoneId.of("GMT+2"));
            LocalTime time = LocalTime.now(ZoneId.of("GMT+2"));
            int ts = Miscellaneous.getTimeslot(time);
            int userid =((Utente) request.getSession().getAttribute("user")).getIdUtente();

            if(request.getParameter("rtype").equals("setEntry")){
                //Gestione dell'accesso alla struttura da parte di un cliente autorizzato
                if(ts == -1){
                    pr.write("{\"status\" : \"error\", \"message\" : \"La struttura al momento è chiusa.\"}");
                    return;
                }

                if(Database.setEntry(data, userid, ts, time) != 0){
                    request.getSession().setAttribute("entry", Database.getEntry(LocalDate.now(), userid));
                    pr.write("{\"status\" : \"ok\", \"message\" : \"Entrata autorizzata.\"}");
                }
                else pr.write("{\"status\" : \"error\", \"message\" : \"Non sei autorizzato ad entrare. Controlla le tue prenotazioni.\"}");
            }

            else if(request.getParameter("rtype").equals("getEntry")){
                //Richiesta stato di entrata/uscita di un cliente
                if(Database.getEntry(data, userid) != 0){
                    request.getSession().setAttribute("entry", Database.getEntry(data, userid));
                    pr.write("{\"status\" : \"in\"}");
                } else{
                    request.getSession().setAttribute("entry", null);
                    pr.write("{\"status\" : \"out\"}");
                }
            }

            else if(request.getParameter("rtype").equals("setExit")){
                //Gestione dell'uscita dalla struttura da parte di un cliente autorizzato
                if(Database.setExit(data,userid, time)){
                    request.getSession().setAttribute("cart", new HashMap<String, Integer>());
                    request.getSession().setAttribute("entry", null);
                    pr.write("{\"status\" : \"ok\", \"message\" : \"Uscita autorizzata.\" }");
                } else pr.write("{\"status\" : \"error\", \"message\" : \"Non sei autorizzato ad uscire.\"}");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(request, response);
    }
}