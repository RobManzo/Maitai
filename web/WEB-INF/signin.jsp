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

    </style>

</head>

<body>

<!-- Navigation -->
<%@ include file="/WEB-INF/navbar.jsp"%>

    <div class="bg-dark">

        <div class="page-section justify-content-center" style="padding-top: 8rem;">
            <div class="container col-md-4 bg-light p-4 rounded">
                <h5 class="text-center text-light bg-success mb-2 p-2 rounded lead" id="result">Registrazione</h5>
                <div class="progress-bar bg-danger rounded" role="progressbar" style="width:20%;" id="progressBar">
                    <b class="lead" id="progressText">Step - 1</b>
                </div>

                <div class="container pt-2">

                    <form action="" method="post" id="register">

                        <!-- <div id="first">
                             <h4 class="text-center bg-primary pt-1 rounded text-light"> Informazioni Personali</h4>
                             <div class="form-group">
                                 <label for="nome">Nome</label>
                                 <input type="text" id="nome" class="form-control" placeholder="Nome">
                             </div>

                             <div class="form-group">
                                 <label for="cognome">Cognome</label>
                                 <input type="text" id="cognome" class="form-control" placeholder="Cognome">
                             </div>

                             <div class="form-group">
                                 <label for="birthdate">Data di Nascita</label><br>
                                 <input type="date" class="form-control" id="birthdate" name="birthdate" min="1900-01-01" max="2020-12-31" pattern="\d{2}/\d{2}/\d{4}" required>
                                 <div class="valid-feedback">Valido</div>
                                 <div class="invalid-feedback">Per favore riempi questo campo</div>
                             </div>

                             <div class="form-group">
                                 <a href="" class="btn-danger" id="next-1">Avanti</a>
                             </div>
                         </div>
                         -->

                        <!--<div id="second">
                              <div class="form-group">
                                  <label for="phone">Numero di cellulare</label>
                                  <input type="tel" id="phone" name="phone" pattern="[0-9]{9}" required>
                                  <div class="valid-feedback">Valido</div>
                                  <div class="invalid-feedback">Inserire un numero di cellulare valido</div>
                              </div>

                              <div class="form-group">
                                  <label for="email">Email</label>
                                  <input type="email" id="email" name="email" placeholder="Inserire Email" required>
                                  <div class="valid-feedback">Ok</div>
                                  <div class="invalid-feedback">Inserire email valida</div>
                              </div>
                              -->

                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" id="password" name="password" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" minlength="7" required>
                            <div class="valid-feedback">Ok</div>
                            <div class="invalid-feedback">La password deve essere composta da almeno 7 lettere di cui una lettera maiuscola, una minuscola e un numero</div>
                        </div>

                        <div class="form-group">
                            <label for="confirmpassword">Conferma Password</label>
                            <input type="password" id="confirmpassword" name="confirmpassword" onkeyup="validator()" required>
                        </div>
                        <!--/div-->
                    </form>

                </div>

            </div>
        </div>
    </div>


    <!-- Footer-->
    <%@ include file="/WEB-INF/footer.jsp"%>

   <!-- <script>
        function validator() {
                var valid = $("#register").validate({
                    rules: {
                        password: "required",
                        confirmpassword: {
                            equalTo: "#password"
                        }
                    },
                    messages: {
                        password: " Enter Password",
                        confirmpassword: " Enter Confirm Password Same as Password"
                    }
                });
                if (valid.form()) {
                    alert('Sucess');
                }
        }

    </script>-->

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