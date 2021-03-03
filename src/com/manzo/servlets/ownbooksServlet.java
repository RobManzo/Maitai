package com.manzo.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzo.entities.Prenotazione;
import com.manzo.entities.Utente;
import com.manzo.misc.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name="ownbooksServlet", urlPatterns={"/cliente/ownbooks"})
public class ownbooksServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter pr = response.getWriter();
            response.setContentType("application/json");
            Utente user = (Utente) request.getSession().getAttribute("user");

            if(request.getParameter("rtype").equals("getBooks")){
                List<Prenotazione> prenotazioni = Database.getPrenotazioni(user);

                ObjectMapper mapper = new ObjectMapper();
                pr.write("{\"Prenotazioni\" :"+ mapper.writeValueAsString(prenotazioni) +"}");
            }

            else if(request.getParameter("rtype").equals("getPren")){
                Prenotazione prenotazione = Database.getPrenotazione(user,Integer.parseInt(request.getParameter("idPren")));
                ObjectMapper mapper = new ObjectMapper();
                pr.write("{\"Prenotazione\" :"+mapper.writeValueAsString(prenotazione)+", \"Today\" :"+mapper.writeValueAsString(LocalDate.now())+"}");
            }

            else if(request.getParameter("rtype").equals("delPren")){
                if(Database.deletePrenotazione(user,Integer.parseInt(request.getParameter("idPren")))){
                    pr.write("{\"status\" : \"error\", \"message\" : \"Eliminazione effettuata con successo\"}");
                } else pr.write("{\"status\" : \"error\", \"message\" : \"Errore durante l'eliminazione.\"}");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ownbooks.jsp");
        dispatcher.forward(request, response);
    }
}