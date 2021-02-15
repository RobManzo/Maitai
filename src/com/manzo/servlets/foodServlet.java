package com.manzo.servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzo.entities.Prodotto;
import com.manzo.misc.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@WebServlet(name="foodServlet", urlPatterns={"/cliente/food"})
public class foodServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter pr = response.getWriter();
            response.setContentType("application/json");

            if(request.getParameter("rtype").equals("getProd")){
                List<Prodotto> products = Database.getProd();
                ObjectMapper mapper = new ObjectMapper();
                pr.write("{\"Prodotto\" :"+ mapper.writeValueAsString(products) +"}");
            }
            else if(request.getParameter("rtype").equals("upCart")){
                ObjectMapper mapper = new ObjectMapper();
                HashMap<String, Integer> carrello = mapper.readValue(request.getParameter("localcart"), new TypeReference<HashMap<String, Integer>>() {});
                request.getSession().setAttribute("cart", carrello);
                System.out.println("Carrello Sessione: " + request.getSession().getAttribute("cart"));
                pr.write("{\"Message\" :\"Prodotto aggiunto al carrello!\"}");
            }
            else if(request.getParameter("rtype").equals("getCart")){
                ObjectMapper mapper = new ObjectMapper();
                pr.write("{\"Carrello\" :"+ mapper.writeValueAsString(request.getSession().getAttribute("cart")) +"}");
            }
            else if(request.getParameter("rtype").equals("sendOrder")){


            }
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getSession().getAttribute("entry"));
        if(request.getSession().getAttribute("entry").equals("true")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/food.jsp");
            dispatcher.forward(request, response);
        } else response.sendError(400);
    }
}