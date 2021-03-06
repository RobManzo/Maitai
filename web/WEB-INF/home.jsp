<%@ page contentType="text/html;charset=UTF-8" %>
<html>

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Maitai - Home Profile</title>
        <link rel="icon" type="image/x-icon" href="/Maitai/assets/img/favicon.ico" />
        <!-- Font Awesome icons (free version) -->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap) -->
        <link href="/Maitai/css/styles.css" rel="stylesheet" />
        <script src="/Maitai/js/home.js"></script>
    </head>

    <body>
        <!-- Navigation -->
        <%@ include file="/WEB-INF/navbar.jsp"%>

        <div class="page-section row fill-window" style="background-color: antiquewhite; align-content: center;">

            <div class="container col-md-3 align-items-center py-2 text-center">
                <a href="" data-toggle="modal" data-target="#manbook">
                    <div class="limit">
                        <img class="img-responsive" src="\Maitai\assets\img\book.png" >
                    </div>
                    <h4 class="my-3" style="color: black;">Portale Prenotazioni</h4>
                </a>
                <p class="text-muted">Prenota o consulta le tue prenotazioni.</p>
            </div>

            <div class="container col-md-3 align-items-center py-2 text-center" id="food">
                <a href="${pageContext.request.contextPath}/cliente/food" id="foodlink">
                    <div class="limit">
                        <img class="img-responsive" src="\Maitai\assets\img\burger.png">
                    </div>
                    <h4 class="my-3" style="color: black;">Bar e Ristorazione</h4>
                </a>
                <p class="text-muted">Ordina cibi e bevande.</p>
            </div>

            <div class="container col-md-3 align-items-center py-2 text-center" id="entry"></div>
        </div>

        <div class="modal fade" id="access"></div>

        <div class="modal fade" id="accessinfo">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content"  style="background-color: antiquewhite;">
                    <div class="modal-header">
                        <h4 class="modal-title">INFORMAZIONE</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body text-center" id="message"> </div>
                    <div class="modal-footer"> <button type="button" class="btn btn-danger" data-dismiss="modal">OK</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="manbook">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content"  style="background-color: antiquewhite;">
                    <div class="modal-header">
                        <h4 class="modal-title">Portale Prenotazioni</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row align-items-center">
                            <div class="col-md-6">
                                <a href="${pageContext.request.contextPath}/cliente/booking">
                                    <div class="limit">
                                        <img class="img-responsive" src="\Maitai\assets\img\newbook.png" >
                                    </div>
                                    <h4 class="my-3" style="color: black;">Nuova Prenotazione</h4>
                                </a>
                            </div>
                            <div class="col-md-6">
                                <a href="${pageContext.request.contextPath}/cliente/ownbooks">
                                    <div class="limit">
                                        <img class="img-responsive" src="\Maitai\assets\img\conbook.png" >
                                    </div>
                                    <h4 class="my-3" style="color: black;">Consulta Prenotazioni</h4>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- Footer-->
        <div class="footer"><%@ include file="/WEB-INF/footer.jsp"%></div>

        <!-- Bootstrap core JS-->
        <script src="/Maitai/jquery/jquery.min.js"></script>
        <script src="/Maitai/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Third party plugin JS-->
        <script src="/Maitai/jquery-easing/jquery.easing.min.js"></script>
        <!-- Core theme JS-->
        <script src="/Maitai/js/scripts.js"></script>
    </body>
</html>

