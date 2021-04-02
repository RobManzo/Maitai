<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Maitai - Storico Ordini</title>
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
        <!-- Navigation -->
        <%@ include file="/WEB-INF/navbar.jsp"%>

        <div class="page-section row fill-window" id="ordertab" style="background-color: antiquewhite; align-content: flex-start;"> </div>

        <div class="modal fade" id="infobox"> </div>
        <div class="modal fade" id="delconf"> </div>

        <!-- Footer-->
        <div class="footer"><%@ include file="/WEB-INF/footer.jsp"%></div>

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
        <script src="/Maitai/js/histkitchen.js"></script>

    </body>
</html>

