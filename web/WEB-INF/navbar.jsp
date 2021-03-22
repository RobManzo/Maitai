<%@ page import="com.manzo.entities.Utente" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.manzo.misc.Database" %>
<%--
  Created by IntelliJ IDEA.
  User: powar
  Date: 26/11/2020
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <style>
            @media (min-width: 992px) {
                .animate {
                    animation-duration: 0.3s;
                    -webkit-animation-duration: 0.3s;
                    animation-fill-mode: both;
                    -webkit-animation-fill-mode: both;
                }
            }

            @keyframes slideIn {
                0% {
                    transform: translateY(1rem);
                    opacity: 0;
                }
                100% {
                    transform:translateY(0rem);
                    opacity: 1;
                }
                0% {
                    transform: translateY(1rem);
                    opacity: 0;
                }
            }

            @-webkit-keyframes slideIn {
                0% {
                    -webkit-transform: transform;
                    -webkit-opacity: 0;
                }
                100% {
                    -webkit-transform: translateY(0);
                    -webkit-opacity: 1;
                }
                0% {
                    -webkit-transform: translateY(1rem);
                    -webkit-opacity: 0;
                }
            }

            .slideIn {
                -webkit-animation-name: slideIn;
                animation-name: slideIn;
            }

            .img-fluid {
                max-width: 100%; !important;
                height: 25px; !important;
            }
        </style>
    </head>

    <body>
        <% if(request.getUserPrincipal() != null && request.getSession().getAttribute("user") == null){
            try {
                Utente user = Database.takeUser(request.getUserPrincipal().getName());
                if (user == null) {
                    response.sendError(400);
                }
                request.getSession().setAttribute("user", user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } %>

    <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand js-scroll-trigger" href="${pageContext.request.contextPath}"><img src="/Maitai/assets/img/navbar-logo.png" alt="" /></a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars ml-1"></i>
                </button>
                    <div class="collapse navbar-collapse" id="navbarResponsive">
                        <ul class="navbar-nav text-uppercase ml-auto">
                            <li class="nav-item"><a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/#services">Servizi</a></li>
                            <li class="nav-item"><a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/#findus">Dove siamo</a></li>
                            <li class="nav-item dropdown">
                                    <% if(request.getSession().getAttribute("user") == null){ %>
                                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Accedi</a>
                                            <div class="dropdown-menu dropdown-menu-right animate slideIn" aria-labelledby="navbarDropdown">
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/login">Login  <span class="fas fa-sign-in-alt"/></a>
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/signin">Registrati  <span class="fas fa-clipboard-list"/></a></div>
                                    <% } else {%>
                                                <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Profilo</a></a>
                                                <div class="dropdown-menu dropdown-menu-right animate slideIn" aria-labelledby="navbarDropdown">
                                                    <% if(((Utente) request.getSession().getAttribute("user")).getRuolo().equals("cliente")){%>
                                                    <% pageContext.getOut().print("<a class=\"dropdown-item\" href=\""+request.getContextPath()+"/"+((Utente)request.getSession().getAttribute("user")).getRuolo()+"/home\">Dashboard  <span class=\"fas fa-user\"/></a>");%>
                                                    <%} else if(((Utente)request.getSession().getAttribute("user")).getRuolo().equals("cucina")){%>
                                                    <% pageContext.getOut().print("<a class=\"dropdown-item\" href=\""+request.getContextPath()+"/kitchen\">Dashboard  <span class=\"fas fa-user\"/></a>");%>
                                                    <%} else if(((Utente) request.getSession().getAttribute("user")).getRuolo().equals("admin")){%>
                                                    <% pageContext.getOut().print("<a class=\"dropdown-item\" href=\""+request.getContextPath()+"/aboard\">Dashboard  <span class=\"fas fa-user\"/></a>");%>
                                                    <%} else if(((Utente) request.getSession().getAttribute("user")).getRuolo().equals("staff")){%>
                                                    <% pageContext.getOut().print("<a class=\"dropdown-item\" href=\""+request.getContextPath()+"/sboard\">Dashboard  <span class=\"fas fa-user\"/></a>");%>
                                                    <%}%>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/account">Account  <span class="fas fa-user-cog"></span></a>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout  <span class="fas fa-sign-out-alt"/></a></div>
                                    <% }%>
                            </li>
                            <% if(request.getRequestURI().endsWith("/food.jsp")){ %>
                            <li class="nav-item"><a class="nav-link js-scroll-trigger" onclick="cartshow()" style="cursor: pointer"><div style="font-size: 0.5rem;"><i class="fas fa-shopping-cart fa-3x" href="#cartmodal"></i></div> </a></li>
                            <% }%>
                        </ul>
                    </div>
            </div>
        </nav>
    </body>
</html>
