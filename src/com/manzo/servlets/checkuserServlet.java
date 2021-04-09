package com.manzo.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebServlet(name="checkuserServlet", urlPatterns={"/staff/checkuser"})
public class checkuserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter pr = response.getWriter();
            response.setContentType("application/json");

            if(request.getParameter("rtype").equals("getUsers")){
                Utente user = (Utente) request.getSession().getAttribute("user");
                List<Utente> users = Database.getUsers(user.getRuolo());
                ObjectMapper mapper = new ObjectMapper();
                pr.write("{\"Utenti\" :"+ mapper.writeValueAsString(users) +"}");
            }

            else if(request.getParameter("rtype").equals("getUserDet")){
                Utente user = Database.takeUser(Integer.parseInt(request.getParameter("ID")));
                if(user!=null){
                    ObjectMapper mapper = new ObjectMapper();
                    pr.write("{\"Utente\" :"+mapper.writeValueAsString(user)+"}");
                }else {
                    pr.write("{\"Utente\" :\"NESSUN UTENTE TROVATO\"}");
                }
            }

            else if(request.getParameter("rtype").equals("removeUser")){
                if(Database.deleteUser(Integer.parseInt(request.getParameter("ID")))){
                    pr.write("{\"Message\" :\" Utente rimosso con successo.\"}");
                } else pr.write("{\"Message\" :\" Errore generico.\"}");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/checkuser.jsp");
        dispatcher.forward(request, response);
    }
}