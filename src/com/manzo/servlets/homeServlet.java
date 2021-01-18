package com.manzo.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzo.misc.Database;

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
import java.util.List;

@WebServlet(name="homeServlet", urlPatterns={"/cliente/home"})
public class homeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LocalDate data = LocalDate.parse(request.getParameter("DataOdierna"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            int user = Integer.parseInt(request.getParameter("user"));

            PrintWriter pr = response.getWriter();
            response.setContentType("application/json");
            if(Database.entry(data, user)){                                         //Se c'è una prenotazione per questa ora allora l'utente può entrare
                pr.write();
            }
            pr.write();

        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(request, response);
    }
}