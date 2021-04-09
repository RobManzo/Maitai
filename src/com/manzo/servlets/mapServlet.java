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
import java.time.LocalTime;
import java.util.List;

@WebServlet(name="mapServlet", urlPatterns={"/staff/map"})
public class mapServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter pr = response.getWriter();
            response.setContentType("application/json");

            if(request.getParameter("rtype").equals("getBookings")){
                List<Prenotazione> prenotazioni = Database.getPrenotazioni(false);
                ObjectMapper mapper = new ObjectMapper();
                pr.write("{\"Prenotazioni\" :"+ mapper.writeValueAsString(prenotazioni) +"}");
            }

            else if(request.getParameter("rtype").equals("getPren")){
                Prenotazione prenotazione = Database.getPrenotazione(Integer.parseInt(request.getParameter("idPren")));
                int idUser = Database.userByPrenotazione(Integer.parseInt(request.getParameter("idPren")));
                ObjectMapper mapper = new ObjectMapper();
                pr.write("{\"Prenotazione\" :"+mapper.writeValueAsString(prenotazione)+", \"UserId\" : "+idUser+",  \"Today\" :"+mapper.writeValueAsString(LocalDate.now())+"}");
            }

            else if(request.getParameter("rtype").equals("setExit")){
                if(Database.setExit(Integer.parseInt(request.getParameter("ID")), LocalTime.now())) pr.write("{\"Message\" : \"Utente con prenotazione # "+ request.getParameter("ID") +" è uscito. \"}");
                else pr.write("{\"Message\" : \"Errore\"}");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/map.jsp");
        dispatcher.forward(request, response);
    }
}