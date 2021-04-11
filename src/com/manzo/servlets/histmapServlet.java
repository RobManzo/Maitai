package com.manzo.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzo.entities.Prenotazione;
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

/**
 * Classe per la visualizzazione dello storico delle prenotazioni e per la gestione delle richieste
 */
@WebServlet(name="histmapServlet", urlPatterns={"/admin/histbook"})
public class histmapServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            PrintWriter pr = response.getWriter();
            response.setContentType("application/json");

            if(request.getParameter("rtype").equals("getBookings")){
                //Richiesta dello storico delle prenotazioni
                List<Prenotazione> prenotazioni = Database.getPrenotazioni(true);
                ObjectMapper mapper = new ObjectMapper();
                pr.write("{\"Prenotazioni\" :"+ mapper.writeValueAsString(prenotazioni) +"}");
            }

            else if(request.getParameter("rtype").equals("getPren")){
                //Richiesta dettagli singola prenotazione
                Prenotazione prenotazione = Database.getPrenotazione(Integer.parseInt(request.getParameter("idPren")));
                int idUser = Database.userByPrenotazione(Integer.parseInt(request.getParameter("idPren")));
                ObjectMapper mapper = new ObjectMapper();
                pr.write("{\"Prenotazione\" :"+mapper.writeValueAsString(prenotazione)+", \"UserId\" : "+idUser+",  \"Today\" :"+mapper.writeValueAsString(LocalDate.now())+"}");
            }

            else if(request.getParameter("rtype").equals("delPren")){
                //Cancellazione prenotazione dalla piattaforma
                if(Database.deletePrenotazione(Integer.parseInt(request.getParameter("idPren")))){
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/histmap.jsp");
        dispatcher.forward(request, response);
    }
}