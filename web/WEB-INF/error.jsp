<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Maitai - ERROR</title>
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
        <div class="page-section row fill-window" style="background-color: antiquewhite; align-content: center;">
            <div class="container align-self-center">
                <h1>Error ${errorCode}</h1>

                The page you requested cannot be shown.<br />
                <% switch ((int) request.getAttribute("errorCode")){
                    case 400: %>
                You cannot access this resource this way.
                <% break;
                    case 404: %>
                The page you requested does not exist.
                <% break;
                    case 403: %>
                You are not authorized to see the page you requested.
                <% break;
                    case 408: %>
                Your request timed out.
                <% break;
                    case 500: %>
                We are experiencing some technical difficulties, please try again later.
                <% break;
                    default: %>
                We have experienced an unexpected error, please try again later.
                <% break;
                } %>
            </div>
        </div>

    <jsp:include page="/WEB-INF/footer.jsp" />

    <!-- Scroll to Top Button (Only visible on small and extra-small screen sizes)-->
    <div class="scroll-to-top d-lg-none position-fixed"><a class="js-scroll-trigger d-block text-center text-white rounded" href="#page-top"><i class="fa fa-chevron-up" style="line-height: 3.1rem;"></i></a></div>
</body>
</html>