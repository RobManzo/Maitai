<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Maitai - Account Settings</title>
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

            <div class="container col-md-3 align-items-center py-2 text-center">
                <a href="" data-toggle="modal" data-target="#changepsw">
                    <div class="limit">
                        <img class="img-responsive" src="\Maitai\assets\img\psw.png" >
                    </div>
                    <h4 class="my-3" style="color: black;">Modifica la password</h4>
                </a>
                <p class="text-muted">Cambia la tua password.</p>
            </div>

            <div class="container col-md-3 align-items-center py-2 text-center">
                <a href="" data-toggle="modal" data-target="#changedet">
                    <div class="limit">
                        <img class="img-responsive" src="\Maitai\assets\img\email.png">
                    </div>
                    <h4 class="my-3" style="color: black;">Modifica Dati Personali</h4>
                </a>
                <p class="text-muted">Cambia dettagli account.</p>
            </div>

            <div class="container col-md-3 align-items-center py-2 text-center" id="logout">
                <a href="${pageContext.request.contextPath}/logout" id="logoutlink">
                    <div class="limit">
                        <img class="img-responsive" src="\Maitai\assets\img\exit.png">
                    </div>
                    <h4 class="my-3" style="color: black;">Effettua il Logout</h4>
                </a>
            </div>

        </div>


        <div class="modal fade" id="changepsw">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content"  style="background-color: antiquewhite;">
                    <div class="modal-header">
                        <h4 class="modal-title">Cambia Password</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body text-center" id="passform">

                        <form action="" method="post" id="changepass">

                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="newpsw">Nuova Password</label>
                                        <input type="password" id="newpsw" class="form-control" placeholder="Inserisci nuova password">
                                        <small></small>
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="reppsw">Ripeti nuova Password</label>
                                        <input type="password" id="reppsw" class="form-control" placeholder="Ripeti la nuova password">
                                        <small></small>
                                    </div>
                                </div>

                        </form>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Annulla</button>
                        <button type="button" class="btn btn-danger" onclick="cambiapsw()">Cambia Password</button>
                    </div>

                </div>
            </div>
        </div>

        <div class="modal fade" id="changedet">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content"  style="background-color: antiquewhite;">
                    <div class="modal-header">
                        <h4 class="modal-title">Cambia Dettagli Account</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body text-center" id="detform">

                        <form action="" method="post" id="detaccount">

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="cell">Nuovo Telefono</label>
                                    <input type="tel" id="cell" class="form-control" placeholder="Inserisci nuovo numero di telefono"  pattern="[0-9]{10}">
                                    <small></small>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="address">Nuovo Indirizzo</label>
                                    <input type="text" id="address" class="form-control" placeholder="Inserisci nuovo indirizzo">
                                    <small></small>
                                </div>
                            </div>

                        </form>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Annulla</button>
                        <button type="button" class="btn btn-danger" onclick="cambiadet()">Cambia Dettagli</button>
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
        <script src="/Maitai/js/account.js"></script>

    </body>
</html>

