<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Maitai - Registrazione</title>
    <link rel="icon" type="image/x-icon" href="/Maitai/assets/img/favicon.ico" />
    <!-- Font Awesome icons (free version) -->
    <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap) -->
    <link href="/Maitai/css/styles.css" rel="stylesheet" />
    <style>
        #second, #third{
            display: none;
        }
    </style>

</head>

<body>

<!-- Navigation -->
<%@ include file="/WEB-INF/navbar.jsp"%>

    <div class="bg-dark">

        <div class="page-section justify-content-center" style="padding-top: 8rem;">
            <div class="container col-md-4 bg-light p-4 rounded">

                <h5 class="text-center text-light bg-success mb-2 p-2 rounded lead" id="result">Registrazione</h5>
                <div class="progress-bar progress-bar-striped progress-bar-animated bg-danger rounded" role="progressbar" style="width:30%;" id="progressBar">
                    STEP 1
                </div>

                <div class="container pt-2">

                    <form action="" method="post" id="register">

                        <div id="first">
                            <div class="form-row">
                                 <div class="form-group col-md-6">
                                        <label for="nome">Nome</label>
                                        <input type="text" id="nome" class="form-control" placeholder="Inserisci il tuo nome">
                                </div>

                                 <div class="form-group col-md-6">
                                     <label for="cognome">Cognome</label>
                                     <input type="text" id="cognome" class="form-control" placeholder="Inserisci il tuo cognome">
                                 </div>
                            </div>

                            <div class="form-row">

                                <div class="form-group col-md-6">
                                    <label for="birthdate">Data di Nascita</label>
                                    <input type="date" class="form-control" id="birthdate" name="birthdate" min="1900-01-01" max="2020-12-31" pattern="\d{2}/\d{2}/\d{4}">
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="codfisc">Codice Fiscale</label>
                                    <input type="text" id="codfisc" class="form-control" name="codfisc" placeholder="Inserisci Codice Fiscale (CNS)">
                                </div>

                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="address">Indirizzo domicilio</label>
                                    <input type="text" id="address" class="form-control" name="address" placeholder="Via/Piazza - Numero civico">
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="prov">Provincia</label>
                                    <input type="text" id="prov" class="form-control" name="prov" placeholder="es: Palermo">
                                </div>

                            </div>

                            <div class="form-row justify-content-between">
                                <div class="form-group col-4"></div>
                                <div class="form-group col-4">
                                    <a class="btn btn-danger" id="next-1">Avanti</a>
                                </div>
                            </div>

                         </div>

                        <div id="second">

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="email">Email</label>
                                    <input type="email" id="email" class="form-control" name="email" placeholder="Inserire Email">
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="confemail">Conferma Email</label>
                                    <input type="email" id="confemail" class="form-control" name="confemail" placeholder="Re-Inserire Email">
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="password">Password</label>
                                    <input type="password" id="password" class="form-control" name="password">
                                    <span id="passmess"></span>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="confirmpassword">Conferma Password</label>
                                    <input type="password" id="confirmpassword" class="form-control" name="confirmpassword">
                                    <span id="confmess"></span>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="phone">Numero di cellulare</label>
                                    <input type="tel" id="phone" class="form-control" name="phone" pattern="[0-9]{9}">
                                </div>
                            </div>

                            <div class="form-row justify-content-between">
                                <div class="form-group col-4">
                                    <a class="btn btn-danger" id="prev-2">Indietro</a>
                                </div>

                                <div class="form-group col-4">
                                    <a class="btn btn-danger" id="next-2">Avanti</a>
                                </div>
                            </div>

                        </div>

                        <div id="third">

                            <div class="form-row justify-content-between">
                                <div class="form-group col-4">
                                    <a class="btn btn-danger" id="prev-3">Indietro</a>
                                </div>

                                <div class="form-group col-4">
                                    <a class="btn btn-danger" id="confirm">Confirm</a>
                                </div>
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
    <!-- Contact form JS-->
    <script src="/Maitai/assets/mail/jqBootstrapValidation.js"></script>
    <script src="/Maitai/assets/mail/contact_me.js"></script>
    <!-- Core theme JS-->
    <script src="/Maitai/js/scripts.js"></script>
    <script src="/Maitai/js/signin.js"></script>

</body>

</html>