<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Maitai - Admin Home</title>
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

        <div class="page-section row fill-window" style="background-color: antiquewhite; align-content: center;">

            <div class="container col-md-4 align-items-center py-2 text-center">
                <a href="" data-toggle="modal" data-target="#manusers">
                    <div class="limit">
                        <img class="img-responsive" src="\Maitai\assets\img\user-check.png" >
                    </div>
                    <h4 class="my-3" style="color: black;">Gestione Utenti</h4>
                </a>
                <p class="text-muted">Aggiungi o Modifica Utenti</p>
            </div>

            <div class="container col-md-4 align-items-center py-2 text-center">
                <a href="" data-toggle="modal" data-target="#manbook">
                    <div class="limit">
                        <img class="img-responsive" src="\Maitai\assets\img\beach.png" >
                    </div>
                    <h4 class="my-3" style="color: black;">Gestione Prenotazioni</h4>
                </a>
                <p class="text-muted">Gestisci prenotazioni o visualizza storico</p>
            </div>


            <div class="container col-md-4 align-items-center py-2 text-center">
                <a href="" data-toggle="modal" data-target="#manorders">
                    <div class="limit">
                        <img class="img-responsive" src="\Maitai\assets\img\burger.png" >
                    </div>
                    <h4 class="my-3" style="color: black;">Gestione Ordinazioni</h4>
                </a>
                <p class="text-muted">Gestisci ordinazioni presso il bar</p>
            </div>
        </div>

        <div class="modal fade" id="manusers">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content"  style="background-color: antiquewhite;">
                    <div class="modal-header">
                        <h4 class="modal-title">Gestione Utenti</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row align-items-center">
                            <div class="col-md-6">
                                <a href="${pageContext.request.contextPath}/staff/checkuser">
                                    <div class="limit">
                                        <img class="img-responsive" src="\Maitai\assets\img\user-check.png" >
                                    </div>
                                    <h4 class="my-3" style="color: black;">Gestisci Utenti</h4>
                                </a>
                            </div>
                            <div class="col-md-6">
                                <a href="${pageContext.request.contextPath}/admin/adduser">
                                    <div class="limit">
                                        <img class="img-responsive" src="\Maitai\assets\img\user-add.png" >
                                    </div>
                                    <h4 class="my-3" style="color: black;">Aggiungi Utente</h4>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="manorders">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content"  style="background-color: antiquewhite;">
                    <div class="modal-header">
                        <h4 class="modal-title">Gestione Ordini</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row align-items-center">
                            <div class="col-md-6">
                                <a href="${pageContext.request.contextPath}/kitchen">
                                    <div class="limit">
                                        <img class="img-responsive" src="\Maitai\assets\img\burger.png" >
                                    </div>
                                    <h4 class="my-3" style="color: black;">Ordini odierni</h4>
                                </a>
                            </div>
                            <div class="col-md-6">
                                <a href="${pageContext.request.contextPath}/admin/histkitchen">
                                    <div class="limit">
                                        <img class="img-responsive" src="\Maitai\assets\img\histburger.png" >
                                    </div>
                                    <h4 class="my-3" style="color: black;">Storico Ordini</h4>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="manbook">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content"  style="background-color: antiquewhite;">
                    <div class="modal-header">
                        <h4 class="modal-title">Gestione Prenotazioni</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row align-items-center">
                            <div class="col-md-6">
                                <a href="${pageContext.request.contextPath}/staff/map">
                                    <div class="limit">
                                        <img class="img-responsive" src="\Maitai\assets\img\conbook.png" >
                                    </div>
                                    <h4 class="my-3" style="color: black;">Prenotazioni Odierne</h4>
                                </a>
                            </div>
                            <div class="col-md-6">
                                <a href="${pageContext.request.contextPath}/admin/histbook">
                                    <div class="limit">
                                        <img class="img-responsive" src="\Maitai\assets\img\histbook.png" >
                                    </div>
                                    <h4 class="my-3" style="color: black;">Storico Prenotazioni</h4>
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
        <!-- Contact form JS-->
        <script src="/Maitai/assets/mail/jqBootstrapValidation.js"></script>
        <script src="/Maitai/assets/mail/contact_me.js"></script>
        <!-- Core theme JS-->
        <script src="/Maitai/js/scripts.js"></script>
        <script src="/Maitai/js/ahome.js"></script>

    </body>
</html>

