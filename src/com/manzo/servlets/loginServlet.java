package com.manzo.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

import com.manzo.entities.Utente;
import com.manzo.misc.Database;

@WebServlet(name="loginServlet", urlPatterns={"/login"})

public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getUserPrincipal() != null && request.getSession().getAttribute("user") == null ) { //Richiesta post j_security_check
            System.out.println(request.getUserPrincipal().toString());
            try {
                Utente utente = Database.takeUser(request.getUserPrincipal().getName());
                if (utente == null) {
                    response.sendError(400);
                }

                request.getSession().setAttribute("user", utente);
                request.getSession().setAttribute("cart", new HashMap<String,Integer>());                              //Inizializzo il carrello vuoto nella sessione
                request.getSession().setAttribute("userId", utente.getIdUtente());                                     //Salvo l'id in un parametro della sessione
                int getentry = Database.getEntry(LocalDate.now(), utente.getIdUtente());
                if(getentry != 0) request.getSession().setAttribute("entry", getentry);
                else request.getSession().setAttribute("entry", null);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else if (request.getParameter("Authentication") != null && request.getParameter("Authentication").equals("Error")) {
            request.getSession().setAttribute("Login", "ERROR");
        }
        response.sendRedirect(request.getContextPath());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getUserPrincipal() != null && request.getSession().getAttribute("user") == null ) {
            doPost(request, response);
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(request, response);
        }

    }
}
