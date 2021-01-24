package com.manzo.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manzo.misc.Database;
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
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name="homeServlet", urlPatterns={"/cliente/home"})
public class homeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter pr = response.getWriter();
            response.setContentType("application/json");
            LocalDate data = LocalDate.now(ZoneId.of("GMT+1"));
            LocalTime time = LocalTime.now(ZoneId.of("GMT+1"));
            int ts = Miscellaneous.getTimeslot(time);
            int user = Integer.parseInt(request.getParameter("user"));

            if(request.getParameter("rtype").equals("setEntry")){

                if(ts == -1){
                    pr.write("{\"status\" : \"Error\"}");
                    return;
                }
                if(Database.setEntry(data, user, ts)){
                    pr.write("{\"status\" : \"Ok\"}");
                }
                 else{
                    pr.write("{\"status\" : \"Error\"}");
                }
            }

            else if(request.getParameter("rtype").equals("getEntry")){
                if(Database.getEntry(data, user)){
                    pr.write("{\"status\" : \"Ok\"}");
                } else{
                    pr.write("{\"status\" : \"Error\"}");
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(request, response);
    }
}