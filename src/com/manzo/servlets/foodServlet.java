package com.manzo.servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzo.entities.Ordine;
import com.manzo.entities.Prodotto;
import com.manzo.entities.Utente;
import com.manzo.misc.Database;
import com.manzo.misc.MailService;
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

/**
 * Classe per la visualizzazione del portale ristorazione e per la gestione delle richieste
 */
@WebServlet(name="foodServlet", urlPatterns={"/cliente/food"})
public class foodServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            PrintWriter pr = response.getWriter();
            response.setContentType("application/json");

            if(request.getParameter("rtype").equals("getProd")){
                // Richiesta dell'elenco dei prodotti disponibili
                List<Prodotto> products = Database.getProd();
                ObjectMapper mapper = new ObjectMapper();
                pr.write("{\"Prodotto\" :"+ mapper.writeValueAsString(products) +"}");
            }
            else if(request.getParameter("rtype").equals("upCart")){
                // Aggiunta prodotto al carrello
                ObjectMapper mapper = new ObjectMapper();
                HashMap<Integer, Integer> carrello = mapper.readValue(request.getParameter("localcart"), new TypeReference<>() {});
                request.getSession().setAttribute("cart", carrello);
                pr.write("{\"Message\" :\"Prodotto aggiunto al carrello!\"}");
            }
            else if(request.getParameter("rtype").equals("getCart")){
                // Richiesta dei prodotti presenti nel carrello
                ObjectMapper mapper = new ObjectMapper();
                pr.write("{\"Carrello\" :"+ mapper.writeValueAsString(request.getSession().getAttribute("cart")) +"}");
            }
            else if(request.getParameter("rtype").equals("sendOrder")){
                // Gestione dell'invio dell'ordine
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
                    Utente user = (Utente) request.getSession().getAttribute("user");
                    String messaggio = "<p>Ciao " + user.getNome() + " " + user.getCognome() + ", <br>"
                            + "Ti avvisiamo che il tuo ordine è stato correttamente inviato e a breve, ti verrà recapitato presso la tua postazione. <br><br>"
                            + "<br>Buon Appetito! <br><br>"
                            + "Lido Maitai</p>";

                    MailService mailer = new MailService(user.getEmail(), "Lido Maitai - Conferma ordine ristorazione", messaggio);
                    Thread thread = new Thread(mailer);
                    thread.start();

                    request.getSession().setAttribute("cart", new HashMap<Integer, Integer>());
                    pr.write("{\"Message\" :\"Ordine confermato!\"}");
                }
                else pr.write("{\"Message\" :\"Errore durante l'ordine!\"}");
            }
            else if(request.getParameter("rtype").equals("emptyCart")){
                //Svuota carrello
                request.getSession().setAttribute("cart", new HashMap<Integer, Integer>());
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
        int entry = (int) request.getSession().getAttribute("entry");
        if(entry != 0){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/food.jsp");
            dispatcher.forward(request, response);
        } else response.sendError(400);
    }
}