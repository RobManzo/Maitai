<%--
  Created by IntelliJ IDEA.
  User: powar
  Date: 26/11/2020
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Agency - Start Bootstrap Theme</title>
        <link rel="icon" type="image/x-icon" href="/Maitai/assets/img/favicon.ico" />
        <!-- Font Awesome icons (free version) -->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />

        <!-- Core theme CSS (includes Bootstrap) -->
        <link href="/Maitai/css/styles.css" rel="stylesheet" />
    </head>

    <body>
        <!-- Navigation-->
        <%@ include file="/WEB-INF/navbar.jsp"%>

        <section class="page-section row fill-window" style="background-color: antiquewhite;">
            <div class="pt-4 col-md-4 container">
                <form method="POST" id="formlogin" action="j_security_check">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="email" class="form-control" placeholder="Inserisci username" id="username" name="j_username">
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" class="form-control" placeholder="Inserisci password" id="password" name="j_password">
                    </div>
                    <div class="form-group form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox"> Ricordami
                        </label>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Login</button>
                        <a href="${pageContext.request.contextPath}/signin">
                            <button type="button" class="btn btn-primary" >Registrati</button>
                        </a>
                    </div>
                </form>
            </div>
        </section>

        <!-- Footer-->
        <%@ include file="/WEB-INF/footer.jsp"%>

        <!-- Bootstrap core JS-->
        <script src="/Maitai/jquery/jquery.min.js"></script>
        <script src="/Maitai/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Third party plugin JS-->
        <script src="/Maitai/jquery-easing/jquery.easing.min.js"></script>
        <!-- Contact form JS-->
        <script src="/Maitai/assets/mail/jqBootstrapValidation.js"></script>
        <script src="/Maitai/assets/mail/contact_me.js"></script>
        <!-- Core theme JS-->
        <script src="/Maitai/js/scripts.js"></script>

    </body>
</html>
