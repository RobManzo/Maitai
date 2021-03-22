package com.manzo.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzo.entities.Ordine;
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
import java.util.List;

@WebServlet(name="kitchenServlet", urlPatterns={"/kitchen"})
public class kitchenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter pr = response.getWriter();
            response.setContentType("application/json");

            if(request.getParameter("rtype").equals("getOrders")){
                List<Ordine> ordini = Database.getOrders();
                ObjectMapper mapper = new ObjectMapper();
                pr.write("{\"Ordini\" :"+ mapper.writeValueAsString(ordini) +"}");
            }

            else if(request.getParameter("rtype").equals("getOrderDet")){
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

        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/kitchen.jsp");
        dispatcher.forward(request, response);
    }
}