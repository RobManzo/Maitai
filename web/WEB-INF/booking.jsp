<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Maitai - Prenotazione</title>
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
    <div><%@ include file="/WEB-INF/navbar.jsp"%></div>

    <div class="page-section row" id="book">
        <div class="container col-md-4 align-items-center py-2">
            <div class="d-flex">
                <div class="input-group mb-3 col-md-6 align-self-center">
                    <div class="input-group-prepend" style="fill: #844c04">
                        <label class="input-group-text" for="selectday">Giorno</label>
                    </div>
                    <select class="custom-select" id="selectday">
                    </select>
                </div>
                <div class="btn-group btn-group-toggle col-md-6 align-self-start pb-3" id="timeslot" data-toggle="buttons">
                    <label class="btn btn-secondary active"> Full Day
                        <input type="radio" name="options" id="fullday" checked> H 8/18
                    </label>
                    <label class="btn btn-secondary"> Mattina
                        <input type="radio" name="options" id="morning"> H 8/13
                    </label>
                    <label class="btn btn-secondary"> Pomeriggio
                        <input type="radio" name="options" id="afternoon"> H 13/18
                    </label>
                </div>
            </div>
            <div class="container-bg rounded shadow p-2" id="postazioni"></div>
        </div>

        <div class="container col-md-4 align-items-end pt-4" id="details">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">POS #</th>
                        <th scope="col">Tipologia</th>
                        <th scope="col">Prezzo</th>
                        <th scope="col">Handle</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>@mdo</td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>Jacob</td>
                        <td>Thornton</td>
                        <td>@fat</td>
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td>Larry</td>
                        <td>the Bird</td>
                        <td>@twitter</td>
                    </tr>
                </tbody>
            </table>
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
    <script src="/Maitai/js/snap.svg.js"></script>
    <script src="/Maitai/js/booking.js"></script>

</body>

</html>