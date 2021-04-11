<%@ page contentType="text/html;charset=UTF-8" %>
<html>

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Maitai - Login</title>
        <link rel="icon" type="image/x-icon" href="/Maitai/assets/img/favicon.ico" />
        <!-- Font Awesome icons (free version) -->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />

        <!-- Core theme CSS (includes Bootstrap) -->
        <link href="/Maitai/css/styles.css" rel="stylesheet" />
        <script src="/Maitai/js/login.js"></script>
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
                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-primary">Login</button>
                        <a href="${pageContext.request.contextPath}/signin">
                            <button type="button" class="btn btn-primary" >Registrati</button>
                        </a>
                    </div>
                    <div class="form-group text-center">
                        <a class="btn btn-warning align-self-center" href="" data-toggle="modal" data-target="#forgotpsw">Password Dimenticata</a>
                    </div>
                </form>
            </div>
        </section>

        <div class="modal fade" id="forgotpsw">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content"  style="background-color: antiquewhite;">
                    <div class="modal-header">
                        <h4 class="modal-title">Password Dimenticata</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body text-center">
                        <form method="post" id="resetpsw" action="">
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="email">Email</label>
                                    <input type="text" id="email" class="form-control" placeholder="Inserisci la tua Email">
                                    <small></small>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="codfisc">Codice Fiscale</label>
                                    <input type="text" id="codfisc" class="form-control" placeholder="Inserisci Cod. Fiscale">
                                    <small></small>
                                </div>
                            </div>
                            <div class="form-row justify-content-between">
                                <div class="form-group col-4">
                                    <a class="btn btn-danger" id="annulla">Annulla</a>
                                </div>
                                <div class="form-group col-4" onclick="resetpsw()">
                                    <a class="btn btn-danger" id="confirm">Conferma</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer-->
        <%@ include file="/WEB-INF/footer.jsp"%>

        <!-- Bootstrap core JS-->
        <script src="/Maitai/jquery/jquery.min.js"></script>
        <script src="/Maitai/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Third party plugin JS-->
        <script src="/Maitai/jquery-easing/jquery.easing.min.js"></script>
        <!-- Core theme JS-->
        <script src="/Maitai/js/scripts.js"></script>
        <% if(request.getSession().getAttribute("Login") != null && request.getSession().getAttribute("Login").equals("ERROR")){ %>
            <script type="text/javascript">
                loginerror();
            </script>

        <% request.getSession().invalidate(); } %>

    </body>
</html>
