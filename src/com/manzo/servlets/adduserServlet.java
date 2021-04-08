package com.manzo.servlets;


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

@WebServlet(name="adduserServlet", urlPatterns={"/admin/adduser"})
public class adduserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name, surname, codfisc, address, province, email, pass, confpass, phone, role, error;
            name = request.getParameter("Nome");
            surname = request.getParameter("Cognome");
            LocalDate birthdate = LocalDate.parse(request.getParameter("Birthdate"));
            codfisc = request.getParameter("CFiscale");
            address = request.getParameter("Indirizzo");
            email = request.getParameter("Email");
            province = request.getParameter("Provincia");
            pass = request.getParameter("Password");
            confpass = request.getParameter("Confpass");
            phone = request.getParameter("Telefono");
            role = request.getParameter("Ruolo");

            PrintWriter pr = response.getWriter();
            response.setContentType("application/json");
            //ObjectMapper mapper = new ObjectMapper();                                   Classe per poter convertire un oggetto in stringa
            String status;

            System.out.println(name + surname + email + pass + confpass + phone + birthdate.toString() + codfisc );


            if((error = Miscellaneous.checkForm(name, surname, email, pass, confpass, phone, birthdate.toString(), codfisc)) != null){
                status = "{\"RESPONSE\" : \"Error\", \"Message\" : \"" + error + " Verrai reinderizzato all'inizio." + "\"}";

            } else if(Database.checkExist(email, codfisc)) {
                status = "{\"RESPONSE\" : \"Error\", \"Message\" : \"Email o persona già registrata. Verrai reinderizzato all'inizio.\"}";
            } else {
                Database.userSignIn(name, surname, email, codfisc, phone, birthdate, pass, address, province, role);
                status = "{\"RESPONSE\" : \"Correct\", \"Message\" : \"Registrazione effettuata. Verrai reinderizzato alla home.\"}";
            }

            pr.write(status);

        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400);
        }

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/adduser.jsp");
        dispatcher.forward(request, response);
    }
}