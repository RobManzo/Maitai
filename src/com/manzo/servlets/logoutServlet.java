package com.manzo.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe per la gestione del logout dell'utente
 */
@WebServlet(name = "logoutServlet", urlPatterns={"/logout"})
public class logoutServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.sendRedirect(request.getContextPath());
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath());
        }
}
