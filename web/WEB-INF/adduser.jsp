<%@ page contentType="text/html;charset=UTF-8" %>

<html>

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Maitai - Aggiungi Utente</title>
        <link rel="icon" type="image/x-icon" href="/Maitai/assets/img/favicon.ico" />
        <!-- Font Awesome icons (free version) -->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap) -->
        <link href="/Maitai/css/styles.css" rel="stylesheet" />
        <script src="/Maitai/js/adduser.js"></script>
        <style>
            @media (min-width: 992px) {
                #mainNav {
                    background: transparent;
                }
            }

            #second, #third{
                display: none;
            }
        </style>

    </head>

    <body>

        <!-- Navigation -->
        <%@ include file="/WEB-INF/navbar.jsp"%>

        <div class="page-section" id="reg">
            <div class="container col-md-6 bg-light p-4 rounded shadow">
                <h5 class="text-center text-light mb-2 p-2 rounded lead" id="result">Registrazione</h5>
                <div class="progress-bar progress-bar-striped progress-bar-animated bg-danger rounded" role="progressbar" style="width:30%;" id="progressBar">
                    STEP 1
                </div>

                <div class="container pt-4">

                    <form action="" method="post" id="register">

                        <div id="first">
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="nome">Nome</label>
                                    <input type="text" id="nome" class="form-control" placeholder="Inserisci il tuo nome">
                                    <small></small>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="cognome">Cognome</label>
                                    <input type="text" id="cognome" class="form-control" placeholder="Inserisci il tuo cognome">
                                    <small></small>
                                </div>

                            </div>

                            <div class="form-row">

                                <div class="form-group col-md-6">
                                    <label for="birthdate">Data di Nascita</label>
                                    <input type="date" class="form-control" id="birthdate" name="birthdate" min="1900-01-01" max="2020-12-31" pattern="\d{2}/\d{2}/\d{4}">
                                    <small></small>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="codfisc">Codice Fiscale</label>
                                    <input type="text" id="codfisc" class="form-control" name="codfisc" placeholder="Inserisci Codice Fiscale (CNS)" oninput="checkCodFisc()">
                                    <small></small>
                                </div>

                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="address">Indirizzo domicilio</label>
                                    <input type="text" id="address" class="form-control" name="address" placeholder="Via/Piazza - Numero civico">
                                    <small></small>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="province">Provincia</label>
                                    <select id="province" class="form-control" name="prov" placeholder="Province">

                                    </select>
                                    <small></small>
                                </div>

                            </div>

                            <div class="form-row justify-content-between">
                                <div class="form-group col-4"></div>
                                <div class="form-group col-4">
                                    <a class="btn btn-danger disabled" id="next-1">Avanti</a>
                                </div>
                            </div>

                        </div>

                        <div id="second">

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="email">Email</label>
                                    <input type="email" id="email" class="form-control" name="email" placeholder="Inserire Email" onchange="checkEmail()">
                                    <small class="pb-1" id="emailhelp"></small>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="confirmemail">Conferma Email</label>
                                    <input type="email" id="confirmemail" class="form-control" name="confemail" placeholder="Re-Inserire Email" onchange="checkEmail()">
                                    <small id="confemailhelp"></small>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="password">Password</label>
                                    <input type="password" id="password" class="form-control" name="password" oninput="checkPass()">
                                    <small id="passhelp"></small>
                                </div>


                                <div class="form-group col-md-6">
                                    <label for="confirmpassword">Conferma Password</label>
                                    <input type="password" id="confirmpassword" class="form-control" name="confirmpassword" oninput="checkPass()">
                                    <small id="confpasshelp"></small>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="phone">Numero di cellulare</label>
                                    <input type="text" id="phone" class="form-control" name="phone" onchange="checkPhone()">
                                    <small id="phonehelp"></small>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="role">Ruolo</label>
                                    <select id="role" class="form-control" name="role" placeholder="Ruolo...">
                                        <option value="admin">ADMIN</option>
                                        <option value="staff">Staff</option>
                                        <option value="cucina">Cucina</option>
                                        <option value="cliente">Cliente</option>
                                    </select>
                                    <small></small>
                                </div>
                            </div>

                            <div class="form-row justify-content-between">
                                <div class="form-group col-4">
                                    <a class="btn btn-danger" id="prev-2">Indietro</a>
                                </div>

                                <div class="form-group col-4">
                                    <a class="btn btn-danger disabled" id="next-2">Avanti</a>
                                </div>
                            </div>

                        </div>

                        <div id="third">

                            <div class="form-row justify-content-between">
                                <div class="form-group col-4">
                                    <a class="btn btn-danger" id="prev-3">Indietro</a>
                                </div>

                                <div class="form-group col-4" onclick="submitform()">
                                    <a class="btn btn-danger" id="confirm">Conferma</a>
                                </div>
                            </div>
                        </div>

                        <div id="response">
                            <small>Ricorda che tutti i campi sono obbligatori.</small>
                        </div>


                    </form>
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

    </body>

</html>