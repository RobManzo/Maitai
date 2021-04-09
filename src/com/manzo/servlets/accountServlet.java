package com.manzo.servlets;

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
import java.sql.SQLException;

@WebServlet(name="accountServlet", urlPatterns={"/account"})
public class accountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter pr = response.getWriter();
            response.setContentType("application/json");

            if(request.getParameter("rtype").equals("changepsw")){
                String newpsw = request.getParameter("newpass");
                Utente u = (Utente) request.getSession().getAttribute("user");
                if(Database.changePassword(u.getIdUtente(), newpsw)){
                    u.setPass(newpsw);
                    request.getSession().setAttribute("user", u);
                    pr.write("{\"message\" : \"Password cambiata con successo. \"}");
                } else pr.write("{\"message\" : \"Qualcosa è andato storto. \"}");

            }

            if(request.getParameter("rtype").equals("changetel")){
                String newtel = request.getParameter("newtel");
                Utente u = (Utente) request.getSession().getAttribute("user");
                if(Database.changeTel(u.getIdUtente(), newtel)){
                    u.setTelefono(newtel);
                    request.getSession().setAttribute("user", u);
                    pr.write("{\"message\" : \"Telefono cambiato con successo. \"}");
                } else pr.write("{\"message\" : \"Qualcosa è andato storto. \"}");

            }

            if(request.getParameter("rtype").equals("changeaddr")){
                String newaddr = request.getParameter("newaddr");
                Utente u = (Utente) request.getSession().getAttribute("user");
                if(Database.changeAddr(u.getIdUtente(), newaddr)){
                    u.setIndirizzo(newaddr);
                    request.getSession().setAttribute("user", u);
                    pr.write("{\"message\" : \"Indirizzo cambiato con successo. \"}");
                } else pr.write("{\"message\" : \"Qualcosa è andato storto. \"}");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/account.jsp");
        dispatcher.forward(request, response);
    }
}