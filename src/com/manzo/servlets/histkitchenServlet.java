package com.manzo.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzo.entities.Ordine;
import com.manzo.misc.Database;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Classe per la visualizzazione dello storico degli ordini e per la gestione delle richieste
 */
@WebServlet(name="histkitchenServlet", urlPatterns={"/admin/histkitchen"})
public class histkitchenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            PrintWriter pr = response.getWriter();
            response.setContentType("application/json");

            if(request.getParameter("rtype").equals("getOrders")){
                // Richiesta dello storico degli ordini
                List<Ordine> ordini = Database.getOrders(false);
                ObjectMapper mapper = new ObjectMapper();
                pr.write("{\"Ordini\" :"+ mapper.writeValueAsString(ordini) +"}");
            }

            else if(request.getParameter("rtype").equals("getOrderDet")){
                // Richiesta dettagli singolo ordine
                int orderId =  Integer.parseInt(request.getParameter("ID"));
                Ordine order = Database.getOrder(orderId);
                if(order!=null){
                    String idPos = Database.getSeat(order.getIdPrenotazione());
                    ObjectMapper mapper = new ObjectMapper();
                    pr.write("{\"Ordine\" :"+mapper.writeValueAsString(order)+", \"Postazione\" :"+idPos+"}");
                }else {
                    pr.write("{\"Ordine\" :\"NESSUN ORDINE TROVATO\"}");
                }
            }
            else if(request.getParameter("rtype").equals("delOrder")){
                // Cancellazione ordine dalla piattaforma
                if(Database.deleteOrder(Integer.parseInt(request.getParameter("ID")))){
                    pr.write("{\"Message\" : \"Cancellazione riuscita.\" }");
                } else pr.write("{\"Message\" : \"Errore nella cancellazione.\" }");

            }

        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/histkitchen.jsp");
        dispatcher.forward(request, response);
    }
}