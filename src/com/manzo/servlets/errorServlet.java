package com.manzo.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe per la visualizzazione degli errori
 */
@WebServlet(name="errorServlet", urlPatterns={"/error"})
public class errorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int errorCode = (int) request.getAttribute("javax.servlet.error.status_code");
        request.setAttribute("errorCode", errorCode);
        response.setStatus(errorCode);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/error.jsp");
        dispatcher.forward(request, response);
    }
}