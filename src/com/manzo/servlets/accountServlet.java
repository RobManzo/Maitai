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