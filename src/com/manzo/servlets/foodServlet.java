package com.manzo.servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzo.entities.Ordine;
import com.manzo.entities.Prodotto;
import com.manzo.misc.Database;
import javafx.util.Pair;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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
                HashMap<Integer, Integer> carrello = mapper.readValue(request.getParameter("localcart"), new TypeReference<>() {});
                request.getSession().setAttribute("cart", carrello);
                System.out.println("Carrello Sessione: " + request.getSession().getAttribute("cart"));
                pr.write("{\"Message\" :\"Prodotto aggiunto al carrello!\"}");
            }
            else if(request.getParameter("rtype").equals("getCart")){
                ObjectMapper mapper = new ObjectMapper();
                pr.write("{\"Carrello\" :"+ mapper.writeValueAsString(request.getSession().getAttribute("cart")) +"}");
            }
            else if(request.getParameter("rtype").equals("sendOrder")){
                HashMap<Integer, Integer> products = (HashMap<Integer, Integer>) request.getSession().getAttribute("cart");
                HashMap<Integer, Pair<Integer, BigDecimal>> prodsInOrder = new HashMap<>();
                BigDecimal tot = new BigDecimal(0);
                for (Integer s : products.keySet()){
                    Prodotto p = Database.getProd(s);
                    prodsInOrder.put(s, new Pair<>(products.get(s), p.getImporto()));
                    tot = tot.add(new BigDecimal(products.get(s)).multiply(p.getImporto()));
                }
                Ordine order = new Ordine(LocalDate.now(), LocalTime.now(), prodsInOrder, tot, "emesso", (Integer) request.getSession().getAttribute("entry"));
                if(Database.setOrder(order, (Integer) request.getSession().getAttribute("entry")) > 0){
                    request.getSession().setAttribute("cart", new HashMap<Integer, Integer>());
                    pr.write("{\"Message\" :\"Ordine confermato!\"}");
                }
                else pr.write("{\"Message\" :\"Errore durante l'ordine!\"}");
            }
            else if(request.getParameter("rtype").equals("emptyCart")){
                request.getSession().setAttribute("cart", new HashMap<Integer, Integer>());
                System.out.println(request.getSession().getAttribute("cart"));
                pr.write("{\"Message\" :\"Carrello svuotato!\"}");
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
        int entry = (int) request.getSession().getAttribute("entry");
        if(entry != 0){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/food.jsp");
            dispatcher.forward(request, response);
        } else response.sendError(400);
    }
}