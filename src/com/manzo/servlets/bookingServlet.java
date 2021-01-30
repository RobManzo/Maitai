package com.manzo.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzo.entities.Utente;
import com.manzo.misc.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name="bookingServlet", urlPatterns={"/cliente/booking"})
public class bookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter pr = response.getWriter();
            response.setContentType("application/json");
            LocalDate data;

            if(request.getParameter("rtype").equals("getSeats")){
                int fascia = Integer.parseInt(request.getParameter("FasciaOraria"));
                data = LocalDate.parse(request.getParameter("DataPrenotazione"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                List<Integer> list = Database.getPosti(data, fascia);
                ObjectMapper mapper = new ObjectMapper();
                pr.write("{\"Id\" :"+ mapper.writeValueAsString(list) +"}");
            } else if(request.getParameter("rtype").equals("setBook")){
                Utente user = (Utente) request.getSession().getAttribute("user");
                int fascia = Integer.parseInt(request.getParameter("FasciaOraria"));
                data = LocalDate.parse(request.getParameter("DataPrenotazione"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String postazioni = request.getParameter("Postazioni");
                double prezzo = Double.parseDouble(request.getParameter("Price"));

                if(Database.insertPrenotazione(user, fascia, data, postazioni, prezzo)){
                    pr.write("{\"status\" : \"ok\", \"message\" : \"Prenotazione effettuata.\"}");
                } else pr.write("{\"status\" : \"error\", \"message\" : \"Errore durante la prenotazione.\"}");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/booking.jsp");
        dispatcher.forward(request, response);
    }
}