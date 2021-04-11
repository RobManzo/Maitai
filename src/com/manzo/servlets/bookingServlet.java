package com.manzo.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzo.entities.Utente;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe per la visualizzazione del portale prenotazioni e per la gestione delle richieste
 */
@WebServlet(name="bookingServlet", urlPatterns={"/cliente/booking"})
public class bookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            PrintWriter pr = response.getWriter();
            response.setContentType("application/json");
            LocalDate data;
            List<Integer> listocc;

            if(request.getParameter("rtype").equals("getSeats")){
                int fascia = Integer.parseInt(request.getParameter("FasciaOraria"));
                data = LocalDate.parse(request.getParameter("DataPrenotazione"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                listocc = Database.getPosti(data, fascia);
                ObjectMapper mapper = new ObjectMapper();
                pr.write("{\"Id\" :"+ mapper.writeValueAsString(listocc) +"}");
            } else if(request.getParameter("rtype").equals("setBook")){
                Utente user = (Utente) request.getSession().getAttribute("user");
                int fascia = Integer.parseInt(request.getParameter("FasciaOraria"));
                data = LocalDate.parse(request.getParameter("DataPrenotazione"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String postazioni = request.getParameter("Postazioni");
                double prezzo = Double.parseDouble(request.getParameter("Prezzo"));

                if(Miscellaneous.checkDate(data)){
                    pr.write("{\"status\" : \"error\", \"message\" : \"Data di prenotazione errata.\"}");
                }

                List<Integer> pos = new ArrayList<>();
                listocc = Database.getPosti(data, fascia);
                String[] postoarray = postazioni.split(",");

                for(String x : postoarray){
                    pos.add(Integer.parseInt(x));
                }

                pos.retainAll(listocc);

                if(pos.isEmpty()){
                    if(Database.insertPrenotazione(user, fascia, data, postazioni, prezzo)){
                        String messaggio = "<p>Ciao " + user.getNome() + " " + user.getCognome() + ", <br>"
                                + "Ti avvisiamo che la tua prenotazione per il "+ data.format(DateTimeFormatter.ofPattern("dd MM yyyy")) +" è stata correttamente inviata.<br><br>"
                                + "Postazioni: " + postazioni + "<br>"
                                + "Fascia Oraria: " + fascia + "<br>"
                                + "Costo: " + prezzo + "€<br><br>"
                                + "<br>A presto! <br>"
                                + "Lido Maitai</p>";
                            MailService mailer = new MailService(user.getEmail(), "Lido Maitai - Conferma Prenotazione", messaggio);
                        Thread thread = new Thread(mailer);
                        thread.start();

                        pr.write("{\"status\" : \"ok\", \"message\" : \"Prenotazione effettuata.\"}");
                    } else pr.write("{\"status\" : \"error\", \"message\" : \"Errore durante la prenotazione.\"}");
                }else pr.write("{\"status\" : \"error\", \"message\" : \"Una o più postazioni sono già occupate.\"}");

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